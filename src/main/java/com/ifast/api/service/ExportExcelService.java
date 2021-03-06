package com.ifast.api.service;


import java.io.IOException;

/**
 * 导出数据接口
 */
public interface ExportExcelService {

    /**
     * 导出项目数据
     */
    String exportProject() throws IOException;

    /**
     * 发送信访超期邮件
     */
    void sendOverDate();
}
