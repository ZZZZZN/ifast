package com.ifast.api.controller;

import com.ifast.api.service.ExportExcelService;
import com.ifast.common.annotation.Log;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 更新用户信息
 */
@RestController
@RequestMapping("/XF/mail/")
public class XfMailController {
    @Autowired
    private ExportExcelService exportExcelService;

    @PostMapping("sendOverDate")
    @Log("发送信访超期邮件")
    @ApiOperation("发送信访超期邮件")
    @ResponseBody
    public void  sendOverDate(HttpServletRequest request, HttpServletResponse response,@RequestParam Map data) throws IOException {
        exportExcelService.sendOverDate();
        return;
    }


}
