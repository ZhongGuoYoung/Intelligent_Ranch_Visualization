package com.mlm.mlmserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson2.JSONArray;
import com.mlm.mlmserver.config.YmlConfig;
import com.mlm.mlmserver.service.FileService;
import com.mlm.mlmserver.utils.FileUtils;

@Service
public class FileServiceImpl implements FileService {
	@Autowired 
	private YmlConfig yml;
	
	@Override
	public JSONArray getDirectoriesAndFiles(String path, String[] globs) {		
		return new FileUtils().traversalDirectory(yml.getWordPath() + path, globs);
	}	
	@Override
	public void addDirectory(String path) {
		new FileUtils().createDirectory(yml.getWordPath() + path);
	}
	@Override
	public void updateDirectory(String oldPath, String newPath) {
		new FileUtils().rename(yml.getWordPath() + oldPath, yml.getWordPath() + newPath);
	}
	@Override
	public boolean deleteDirectory(String path) {		
		return new FileUtils().deleteDirectory(yml.getWordPath() + path);
	}
	
	
	@Override
	public void uploadFile(String path, MultipartFile file) {
		new FileUtils().uploadFile(file, yml.getWordPath() + path);
	}

}
