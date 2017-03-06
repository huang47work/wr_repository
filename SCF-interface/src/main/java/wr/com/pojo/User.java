package wr.com.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String userId;

    private String userName;

    private String name;

    private String password;

    private String address;

    private String phone;

    private String email;

    private String orgId;

    private String type;

    private String commissionStarTime;

    private Double balance;

    private Integer role;

    private String sex;

    private Date hiredate;

    private Date workTime;

    private String educationBackground;

    private String jobNumber;

    private String graduateSchool;

    private String major;

    private String idcardFrontPicUrl;

    private String idcardOppositePicUrl;

    private Integer workStatus;

    private String position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCommissionStarTime() {
        return commissionStarTime;
    }

    public void setCommissionStarTime(String commissionStarTime) {
        this.commissionStarTime = commissionStarTime == null ? null : commissionStarTime.trim();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground == null ? null : educationBackground.trim();
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber == null ? null : jobNumber.trim();
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool == null ? null : graduateSchool.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getIdcardFrontPicUrl() {
        return idcardFrontPicUrl;
    }

    public void setIdcardFrontPicUrl(String idcardFrontPicUrl) {
        this.idcardFrontPicUrl = idcardFrontPicUrl == null ? null : idcardFrontPicUrl.trim();
    }

    public String getIdcardOppositePicUrl() {
        return idcardOppositePicUrl;
    }

    public void setIdcardOppositePicUrl(String idcardOppositePicUrl) {
        this.idcardOppositePicUrl = idcardOppositePicUrl == null ? null : idcardOppositePicUrl.trim();
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", userName=" + userName + ", name=" + name + ", password="
				+ password + ", address=" + address + ", phone=" + phone + ", email=" + email + ", orgId=" + orgId
				+ ", type=" + type + ", commissionStarTime=" + commissionStarTime + ", balance=" + balance + ", role="
				+ role + ", sex=" + sex + ", hiredate=" + hiredate + ", workTime=" + workTime + ", educationBackground="
				+ educationBackground + ", jobNumber=" + jobNumber + ", graduateSchool=" + graduateSchool + ", major="
				+ major + ", idcardFrontPicUrl=" + idcardFrontPicUrl + ", idcardOppositePicUrl=" + idcardOppositePicUrl
				+ ", workStatus=" + workStatus + ", position=" + position + "]";
	}
    
}