package com.mlm.mlmserver.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;


public class FileUtils {	
	public JSONObject readFileToJSONObject(String file) {
		String result = null;
		try {
			result = new String(Files.readAllBytes(Paths.get(file)));
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return JSONObject.parseObject(result);
	}
	
	public JSONArray readFileToJSONArray(String file) {
		String result = null;
		try {
			result = new String(Files.readAllBytes(Paths.get(file)));
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return JSONArray.parseArray(result);
	}

	public void writeStringToFile(String file, String data) {
		Path p = Paths.get(file);
		Path pp = p.getParent();
		try {
			if (!Files.exists(pp)) {
				Files.createDirectories(pp);
			}
			Files.write(p, data.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		File file = new File(path);
//		if (!file.getParentFile().exists()) {
//			file.getParentFile().mkdirs();
//		}
//		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
//			writer.write(data);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public boolean rename(String fromPath, String toPath) {		
		Path from = Paths.get(fromPath);
	    Path to = Paths.get(toPath);
	    boolean result = false;
	    try {
	        Files.move(from, to);
	        result = true;
	    } catch (IOException e) {
	        System.err.println("重命名失败: " + e.getMessage());
	    }
		return result;
	}
	

    
    
	/** 上传文件保存到指定目录，返回文件名。如果父文件夹不存在则自动创建父文件夹。如果文件已存在，将覆盖之前的文件
     * @param	multipartFile   MultipartFile。
     * @param	path		文件保存绝对路径。
     * @return 	带有时间戳的文件名
     */
	public String uploadFile(MultipartFile multipartFile, String path) {		
		Path file = Paths.get(path);
		try {
			if (Files.notExists(file.getParent())) {
				Files.createDirectories(file.getParent());
			}
//			 原始文件写入新文件，如果文件名重复的话，将会覆盖掉之前的文件		
			multipartFile.transferTo(file);
		} catch (IllegalStateException | IOException e) {			
			e.printStackTrace();
		}
		return multipartFile.getOriginalFilename();
	}
	
	
	/** 创建文件夹
     * @param	url    	文件夹绝对路径
     */
	public Path createDirectory(String url) {	
		Path dir = Paths.get(url);
		if (Files.notExists(dir)) {			
			try {
				dir = Files.createDirectories(dir);
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}
		return dir;		
	}
	
	/** 删除文件夹及其下的文件（也可以删除文件）
     * @param	url    	文件夹绝对路径
     */
	public boolean deleteDirectory(String url) {
		System.out.println("删除：" + url);
		boolean res = false;
		Path pdir = Paths.get(url);
		if (Files.exists(pdir)) {			
			try {
				Files.walkFileTree(pdir, new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
						Files.delete(file);
						return FileVisitResult.CONTINUE;
		        	}
					@Override
					public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
						Files.delete(dir);
		            	return FileVisitResult.CONTINUE;
		        	}
				});
				System.out.println("删除成功");
				res = true;
			} catch (IOException e) {
				System.out.println("删除失败");
		    	e.printStackTrace();
			}
		} else {
			System.out.println("路径不存在");
		}
		return res;
	}
	
	/** 遍历指定目录。
     * @param	url		文件夹绝对路径。
     * @param	globs	指定遍历的文件类型。不指定globs时，则获取所有文件
     * @return 	返回目录下的文件夹及文件。
     */
	public JSONArray traversalDirectory(String url, String[] globs) {
		JSONArray root = new JSONArray();
		Path pdir = Paths.get(url);
		if (Files.isDirectory(pdir)) {
			try {
				Files.walkFileTree(pdir, new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
						if (globs == null || globs.length == 0) {
							addToJSONArray(root, file.getFileName().toString(), pdir.relativize(file).toString(), attributes);
						} else {
							String name = file.getFileName().toString();
							String extension = name.substring(name.lastIndexOf(".") + 1);														
							if (Arrays.stream(globs).anyMatch(glob -> glob.equalsIgnoreCase(extension))) {
								addToJSONArray(root, name, pdir.relativize(file).toString(), attributes);
							}
						}
						return FileVisitResult.CONTINUE;
		        	}
					@Override
					public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attributes) throws IOException {
						addToJSONArray(root, file.getFileName().toString(), pdir.relativize(file).toString(), attributes);
		            	return FileVisitResult.CONTINUE;
		        	}				
				});				
			} catch (IOException e) {
		    	e.printStackTrace();
			}
		} else {
			System.out.println("目录不存在");
		}
		return root;
	}
	
	private static void addToJSONArray(JSONArray root, String name, String relativePath, BasicFileAttributes attributes) {
		if (relativePath.length() == 0) return;
        String[] parts = relativePath.split(Pattern.quote(File.separator));
        JSONArray current = root;
        for (int i = 0; i < parts.length; i++) {
            String key = parts[i];
            if (i == parts.length - 1) {
            	JSONObject info = new JSONObject();
            	info.put("name", name);
    			info.put("path", relativePath);
    			info.put("ctime", attributes.creationTime().toString());
            	if (attributes.isDirectory()) {
        			info.put("type", "directory");
        			info.put("data", new JSONArray());
            	} else {
        			info.put("type", "file");
            	}
            	current.add(info);
            } else {
            	for (int j = 0; j < current.size(); j++) {
            		JSONObject item = current.getJSONObject(j);
					if (item.getString("name").equals(key)) {
						current = item.getJSONArray("data");
						break;
					}
				}
            }
        }
    }
	
	
	
	public static void main(String[] args) {

	}
}
