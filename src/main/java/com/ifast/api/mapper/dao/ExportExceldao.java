package com.ifast.api.mapper.dao;

import com.ifast.api.pojo.dto.ProjectDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


/**
 *接收草料数据dao
 */
@Repository
public interface ExportExceldao {

    /**
     * 查询项目基础信息
     * @return
     */
    ArrayList<ProjectDTO> selectProjectList();
}
