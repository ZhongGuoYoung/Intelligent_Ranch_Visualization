package com.mlm.mlmserver.service;

import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson2.JSONArray;


public interface FileService {	
	JSONArray getDirectoriesAndFiles(String path, String[] globs);
	void addDirectory(String path);
	void updateDirectory(String oldPath, String newPath);
	boolean deleteDirectory(String path);
	
	void uploadFile(String path, MultipartFile file);

}
