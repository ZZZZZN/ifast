package com.ifast.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ifast.sys.dao.ChartOperationDao;
import com.ifast.sys.domain.ChartOperationDO;
import com.ifast.sys.domain.PetitionLetterDO;
import com.ifast.sys.service.ChartOperationService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;


import com.ifast.common.base.CoreServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 挂图作战表
 * </pre>
 * <small> 2020-12-16 17:08:41 | zn</small>
 */
@Service
public class ChartOperationServiceImpl extends CoreServiceImpl<ChartOperationDao, ChartOperationDO> implements ChartOperationService {


    @Override
    public Page<ChartOperationDO> selectPage(Page<ChartOperationDO> page, Map para) {
        EntityWrapper wrapper = new EntityWrapper();
        SqlHelper.fillWrapper(page, wrapper);
        String name="";
        String dept="";
        Long id=null;
        if(para.get("name")!=null){
            name=para.get("name").toString();
        }
        if(para.get("dept")!=null){
            dept=para.get("dept").toString();
        }
        if(para.get("id")!=null){
            id=(Long) para.get("id");
        }
        page.setRecords(baseMapper.selectStringList(page,name,dept,id));
        return page;
    }
}
