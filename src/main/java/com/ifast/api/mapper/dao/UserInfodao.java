package com.ifast.api.mapper.dao;

import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 *用户信息dao
 */
@Repository
public interface UserInfodao{

    /**
     * 通过username获取用户信息
     * @param username
     * @return
     */
    Map selectUserInfoByUserName(@Param("username") String username);

    /**
     * 根据字典类型获取字典值
     * @param type
     * @return
     */
    JSONArray selectDictInfo(@Param("type") String type);

    /**
     * 获取部门列表
     * @param type
     * @return
     */
    JSONArray selectDetpList();
}
