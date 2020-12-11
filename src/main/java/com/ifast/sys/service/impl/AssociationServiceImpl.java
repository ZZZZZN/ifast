package com.ifast.sys.service.impl;

import com.ifast.sys.dao.AssociationDao;
import com.ifast.sys.domain.AssociationDO;
import com.ifast.sys.service.AssociationService;
import org.springframework.stereotype.Service;

import com.ifast.common.base.CoreServiceImpl;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2020-12-09 16:55:03 | zn</small>
 */
@Service
public class AssociationServiceImpl extends CoreServiceImpl<AssociationDao, AssociationDO> implements AssociationService {
    @Override
    public boolean deleteAll(Long petitionid) {
        return baseMapper.deleteAll(petitionid);
    }
}
