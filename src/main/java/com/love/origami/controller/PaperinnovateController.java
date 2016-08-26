package com.love.origami.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	 * 
	* @Title: register 
	* @Description:新增
	* @param @param paperinnovateInfo
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value="/origami/oriinns", method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "hystrixjsonQuery")
	public String register(@RequestBody String paperinnovateInfo){
		Paperinnovate paperinnovate = JSON.parseObject(paperinnovateInfo,Paperinnovate.class);
		String result = paperinnovateService.register(paperinnovate);
		return result;
	}
	public String hystrixjsonQuery(String paperinnovateInfo)
	{
		return "{\"result\":\"error\"}";
	}
	
	/**
	 * 
	* @Title: delete 
	* @Description: 根据ID删除一条信息 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/origami/oriinns/{id}", method = RequestMethod.DELETE)
	@HystrixCommand(fallbackMethod = "hystrixjsonQuery")
	public String  delete(@PathVariable String id){
		return paperinnovateService.delete(Integer.parseInt(id));

	}
	/**
	 * 
	* @Title: search 
	* @Description: 根据查询条件查询信息列表
	* @param @param paperinnovate
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/origami/oriinns", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@HystrixCommand(fallbackMethod = "searchhystrixjsonQuery")
	public String search(Paperinnovate paperinnovate) {
		if(paperinnovate.getPageIndex()==null){
			paperinnovate.setPageIndex(1);
		}
		if(paperinnovate.getPageSize()==null){
			paperinnovate.setPageSize(10);
		}
		if(paperinnovate.getPageIndex()!=null&&paperinnovate.getPageSize()!=null){
			paperinnovate.offset();
		}
		List<Paperinnovate> paperinnovatelist = paperinnovateService.search(paperinnovate);
		String paperinnovateArray =JSONArray.toJSONString(paperinnovatelist);
		/*JSONArray array =JSONArray.parseArray(didicArray);
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = (JSONObject) array.get(i);
				// 取出 jsonObject 中的字段
				if(obj.getBoolean("infectiousFlag") != null){
					if (obj.getBoolean("infectiousFlag")) {
						obj.put("infectiousFlag", "是");
					}else{
						obj.put("infectiousFlag", "否");
					}
				}else{
					obj.put("infectiousFlag", null);
				}
				if(obj.getBoolean("symptomFlag") != null){
					if (obj.getBoolean("symptomFlag")) {
						obj.put("symptomFlag", "是");
					}else{
						obj.put("symptomFlag", "否");
					}
				}else{
					obj.put("symptomFlag", null);
				}
				
			}
		}
		String didicArraystr  =array.toJSONString();
		return didicArraystr;*/
		return paperinnovateArray;
	}
	
	public String searchhystrixjsonQuery(Paperinnovate paperinnovate)
	{
		return "{\"result\":\"error\"}";
	}
	
	/**
	 * 根据查询条件获取信息记录数
	 * @param didicvo
	 * @return
	 */
	@RequestMapping(value = "/origami/oriinns/counts", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@HystrixCommand(fallbackMethod = "searchhystrixjsonQuery")
	public String searchCount(Paperinnovate paperinnovate) {
		if(paperinnovate.getPageIndex()!=null&&paperinnovate.getPageSize()!=null){
			paperinnovate.offset();
		}
		int count = paperinnovateService.searchCount(paperinnovate);
		return String.valueOf(count);
	}
	/**
	 * 
	* @Title: getById 
	* @Description: 根据id获取信息 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value="/origami/oriinns/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
/*	@HystrixCommand(fallbackMethod = "hystrixjsonQuery")*/
	public String  getById(@PathVariable String id){
		Paperinnovate paperinnovate = paperinnovateService.getById(Integer.parseInt(id));
		JSONObject json = (JSONObject) JSONObject.toJSON(paperinnovate);
	    String paperinnovatejson =json.toString();
		return paperinnovatejson;
	}
	/**
	 * 
	* @Title: alter 
	* @Description: 修改折纸信息
	* @param @param paperinnovateInfo
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value="/origami/oriinns",method=RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	@HystrixCommand(fallbackMethod = "hystrixjsonQuery")
	public String alter(@RequestBody String paperinnovateInfo){
		Paperinnovate paperinnovate=JSON.parseObject(paperinnovateInfo, Paperinnovate.class);
		String result = paperinnovateService.alter(paperinnovate);	
		return result;
	}
	
}
