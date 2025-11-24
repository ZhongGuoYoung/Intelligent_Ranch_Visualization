package com.mlm.mlmserver.controller;

import com.alibaba.fastjson2.JSONArray;
import com.mlm.mlmserver.entity.Response;
import com.mlm.mlmserver.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file/")
public class FileController {
	@Resource
	private FileService fs;

	@RequestMapping(value = "getDirectoriesAndFiles",method = {RequestMethod.POST, RequestMethod.GET})
	public Object getDirectoriesAndFiles(String path, String[] globs) {
		JSONArray result = fs.getDirectoriesAndFiles(path, globs);
	 	return new Response<Object>().success(result);
	}
	@RequestMapping(value = "addDirectory", method = {RequestMethod.POST, RequestMethod.GET})
	public Object addDirectory(String path){
		fs.addDirectory(path);
		return new Response<Object>().success(1);
	}						 
	@RequestMapping(value = "updateDirectory", method = {RequestMethod.POST, RequestMethod.GET})
	public Object updateDirectory(String oldPath, String newPath){
		fs.updateDirectory(oldPath, newPath);
		return new Response<Object>().success(1);
	}	
	@RequestMapping(value = "deleteDirectory", method = {RequestMethod.POST, RequestMethod.GET})
	public Object deleteDirectory(String path){	
		boolean res = fs.deleteDirectory(path);
		return new Response<Object>().success(res);
	}
	
	@RequestMapping(value = "uploadFile", method = {RequestMethod.POST, RequestMethod.GET})
	public Object uploadFile(String path, MultipartFile file){
		fs.uploadFile(path, file);
		return new Response<Object>().success(1);
	}
	
}
