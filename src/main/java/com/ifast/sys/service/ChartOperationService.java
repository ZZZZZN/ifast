package com.ifast.sys.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.ifast.common.base.CoreService;
import com.ifast.sys.domain.ChartOperationDO;
import com.ifast.sys.domain.PetitionLetterDO;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 挂图作战表
 * </pre>
 * <small> 2020-12-16 17:08:41 | zn</small>
 */
public interface ChartOperationService extends CoreService<ChartOperationDO> {

    /**
     * 分页查询
     * @param page
     * @param para
     * @return
     */
    Page<ChartOperationDO> selectPage(Page<ChartOperationDO> page, Map para);




}
