package com.ifast.api.service;

import com.alibaba.fastjson.JSONArray;
import com.ifast.common.base.CoreService;
import com.ifast.sys.domain.UserDO;

import java.util.Map;

/**
 * 用户信息service
 */
public interface UserInfoService  {
    /** 根据用户名获取用户信息*/
    Map selectUserInfoByUserName(String username);

    /**
     * 根据字典类型获取字典值
     * @param type
     * @return
     */
    JSONArray selectDictInfo(String type);

    /**
     * 获取部门列表
     * @param type
     * @return
     */
    JSONArray selectDetpList();

}
