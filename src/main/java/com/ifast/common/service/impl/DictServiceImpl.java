package com.ifast.common.service.impl;

import com.ifast.common.base.CoreServiceImpl;
import com.ifast.common.dao.DictDao;
import com.ifast.common.domain.DictDO;
import com.ifast.common.service.DictService;
import com.ifast.sys.domain.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <pre>
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
@Service
public class DictServiceImpl extends CoreServiceImpl<DictDao, DictDO> implements DictService {

    @Override
    public List<DictDO> listType() {
        return baseMapper.listType();
    }

    @Override
    public String getName(String type, String value) {
        DictDO selectOne = DictDO.dao.selectOne("type = {0} and value = {1}", type, value);
        return selectOne == null ? "" : selectOne.getName();
    }

    @Override
    public List<DictDO> getHobbyList(UserDO userDO) {
        List<DictDO> hobbyList = DictDO.dao.selectList("type = {0}", "hobby");

        if (StringUtils.isNotEmpty(userDO.getHobby())) {
            String userHobbys[] = userDO.getHobby().split(";");
            for (String userHobby : userHobbys) {
                for (DictDO hobby : hobbyList) {
                    if (!Objects.equals(userHobby, hobby.getId().toString())) {
                        continue;
                    }
                    hobby.setRemarks("true");
                    break;
                }
            }
        }

        return hobbyList;
    }

    @Override
    public List<DictDO> getSexList() {
        return DictDO.dao.selectList("type = {0}", "sex");
    }

    /**
     * 获取职级列表
     * @return
     */
    @Override
    public List<DictDO> selectRankList() {
        return DictDO.dao.selectList("type = {0}", "rank");
    }

    /**
     * 获取民族列表
     * @return
     */
    @Override
    public List<DictDO> selectNationList() {
        return DictDO.dao.selectList("type = {0}", "nation");
    }

    /**
     * 获取政治面貌列表
     * @return
     */
    @Override
    public List<DictDO> selectpoliticalList() {
        return DictDO.dao.selectList("type = {0}", "political");
    }

    /**
     * 获取编制所在单位列表
     * @return
     */
    @Override
    public List<DictDO> selectbzszdwList() {
        return DictDO.dao.selectList("type = {0}", "bzszdw");
    }

    /**
     * 获取编制类别列表
     * @return
     */
    @Override
    public List<DictDO> selectbzlbList() {
        return DictDO.dao.selectList("type = {0}", "bzlb");
    }

    /**
     * 获取人员状态列表
     * @return
     */
    @Override
    public List<DictDO> selecteducation_topList() {
        return DictDO.dao.selectList("type = {0}", "education_top");
    }

    /**
     * 获取最高学历类型
     * @return
     */
    @Override
    public List<DictDO> selecteducation_typeList() {
        return DictDO.dao.selectList("type = {0}", "education_type");
    }

    /**
     * 获取最高学位列表
     * @return
     */
    @Override
    public List<DictDO> selectdegree_topList() {
        return DictDO.dao.selectList("type = {0}", "degree_top");
    }

    /**
     * 获取进入单位形式列表
     * @return
     */
    @Override
    public List<DictDO> selectjrdwxsList() {
        return DictDO.dao.selectList("type = {0}", "jrdwxs");
    }

}
