package com.ifast.sys.service;


import com.ifast.common.base.CoreService;
import com.ifast.sys.domain.AssociationDO;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2020-12-09 16:55:03 | zn</small>
 */
public interface AssociationService extends CoreService<AssociationDO> {

    boolean deleteAll(@Param("petitionid")Long petitionid);
    
}
