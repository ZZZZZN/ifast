package com.ifast.api.mapper.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 *接收草料数据dao
 */
@Repository
public interface AcceptDatadao {

    void insertData(JSONObject data);

    void insertDataDetail(List list);
}
