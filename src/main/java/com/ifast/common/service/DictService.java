package com.ifast.common.service;

import java.util.List;

import com.ifast.common.base.CoreService;
import com.ifast.common.domain.DictDO;
import com.ifast.sys.domain.UserDO;

/**
 * <pre>
 * 数据字典
 * </pre>
 * 
 * <small> 2018年1月3日 | Aron</small>
 */
public interface DictService extends CoreService<DictDO> {
    
    List<DictDO> listType();

    String getName(String type, String value);

    /**
     * 获取爱好列表
     * 
     * @return
     * @param userDO
     */
    List<DictDO> getHobbyList(UserDO userDO);

    /**
     * 获取性别列表
     * 
     * @return
     */
    List<DictDO> getSexList();

    /**
     * 获取职级列表
     * @return
     */
    List<DictDO> selectRankList();

    /**
     * 获取民族列表
     * @return
     */
    List<DictDO> selectNationList();

    /**
     * 获取政治面貌列表
     * @return
     */
    List<DictDO> selectpoliticalList();

    /**
     * 获取编制所在单位列表
     * @return
     */
    List<DictDO> selectbzszdwList();

    /**
     * 获取编制类别列表
     * @return
     */
    List<DictDO> selectbzlbList();

    /**
     * 获取最高学历列表
     * @return
     */
    List<DictDO> selecteducation_topList();

    /**
     * 获取最高学历类型列表
     * @return
     */
    List<DictDO> selecteducation_typeList();

    /**
     * 获取最高学位列表
     * @return
     */
    List<DictDO> selectdegree_topList();

    /**
     * 获取进入单位形式列表
     * @return
     */
    List<DictDO> selectjrdwxsList();
}
