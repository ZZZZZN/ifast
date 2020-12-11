package com.ifast.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.ifast.api.service.UserInfoService;
import com.ifast.api.util.JWTUtil;
import com.ifast.common.annotation.Log;
import com.ifast.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 更新用户信息
 */
@RestController
@RequestMapping("/api/userUpdate/")
public class UpdateUserController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("info")
    @Log("获取用户信息")
    @ApiOperation("获取用户信息")
    public Result<?> login(HttpServletRequest request, HttpServletResponse response) {
        String token=request.getHeader("zn-Token");
        String username= JWTUtil.getUserId(token);
        Map userInfo=userInfoService.selectUserInfoByUserName(username);
        return Result.ok(userInfo);
    }

    @PostMapping("dictInfo")
    @Log("获取用户信息")
    @ApiOperation("获取用户信息")
    public Result<?> DictInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject data) {
       /* String type=request.getParameter("type");*/
        JSONArray result=userInfoService.selectDictInfo(data.getString("type"));
        return Result.ok(result.toJSONString());
    }

    @PostMapping("detpList")
    @Log("获取部门列表")
    @ApiOperation("获取部门列表")
    public Result<?> detpList(HttpServletRequest request, HttpServletResponse response) {
        /* String type=request.getParameter("type");*/
        JSONArray result=userInfoService.selectDetpList();
        return Result.ok(result.toJSONString());
    }

}
