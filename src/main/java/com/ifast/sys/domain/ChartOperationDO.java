package com.ifast.sys.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ifast.common.base.BaseDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * <pre>
 * 挂图作战表
 * </pre>
 * <small> 2020-12-16 17:08:41 | zn</small>
 */
@Data
@SuppressWarnings("serial")
@TableName("wall_chart_operation")
//@EqualsAndHashCode(callSuper=true)
public class ChartOperationDO implements Serializable {
    private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

    /** 项目名称 */
    private String projectname;

    /** 施工单位、项目负责人、联系电话 */
    private String constructionunit;

    /** 项目地址 */
    private String projectaddress;

    /** 存在安全隐患 */
    private String existisecurity;

    /** 整改措施 */
    private String measures;

    /** 整改时间 */
    private Date rectificationtime;

    /** 整改销案情况(0：未销案  1：已销案) */
    private Integer cancellation;

    @TableField(exist = false)
    /**  整改销案情况String 类型字段    */
    private String  cancellationString;

    /** 主要领导 */
    private String mainleader;

    /** 分管领导及电话 */
    private String leadercharge;

    /** 整改科室负责人及电话 */
    private String department;

    /** 整改监督组负责人及电话 */
    private String supervise;

    /** 逻辑删除(0:未删除  1:逻辑删除) */
    private Integer delflag;

}
