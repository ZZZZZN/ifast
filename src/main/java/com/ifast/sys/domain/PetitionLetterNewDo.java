package com.ifast.sys.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;
import java.util.Date;

public class PetitionLetterNewDo implements Serializable {

    private static final long serialVersionUID = 1L;


    public Long id;

    /** 信访来源 */
    public Integer source;

    public String sourcepetition;

    /** 信访时间 */
    public Date petitiontime;

    /** 信访标题 */
    public String lettertitle;

    /** 信访内容 */
    public String content;

    /** 收文编号 */
    public String receiptno;

    /** 交办科室 */

    public String deptno;


    public String deptname;

    /** 交办人 */
    public String receiver;

    /** 处理状态（0：未处理 1：已处理） */
    public Integer status;

    /** 收文时间 */
    public Date receivetime;

    /** 规定回复时间 */
    public Date processtime;

    /** 实际回复时间 */
    public Date actualreplytime;

    /** 提醒时间 */
    public Date remindertime;

    /** 是否收回 */
    public Integer isrecover;

    /** 收回时间 */
    public Date recovertime;

    /** 备注 */
    public String remark;

    /** 逻辑删除（0：不删除 ，1：逻辑删除） */
    public Integer delflag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getSourcepetition() {
        return sourcepetition;
    }

    public void setSourcepetition(String sourcepetition) {
        this.sourcepetition = sourcepetition;
    }

    public Date getPetitiontime() {
        return petitiontime;
    }

    public void setPetitiontime(Date petitiontime) {
        this.petitiontime = petitiontime;
    }

    public String getLettertitle() {
        return lettertitle;
    }

    public void setLettertitle(String lettertitle) {
        this.lettertitle = lettertitle;
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


    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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

    public Date getRemindertime() {
        return remindertime;
    }

    public void setRemindertime(Date remindertime) {
        this.remindertime = remindertime;
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
}
