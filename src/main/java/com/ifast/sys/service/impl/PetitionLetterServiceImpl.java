package com.ifast.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ifast.sys.dao.PetitionLetterDao;
import com.ifast.sys.domain.InvestigationDo;
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
        return null;
    }


    @Override
    public PetitionLetterNewDo selectOne(Long id) {
        return baseMapper.selectOne(id);
    }

    /**
     * 分页查询实现类
     * @param page
     * @param para
     * @return
     */
    @Override
    public Page<PetitionLetterDO> selectPage(Page<PetitionLetterDO> page, Map para) {
        EntityWrapper wrapper = new EntityWrapper();
        SqlHelper.fillWrapper(page, wrapper);
        String name=para.get("name").toString();
        String deptId=para.get("deptId").toString();
        page.setRecords(baseMapper.selectAllList(page,name,deptId));
        return page;
    }

    @Override
    public List<PetitionLetterNewDo> selectRemind() {
        return baseMapper.selectRemind();
    }

    @Override
    public List<PetitionLetterNewDo> selectsubmit() {
        return baseMapper.selectsubmit();
    }


    @Override
    public List<InvestigationDo> selectTerm() {
        return baseMapper.selectTerm();
    }

    @Override
    public void updateTxStatus(Integer id) {
         baseMapper.updateTxStatus(id);
    }


}
