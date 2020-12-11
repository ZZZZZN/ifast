package com.ifast.sys.service;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ifast.common.base.CoreService;
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
public interface PetitionLetterService extends CoreService<PetitionLetterDO> {

    /**
     * 查询信访列表
     *
     */
    List<PetitionLetterDO> selectAllList(@Param("name") String name, @Param("deptId")String deptId);

    /**
     * 用id查询出信息
     */
    PetitionLetterNewDo selectOne(@Param("name")Long id);


}
