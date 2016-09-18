package com.love.origami.controller;

import java.io.UnsupportedEncodingException;
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
@RequestMapping("/origami")
public class PaperinnovateController {
	
	@Resource
	private PaperinnovateService paperinnovateService;
	
	
	@RequestMapping(value="/oriinns", method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "hystrixjsonQuery")
	public String register(@RequestBody String paperinnovateInfo){
		Paperinnovate paperinnovate = JSON.parseObject(paperinnovateInfo,Paperinnovate.class);
		String register = paperinnovateService.register(paperinnovate);
		return register;
	}
	
	
	  @RequestMapping(value="/oriinns/{id}",method=RequestMethod.GET)
	   @HystrixCommand(fallbackMethod = "hystrixidQuery")
	   public String getById(@PathVariable("id") Integer id){
		  Paperinnovate paperinnovate = paperinnovateService.getById(id);
		    String result =JSONObject.toJSONString(paperinnovate);
			return result;
	   }
	  @RequestMapping(value="/oriinns/{id}",method=RequestMethod.PUT)
	   @HystrixCommand(fallbackMethod = "hystrixjsonQuery")
	   public String alter(@RequestBody String paperinnovateInfo){
		  Paperinnovate paperinnovate=JSON.parseObject(paperinnovateInfo, Paperinnovate.class);
			String result=paperinnovateService.alter(paperinnovate);	
		    return result;
	   }
		@RequestMapping(value = "/oriinns", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		@HystrixCommand(fallbackMethod = "searchhystrixjsonQuery")
		public String search(Paperinnovate paperinnovate) {
			if(paperinnovate.getPageIndex()!=null&&paperinnovate.getPageSize()!=null){
				paperinnovate.offset();
			}
			List<Paperinnovate> list = paperinnovateService.search(paperinnovate);
			String Array =JSONArray.toJSONString(list);
			return Array;
		}
		@RequestMapping(value = "/oriinns/counts", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		@HystrixCommand(fallbackMethod = "searchhystrixjsonQuery")
		public String searchCount(Paperinnovate paperinnovate) {
			if(paperinnovate.getPageIndex()!=null&&paperinnovate.getPageSize()!=null){
				paperinnovate.offset();
			}
			int count = paperinnovateService.searchCount(paperinnovate);
			return String.valueOf(count);
		}
		  @RequestMapping(value="/oriinns/{id}",method=RequestMethod.DELETE)
		   @HystrixCommand(fallbackMethod = "hystrixidQuery")
		   public String delete(@PathVariable("id") Integer id) throws UnsupportedEncodingException{
			   String result=  paperinnovateService.delete(id);		 
				 return result;
		   }
	  public String hystrixQuery()
		{
			return "{\"result\":\"error\"}";
		}
		public String hystrixidQuery(Integer id)
		{
			return "{\"result\":\"error\"}";
		}
		public String hystrixjsonQuery(String customerInfo)
		{
			return "{\"result\":\"error\"}";
		}
		public String searchhystrixjsonQuery(Paperinnovate paperinnovate)
		{
			return "{\"result\":\"error\"}";
		}
	
}
