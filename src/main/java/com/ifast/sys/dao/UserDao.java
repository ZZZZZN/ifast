package com.ifast.sys.dao;

import com.ifast.common.base.BaseDao;
import com.ifast.sys.domain.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <pre>
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
public interface UserDao extends BaseDao<UserDO> {
	
	Long[] listAllDept();

	List<UserDO> exportUser(@Param("name") String name, @Param("deptId")String deptId);

}
