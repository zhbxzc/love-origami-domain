package com.love.origami.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.love.origami.common.FilesVO;
import com.love.origami.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/origami")
@Api(value="filedomain",description="文件处理领域服务接口")
public class FileController {
	@Resource
	private FileService fileService;

	@ApiOperation(value="文件上传",notes="文件上传接口")
	@RequestMapping(value="/files",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String savefile(@RequestParam("whose") String whose,@RequestParam("file") MultipartFile file,HttpServletRequest request){
		FilesVO filesVO=new FilesVO();
		filesVO.setFile(file);
		filesVO.setWhose(whose);
		String result = fileService.savefile(filesVO,request);
		return JSON.toJSONString(result);
	}
	@ApiOperation(value="文件下载",notes="文件下载接口")
	@RequestMapping(value="/files/{id}",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public ModelAndView getfile(@PathVariable("id") String id,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("multipart/form-data");
		try {
			ServletOutputStream out = response.getOutputStream();
			fileService.getFile(id, out,response);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView();
	}
	@ApiOperation(value="文件基本信息查看",notes="文件基本信息查看接口")
	@RequestMapping(value="/files",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public List<FilesVO> searchFile(){
		List<FilesVO> list = fileService.searchAll();
		return list;
	}
	@ApiOperation(value="文件删除",notes="文件删除接口")
	@RequestMapping(value="/files/{id}",method=RequestMethod.DELETE,produces="application/json;charset=UTF-8")
	public String deleteFile(@PathVariable("id") String id){
		boolean result = fileService.deleteFile(id);
		return  JSON.toJSONString(result);
	}
}
