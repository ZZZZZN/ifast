package com.ifast.sys.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.ifast.sys.domain.PetitionLetterDO;


/***
 * 巡检自定义表头service
 */
public interface PatrolService{

    /**
     * 根据表名查询字段名称
     */
    JSONArray selectcol();

    /**
     * 查询表数据
     * @return
     */
    Page<JSONObject> selectList(Page<JSONObject> page);
}
