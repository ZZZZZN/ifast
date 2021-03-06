package com.ifast.sys.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
@TableName("sys_user")
public class UserDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    // 用户真实姓名
    private String name;
    // 用户名
    private String username;
    // 密码
    private String password;
    // salt
    private String salt;
    // 部门
    private Long deptId;
    @TableField(exist = false)
    private String deptName;
    // 邮箱
    private String email;
    // 手机号
    private String mobile;
    // 状态 0:禁用，1:正常
    private Integer status;
    // 创建用户id5x5x
    private Long userIdCreate;
    // 创建时间
    private Date gmtCreate;
    // 修改时间
    private Date gmtModified;
    //角色
    @TableField(exist = false)
    private List<Long> roleIds;
    //性别
    private Long sex;
    @TableField(exist = false)
    private String sexName;
    //出身日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    //图片ID
    private Long picId;
    //现居住地
    private String liveAddress;
    //爱好
    private String hobby;
    //省份
    private String province;
    //所在城市
    private String city;
    //所在地区
    private String district;
    //职级
    private String rank;
    //民族
    private String nation;
    //政治面貌
    private String political;
    //身份证号
    private String idnumber;
    //编制所在单位
    private String bzszdw;
    //编制类别
    private String bzlb;
    //人员类别
    private String ryzt;
    //进入单位形式
    private String jrdwxs;
    //参加工作时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date jobStart;
    //进入单位时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date jrdwsj;
    //最高学历
    private String educationTop;
    //最高学历类型
    private String educationType;
    //毕业学校
    private String school;
    //最高学位
    private String degreeTop;
    //毕业时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date graduationTime;
    //专业
    private String major;
    //职称名称
    private String title;
    //职称取得时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date titleTime;
    //职称等级
    private String titleLeve;

    public UserDO() {
    }


    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 设置：邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：状态 0:禁用，1:正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态 0:禁用，1:正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：创建用户id
     */
    public void setUserIdCreate(Long userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    /**
     * 获取：创建用户id
     */
    public Long getUserIdCreate() {
        return userIdCreate;
    }

    /**
     * 设置：创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取：创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置：修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取：修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    public List<Long> getroleIds() {
        return roleIds;
    }

    public void setroleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getBzszdw() {
        return bzszdw;
    }

    public void setBzszdw(String bzszdw) {
        this.bzszdw = bzszdw;
    }

    public String getBzlb() {
        return bzlb;
    }

    public void setBzlb(String bzlb) {
        this.bzlb = bzlb;
    }

    public String getRyzt() {
        return ryzt;
    }

    public void setRyzt(String ryzt) {
        this.ryzt = ryzt;
    }

    public String getJrdwxs() {
        return jrdwxs;
    }

    public void setJrdwxs(String jrdwxs) {
        this.jrdwxs = jrdwxs;
    }


    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public Date getJrdwsj() {
        return jrdwsj;
    }

    public void setJrdwsj(Date jrdwsj) {
        this.jrdwsj = jrdwsj;
    }








    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }








    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getJobStart() {
        return jobStart;
    }

    public void setJobStart(Date jobStart) {
        this.jobStart = jobStart;
    }

    public String getEducationTop() {
        return educationTop;
    }

    public void setEducationTop(String educationTop) {
        this.educationTop = educationTop;
    }

    public String getEducationType() {
        return educationType;
    }

    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

    public String getDegreeTop() {
        return degreeTop;
    }

    public void setDegreeTop(String degreeTop) {
        this.degreeTop = degreeTop;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    public Date getTitleTime() {
        return titleTime;
    }

    public void setTitleTime(Date titleTime) {
        this.titleTime = titleTime;
    }

    public String getTitleLeve() {
        return titleLeve;
    }

    public void setTitleLeve(String titleLeve) {
        this.titleLeve = titleLeve;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", roleIds=" + roleIds +
                ", sex=" + sex +
                ", birth=" + birth +
                ", picId=" + picId +
                ", liveAddress='" + liveAddress + '\'' +
                ", hobby='" + hobby + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", rank='" + rank + '\'' +
                ", nation='" + nation + '\'' +
                ", political='" + political + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", bzszdw='" + bzszdw + '\'' +
                ", bzlb='" + bzlb + '\'' +
                ", ryzt='" + ryzt + '\'' +
                ", jrdwxs='" + jrdwxs + '\'' +
                ", jobStart=" + jobStart +
                ", jrdwsj=" + jrdwsj +
                ", educationTop='" + educationTop + '\'' +
                ", educationType='" + educationType + '\'' +
                ", school='" + school + '\'' +
                ", degreeTop='" + degreeTop + '\'' +
                ", graduationTime=" + graduationTime +
                ", major='" + major + '\'' +
                ", title='" + title + '\'' +
                ", titleTime=" + titleTime +
                ", titleLeve='" + titleLeve + '\'' +
                '}';
    }
}
