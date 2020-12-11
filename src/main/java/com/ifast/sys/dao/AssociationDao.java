package com.ifast.sys.dao;


import com.ifast.common.base.BaseDao;
import com.ifast.sys.domain.AssociationDO;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2020-12-09 16:55:03 | zn</small>
 */
public interface AssociationDao extends BaseDao<AssociationDO> {

    boolean deleteAll(Long petitionid);

}
