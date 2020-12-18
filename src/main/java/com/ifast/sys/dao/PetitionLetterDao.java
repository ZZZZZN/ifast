package com.ifast.sys.dao;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ifast.common.base.BaseDao;
import com.ifast.sys.domain.PetitionLetterDO;
import com.ifast.sys.domain.PetitionLetterNewDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

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

    List<PetitionLetterDO> selectAllList(RowBounds var1, @Param("name") String name, @Param("deptId")String deptId);

    /**
     *根据id关联查询信访信息表
     * @param id
     * @return
     */
    PetitionLetterNewDo selectOne(Long id);

    /**
     * 查询状态为定：未处理，没有逻辑删除的信访件信息
     * 给提醒任务
     */
    List<PetitionLetterNewDo> selectRemind();


}
