package com.love.origami.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.love.origami.model.Paperinnovate;
import com.love.origami.service.PaperinnovateService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;



@RestController
@RequestMapping("Paperinnovatedomain")
public class PaperinnovateController {
	
	@Resource
	private PaperinnovateService paperinnovateService;
	
	/**
	 * 注册诊断字典
	 * FIXME 
	 * @param didicInfo诊断信息的JSON串
	 * @return 注册结果
	 */
	@RequestMapping(value="/origami/oriinns", method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "hystrixjsonQuery")
	public String register(@RequestBody String paperinnovateInfo){
		Paperinnovate paperinnovate = JSON.parseObject(paperinnovateInfo,Paperinnovate.class);
		String register = paperinnovateService.register(paperinnovate);
		return register;
	}
	
	public String hystrixjsonQuery(String didicInfo)
	{
		return "{\"result\":\"error\"}";
	}
	
	
}
