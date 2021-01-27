package com.ifast.sys.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ifast.sys.dao.PatrolDao;
import com.ifast.sys.service.PatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/***
 * 巡查记录实现类
 */
@Service
@DS("slave")
public class PatrolServiceImpl  implements PatrolService {


    @Autowired
    private PatrolDao patrolDao;
    /**
     * 查询表列名
     * @return
     */
    @Override
    public JSONArray selectcol() {
        return patrolDao.selectCol();
    }

    /**
     *
     * @return
     */
    @Override
    public  Page<JSONObject> selectList(Page<JSONObject> page) {
        EntityWrapper wrapper = new EntityWrapper();
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(patrolDao.selectList(page));
        return page;
    }
}
