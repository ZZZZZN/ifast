package com.ifast.sys.service;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ifast.common.base.CoreService;
import com.ifast.sys.domain.InvestigationDo;
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
public interface PetitionLetterService  extends CoreService<PetitionLetterDO>{

    /**
     * 查询信访列表
     *
     */
    List<PetitionLetterDO> selectAllList( String name,String deptId);

    /**
     * 用id查询出信息
     */
    PetitionLetterNewDo selectOne(Long id);

    /**
     * 分页查询
     * @param page
     * @param para
     * @return
     */
    Page<PetitionLetterDO> selectPage(Page<PetitionLetterDO> page,Map para);
    /**
     * 为定时任务做查询
     */
    List<PetitionLetterNewDo> selectRemind();
    /**
     * 超时未处理
     */
    List<PetitionLetterNewDo> selectOverDate();


    /**
     * 已提交未提醒信访件查询
     */
    List<PetitionLetterNewDo> selectsubmit();


    /**
     * 查询限期整改在当天还没改的负责人邮箱
     */
    List<InvestigationDo> selectTerm();

    /**
     * 根据ID更新提醒状态
     * @param id
     */
    void updateTxStatus(Integer id);


}
