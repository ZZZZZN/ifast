package com.ifast.api.service;



import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 接收草料数据接口
 */
public interface AccetpDataService {
    /**
     * 插入接收数据
     * @param data
     */
       void insertData(JSONObject data, List info);

}
