package com.love.origami.dao;

import com.love.origami.model.Paperinnovate;

public interface PaperinnovateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ch_paper_innovate
     *
     * @mbggenerated Fri Sep 02 17:37:14 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ch_paper_innovate
     *
     * @mbggenerated Fri Sep 02 17:37:14 CST 2016
     */
    int insert(Paperinnovate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ch_paper_innovate
     *
     * @mbggenerated Fri Sep 02 17:37:14 CST 2016
     */
    int insertSelective(Paperinnovate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ch_paper_innovate
     *
     * @mbggenerated Fri Sep 02 17:37:14 CST 2016
     */
    Paperinnovate selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ch_paper_innovate
     *
     * @mbggenerated Fri Sep 02 17:37:14 CST 2016
     */
    int updateByPrimaryKeySelective(Paperinnovate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ch_paper_innovate
     *
     * @mbggenerated Fri Sep 02 17:37:14 CST 2016
     */
    int updateByPrimaryKeyWithBLOBs(Paperinnovate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ch_paper_innovate
     *
     * @mbggenerated Fri Sep 02 17:37:14 CST 2016
     */
    int updateByPrimaryKey(Paperinnovate record);
}