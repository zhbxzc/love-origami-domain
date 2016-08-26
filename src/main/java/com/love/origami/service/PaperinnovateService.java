package com.love.origami.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.love.origami.dao.paperinnovateMapper;
import com.love.origami.model.Paperinnovate;
import static com.love.origami.common.CommonUtil.setResult;


@Service("PaperinnovateService")
public class PaperinnovateService {
	@Resource
	private paperinnovateMapper paperinnovateMapper;
	/**
	 * 
	* @Title: register 
	* @Description: 注册折纸手艺
	* @param @param paperinnovate
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@Transactional
	public String register(Paperinnovate paperinnovate){
		if(paperinnovate.getTitle()==null||"".equals(paperinnovate.getTitle())){
			return setResult(paperinnovate, false, "标题不能为空");
		}
		int count = paperinnovateMapper.isDup(paperinnovate);
		if(count!=0){
			return setResult(paperinnovate, false, "title标题不能重复");
		}
		paperinnovate.setCreatedTime(new Date());
		paperinnovateMapper.insertSelective(paperinnovate);
		return setResult(paperinnovate, true, "新增成功");
	}
	
	/**
	 * 
	* @Title: delete 
	* @Description: 根据折纸ID删除一条信息 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	
	public String delete(Integer id) {
		int result = 0;
		String flag = "0";
		try {
			result = paperinnovateMapper.deleteByPrimaryKey(id);

		} catch (Exception e) {
			System.out.println(e);
		}
		if (result > 0) {
			flag = "1";
		} else {
			flag = "0";
		}
		return flag;
	}
		

	/**
	 * 
	* @Title: search 
	* @Description: 根据查询条件返回信息列表 
	* @param @param didic
	* @param @return    设定文件 
	* @return List<Didic>    返回类型 
	* @throws
	 */
	public List<Paperinnovate> search(Paperinnovate paperinnovate){
		List<Paperinnovate> paperinnovatelist= paperinnovateMapper.selectByCondition(paperinnovate);
		return paperinnovatelist;
	}
	/**
	 * 
	* @Title: searchCount 
	* @Description: 根据查询条件返回信息列表条数 
	* @param @param paperinnovate
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int searchCount(Paperinnovate paperinnovate) {
		return paperinnovateMapper.searchCount(paperinnovate);
	}
	/**
	 * 
	* @Title: getById 
	* @Description: 根据ID查询信息 
	* @param @param id
	* @param @return    设定文件 
	* @return paperinnovate    返回类型 
	* @throws
	 */
	public Paperinnovate getById(Integer id) {
		return paperinnovateMapper.getById(id);
	}
	
	public String alter(Paperinnovate paperinnovate){
		// 校验标题
		if(paperinnovate.getTitle()==null||"".equals(paperinnovate.getTitle())){
			return setResult(paperinnovate, false, "标题不能为空");
		}
		int count = paperinnovateMapper.isDup(paperinnovate);

		paperinnovate.setLastUpdatedTime(new Date());
		// 更新折纸信息
		int num = paperinnovateMapper.updateByPrimaryKeySelective(paperinnovate);
		if(num>0){
			return setResult(paperinnovate, true, "修改成功");
		}else{
			return setResult(paperinnovate, false, "修改失败");
		}
	}
}
