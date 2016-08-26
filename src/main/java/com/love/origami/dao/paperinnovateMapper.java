package com.love.origami.dao;

import java.util.List;

import com.love.origami.model.Paperinnovate;



public interface paperinnovateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Paperinnovate record);

    int insertSelective(Paperinnovate record);

    Paperinnovate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Paperinnovate record);

    int updateByPrimaryKey(Paperinnovate record);
    
    int isDup(Paperinnovate record);
    
    List<Paperinnovate> selectByCondition(Paperinnovate paperinnovate);
    
    int searchCount(Paperinnovate paperinnovate);
    
    Paperinnovate getById(Integer id);
}