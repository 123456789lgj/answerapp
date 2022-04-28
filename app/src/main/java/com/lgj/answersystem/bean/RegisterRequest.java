package com.lgj.answersystem.bean;


public class RegisterRequest {

//    @ApiModelProperty(value = "密码")
    private String password;

//    @ApiModelProperty(value = "手机号码")
    private String phone;


//    @ApiModelProperty(value = "验证码")
    private String authCode;

//    @ApiModelProperty(value = "昵称")
    private String nickname;


//    @ApiModelProperty(value = "设备id")
    private String deviceId;

//    @ApiModelProperty(value = "性别:0没有设置;1为男性;2为女性")
    private Integer sex;

//    @ApiModelProperty(value = "年龄")
    private Integer age;

//    @ApiModelProperty(value = "院校/公司名称")
    private String company;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
