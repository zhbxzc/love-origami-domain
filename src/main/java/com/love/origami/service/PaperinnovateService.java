package com.love.origami.service;
import static com.love.origami.common.CommonUtil.setResult;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.love.origami.dao.PaperinnovateMapper;
import com.love.origami.model.Paperinnovate;


@Service("PaperinnovateService")
public class PaperinnovateService {
	@Resource
	private PaperinnovateMapper paperinnovateMapper;
	

	/**
	 * 注册
	 * FIXME 
	 * @param 信息的JSON串
	 * @return 注册结果
	 */
	@Transactional
	public String register(Paperinnovate paperinnovate){

		paperinnovate.setCreatedTime(new Date());
		paperinnovateMapper.insertSelective(paperinnovate);
		return setResult(paperinnovate, true, "新增成功");
	}
	
	/**
	 * 根据ID删除一条信息
	 * @param id
	 * @return 删除成功标志
	 * 
	 */
	public String delete(Integer id) {
		int result = 0;
		String flag = "0";
		try {
			result = paperinnovateMapper.delete(id);

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
	 * 根据查询条件返回列表
	 * @return PageBean
	 * @author 
	 * @param  
	 * @since 2016/8/15 
	 */
	public List<Paperinnovate> search(Paperinnovate paperinnovate){
		//查询某一页的所有记录 含有offset和size,如0-7,8-15
		List<Paperinnovate> List= paperinnovateMapper.selectByCondition(paperinnovate);
		//返回当前页面所有的记录信息
		return List;
	}
	
	
	/**
	 * 根据查询条件返回列表条数
	 * @return
	 * @author 
	 * @since 2016/8/16 
	 */
	public int searchCount(Paperinnovate paperinnovate) {
		int count = paperinnovateMapper.searchCount(paperinnovate);
		return count;
	}
	
	/**
	 * 根据ID查询信息
	 * @return 
	 * @author 
	 * @since 2016/8/16 
	 */
	public Paperinnovate getById(Integer id) {
		return paperinnovateMapper.selectById(id);
	}
	
	
	/**
	 * FIXME 修改信息 
	 * @param 
	 * @return 
	 */
	public String alter(Paperinnovate paperinnovate){
	
		paperinnovate.setLastUpdatedTime(new Date());
		// 更新信息
		int num = paperinnovateMapper.updateByPrimaryKeySelective(paperinnovate);
		if(num>0){
			return setResult(paperinnovate, true, "修改成功");
		}else{
			return setResult(paperinnovate, false, "修改失败");
		}
	}
	
}
