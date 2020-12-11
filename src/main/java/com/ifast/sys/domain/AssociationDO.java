package com.ifast.sys.domain;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ifast.common.base.BaseDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * <pre>
 * 
 * </pre>
 * <small> 2020-12-09 16:55:03 | zn</small>
 */
@Data
@SuppressWarnings("serial")
@TableName("sys_association")
//@EqualsAndHashCode(callSuper=true)
public class AssociationDO  {
	@TableId
	private Long id;

    /** 信访主键 */
    private Long petitionid;

    /** 送达科室 */
    private Integer depid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPetitionid() {
        return petitionid;
    }

    public void setPetitionid(Long petitionid) {
        this.petitionid = petitionid;
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }


}
