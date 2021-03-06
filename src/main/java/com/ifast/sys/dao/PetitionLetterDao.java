package com.ifast.sys.dao;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ifast.common.base.BaseDao;
import com.ifast.sys.domain.InvestigationDo;
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

    /**
     * 查询状态为未提交提醒的信访件
     * 给提醒任务
     */
    List<PetitionLetterNewDo> selectsubmit();


    /**
     * 查询限期整改在当天还没改的负责人邮箱
     */
    List<InvestigationDo> selectTerm();

    /**
     * 根据id更新提醒状态
     * @param id
     */
     void updateTxStatus(@Param("id") Integer id);

    /**
     * 查询超时信访件
     * @return
     */
    List<PetitionLetterNewDo> selectOverDate();

}
