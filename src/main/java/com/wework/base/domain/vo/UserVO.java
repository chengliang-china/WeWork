package com.wework.base.domain.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value="user", description="用户对象")
public class UserVO implements Serializable {

    private Long userId;
    private Long parentId;
    private String userName;
    private Long userType;
    private String idNumber;
    private Long sex;
    private String passportNo;
    private String region;
    private String birthday;
    private String blod;
    private String education;
    private String hobby;
    private String invitationCode;
    private String appId;
    private String appSecret;
    private String phone;
    private BigDecimal walletBalance;
    private Date createTime;
    private Long isDel;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }


    public void setSex(Long sex) {
        this.sex = sex;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setBlod(String blod) {
        this.blod = blod;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWalletBalance(BigDecimal walletBalance) {
        this.walletBalance = walletBalance;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setIsDel(Long isDel) {
        this.isDel = isDel;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getUserName() {
        return userName;
    }

    public Long getUserType() {
        return userType;
    }


    public Long getSex() {
        return sex;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public String getRegion() {
        return region;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBlod() {
        return blod;
    }

    public String getEducation() {
        return education;
    }

    public String getHobby() {
        return hobby;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getPhone() {
        return phone;
    }

    public BigDecimal getWalletBalance() {
        return walletBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Long getIsDel() {
        return isDel;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userId=" + userId +
                ", parentId=" + parentId +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", idNumber='" + idNumber + '\'' +
                ", sex=" + sex +
                ", passportNo='" + passportNo + '\'' +
                ", region='" + region + '\'' +
                ", birthday='" + birthday + '\'' +
                ", blod='" + blod + '\'' +
                ", education='" + education + '\'' +
                ", hobby='" + hobby + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                ", appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", phone='" + phone + '\'' +
                ", walletBalance=" + walletBalance +
                ", createTime=" + createTime +
                ", isDel=" + isDel +
                '}';
    }
}
