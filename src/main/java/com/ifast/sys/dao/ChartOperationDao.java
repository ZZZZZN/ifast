package com.ifast.sys.dao;


import com.ifast.common.base.BaseDao;
import com.ifast.sys.domain.ChartOperationDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 
 * <pre>
 * 挂图作战表
 * </pre>
 * <small> 2020-12-16 17:08:41 | zn</small>
 */
public interface ChartOperationDao extends BaseDao<ChartOperationDO> {

    /**
     * 查询所有挂图作战信息，根据条件查询信息
     * @param var1
     * @param name
     * @param dept
     * @param id
     * @return
     */

    List<ChartOperationDO> selectStringList(RowBounds var1, @Param("name") String name,@Param("dept") String dept, @Param("id")Long id);


}
