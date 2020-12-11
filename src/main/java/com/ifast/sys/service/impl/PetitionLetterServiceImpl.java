package com.ifast.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ifast.sys.dao.PetitionLetterDao;
import com.ifast.sys.domain.PetitionLetterDO;
import com.ifast.sys.domain.PetitionLetterNewDo;
import com.ifast.sys.service.PetitionLetterService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;



import com.ifast.common.base.CoreServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2020-12-07 09:14:51 | zn</small>
 */
@Service
public class PetitionLetterServiceImpl extends CoreServiceImpl<PetitionLetterDao, PetitionLetterDO> implements PetitionLetterService {

    @Override
    public List<PetitionLetterDO> selectAllList(@Param("name") String name, @Param("deptId")String deptId) {
        return baseMapper.selectAllList(name,deptId);
    }


    @Override
    public PetitionLetterNewDo selectOne(Long id) {
        return baseMapper.selectOne(id);
    }
}
