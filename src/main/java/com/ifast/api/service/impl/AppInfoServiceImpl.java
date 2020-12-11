package com.ifast.api.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.ifast.api.mapper.dao.UserInfodao;
import com.ifast.api.service.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@AllArgsConstructor
@Data
public class AppInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfodao userInfodao;

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Override
    public Map selectUserInfoByUserName(String username) {
        return userInfodao.selectUserInfoByUserName(username);
    }

    /**
     * 根据字典类型获取字典值
     * @param type
     * @return
     */
    @Override
    public JSONArray selectDictInfo(String type) {
        return userInfodao.selectDictInfo(type);
    }

    /**
     * 获取部门列表
     * @param type
     * @return
     */
    @Override
    public JSONArray selectDetpList() {
        return userInfodao.selectDetpList();
    }
}
