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
 * 
 * </pre>
 * <small> 2020-12-07 16:06:25 | zn</small>
 */
@Data
@SuppressWarnings("serial")
@TableName("sys_petition_letter")
//@EqualsAndHashCode(callSuper=true)

public class PetitionLetterDO implements Serializable {
    private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

    /** 信访来源 */
    private Integer sourcepetition;

    private String sourcePetition;

    /** 信访时间 */
    private Date petitiontime;

//    /** 信访标题 */
//    private String lettertitle;

    /** 信访内容 */
    private String content;

    /** 收文编号 */
    private String receiptno;

    /** 交办科室 */
    @TableField(exist = false)
    private Integer servicedept;

    @TableField(exist = false)
    private String serviceDept;

    @TableField(exist = false)
    private String serviceDeptNO;

    @TableField(exist = false)
    private String serviceDeptName;

//    /** 交办人 */
//    private String receiver;

    /** 处理状态（0：未处理 1：已处理） */
    private Integer status;

    /** 收文时间 */
    private Date receivetime;

    /** 规定回复时间 */
    private Date processtime;

    /** 实际回复时间 */
    private Date actualreplytime;
//
//    /** 提醒时间 */
//    private Date remindertime;

    /** 上传受理告知书时间 */
    private Date acceptancetime;

    /** 是否收回 */
    private Integer isrecover;

    /** 收回时间 */
    private Date recovertime;

    /** 备注 */
    private String remark;

    /** 逻辑删除（0：不删除 ，1：逻辑删除） */
    private Integer delflag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSourcepetition() {
        return sourcepetition;
    }

    public void setSourcepetition(Integer sourcepetition) {
        this.sourcepetition = sourcepetition;
    }

    public Date getPetitiontime() {
        return petitiontime;
    }

    public void setPetitiontime(Date petitiontime) {
        this.petitiontime = petitiontime;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiptno() {
        return receiptno;
    }

    public void setReceiptno(String receiptno) {
        this.receiptno = receiptno;
    }

    public Integer getServicedept() {
        return servicedept;
    }

    public void setServicedept(Integer servicedept) {
        this.servicedept = servicedept;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }

    public Date getProcesstime() {
        return processtime;
    }

    public void setProcesstime(Date processtime) {
        this.processtime = processtime;
    }

    public Date getActualreplytime() {
        return actualreplytime;
    }

    public void setActualreplytime(Date actualreplytime) {
        this.actualreplytime = actualreplytime;
    }



    public Integer getIsrecover() {
        return isrecover;
    }

    public void setIsrecover(Integer isrecover) {
        this.isrecover = isrecover;
    }

    public Date getRecovertime() {
        return recovertime;
    }

    public void setRecovertime(Date recovertime) {
        this.recovertime = recovertime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public String getSourcePetition() {
        return sourcePetition;
    }

    public void setSourcePetition(String sourcePetition) {
        this.sourcePetition = sourcePetition;
    }

    public String getServiceDept() {
        return serviceDept;
    }

    public String getServiceDeptNO() {
        return serviceDeptNO;
    }

    public void setServiceDeptNO(String serviceDeptNO) {
        this.serviceDeptNO = serviceDeptNO;
    }

    public String getServiceDeptName() {
        return serviceDeptName;
    }

    public void setServiceDeptName(String serviceDeptName) {
        this.serviceDeptName = serviceDeptName;
    }

    public void setServiceDept(String serviceDept) {
        this.serviceDept = serviceDept;
    }

    public Date getAcceptancetime() {
        return acceptancetime;
    }

    public void setAcceptancetime(Date acceptancetime) {
        this.acceptancetime = acceptancetime;
    }
}
