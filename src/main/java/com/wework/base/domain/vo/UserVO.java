package com.wework.base.domain.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value="userVO", description="用户对象")
public class UserVO implements Serializable {

    private Long userId;
    private Long parentId;
    private String userName;
    private Long userType;
    private String idNumber;
    private Long gender;
    private String passportNo;
    private String region;
    private String birthday;
    private String blod;
    private String education;
    private String hobby;
    private String invitationCode;
    private String appId;
    private String phone;
    private BigDecimal walletBalance;
    private Date createTime;
    private Long isDel;
    private String province;
    private String city;
    private String country;
    private String nickName;
    private String avatarUrl;
    private String openId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBlod() {
        return blod;
    }

    public void setBlod(String blod) {
        this.blod = blod;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(BigDecimal walletBalance) {
        this.walletBalance = walletBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getIsDel() {
        return isDel;
    }

    public void setIsDel(Long isDel) {
        this.isDel = isDel;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOpenid() {
        return openId;
    }

    public void setOpenid(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "UserPO{" +
                "userId=" + userId +
                ", parentId=" + parentId +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", idNumber='" + idNumber + '\'' +
                ", gender=" + gender +
                ", passportNo='" + passportNo + '\'' +
                ", region='" + region + '\'' +
                ", birthday='" + birthday + '\'' +
                ", blod='" + blod + '\'' +
                ", education='" + education + '\'' +
                ", hobby='" + hobby + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                ", appId='" + appId + '\'' +
                ", phone='" + phone + '\'' +
                ", walletBalance=" + walletBalance +
                ", createTime=" + createTime +
                ", isDel=" + isDel +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", openid='" + openId + '\'' +
                '}';
    }
}
