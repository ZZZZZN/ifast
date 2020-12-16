package com.ifast.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.ifast.api.service.AccetpDataService;
import com.ifast.common.annotation.Log;
import com.ifast.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 更新用户信息
 */
@RestController
@RequestMapping("/accept/acceptData/")
public class AcceptDataController {
    @Autowired
    private AccetpDataService accetpDataService;

    @PostMapping("accept")
    @Log("接收草料二维码数据")
    @ApiOperation("接收草料二维码数据")
    public Result<?> accept(HttpServletRequest request, HttpServletResponse response,@RequestParam Map data) {
        try {

            JSONObject info = JSONObject.parseObject(data.get("data").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime=sdf.format(new Date(Long.valueOf(info.get("add_time")+"000")));
            //String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").pase(data.get("add_time").toString());  // 获取年月日时分秒
            info.put("add_time",datetime);

            List<Map> list= (List<Map>) info.get("content");
            String record_id=info.get("record_id").toString();
            for (Map info1:list){
                info1.put("record_id",record_id);
                info1.put("title",info1.get("title").toString());
                info1.put("type",info1.get("type").toString());
                info1.put("value",info1.get("value").toString());
                info1.put("field_id",info1.get("field_id").toString());
            }
            accetpDataService.insertData(info,list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
        return Result.accetp_ok();
    }


}
