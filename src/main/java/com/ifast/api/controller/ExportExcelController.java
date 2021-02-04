package com.ifast.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.ifast.api.service.AccetpDataService;
import com.ifast.api.service.ExportExcelService;
import com.ifast.common.annotation.Log;
import com.ifast.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 更新用户信息
 */
@RestController
@RequestMapping("/export/excel/")
public class ExportExcelController {
    @Autowired
    private ExportExcelService exportExcelService;

    @PostMapping("project")
    @Log("导出项目数据")
    @ApiOperation("导出项目数据")
    @ResponseBody
    public String  project(HttpServletRequest request, HttpServletResponse response,@RequestParam Map data) throws IOException {
        String url= exportExcelService.exportProject();
        return url;
    }


}
