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
	 * 注册诊断字典
	 * FIXME 
	 * @param didicInfo诊断信息的JSON串
	 * @return 注册结果
	 */
	@Transactional
	public String register(Paperinnovate paperinnovate){
		if(paperinnovate.getTitle()==null||"".equals(paperinnovate.getTitle())){
			return setResult(paperinnovate, false, "标题不能为空");
		}
		paperinnovate.setCreatedTime(new Date());
		paperinnovateMapper.insertSelective(paperinnovate);
		return setResult(paperinnovate, true, "新增成功");
	}
	
}
