package com.ifast.sys.dao;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ifast.common.base.BaseDao;
import com.ifast.sys.domain.PetitionLetterDO;
import com.ifast.sys.domain.PetitionLetterNewDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2020-12-07 09:14:51 | zn</small>
 */
public interface PetitionLetterDao extends BaseDao<PetitionLetterDO> {

    List<PetitionLetterDO> selectAllList(@Param("name") String name, @Param("deptId")String deptId);

    /**
     *根据id查询出一条数据
     * @param id
     * @return
     */
    PetitionLetterNewDo selectOne(Long id);




}
