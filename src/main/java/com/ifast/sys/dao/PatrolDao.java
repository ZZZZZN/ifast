package com.ifast.sys.dao;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 巡查记录DAO
 */
public interface PatrolDao  {

    /**
     * 查询列表数据
     * @return
     */
    List<JSONObject> selectList(RowBounds var1);

    /**
     * 查询列名
     * @return
     */
    JSONArray selectCol();
}
