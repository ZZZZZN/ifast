package com.ifast.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ifast.api.mapper.dao.AcceptDatadao;
import com.ifast.api.service.AccetpDataService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 接收草料数据实现类
 */
@Service
@AllArgsConstructor
@Data
@Transactional(rollbackFor = Exception.class)
public class AcceptDataServiceImpl  implements AccetpDataService {


    @Autowired
    private AcceptDatadao acceptDatadao;

    /**
     * 实现输出插入方法
     * @param data
     */
    @Override
    public void insertData(JSONObject data, List info) {
        acceptDatadao.insertData(data);
        acceptDatadao.insertDataDetail(info);
    }

}
