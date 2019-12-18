package com.jxh.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author:shenkunlin
 * @Description:Role构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="role")
public class Role implements Serializable{

	@Id
    @Column(name = "rolename")
	private String rolename;//管理者

    @Column(name = "password")
	private String password;//密码，加密存储

    @Column(name = "name")
	private String name;//真实姓名

    @Column(name = "created")
	private Date created;//创建时间

    @Column(name = "updated")
	private Date updated;//修改时间

    @Column(name = "status")
	private String status;//	使用状态（1正常 0非正常）注册默认是1

    @Column(name = "phone")
	private String phone;//注册手机号

    @Column(name = "email")
	private String email;//注册邮箱

    @Column(name = "is_mobile_check")
	private String isMobileCheck;//手机是否验证 （0否  1是）

    @Column(name = "is_email_check")
	private String isEmailCheck;//邮箱是否验证 （0否  1是）

    @Column(name = "sex")
	private String sex;//性别，1男，0女

    @Column(name = "last_login_time")
	private Date lastLoginTime;//最后登录时间



	//get方法
	public String getRolename() {
		return rolename;
	}

	//set方法
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	//get方法
	public String getPassword() {
		return password;
	}

	//set方法
	public void setPassword(String password) {
		this.password = password;
	}
	//get方法
	public String getName() {
		return name;
	}

	//set方法
	public void setName(String name) {
		this.name = name;
	}
	//get方法
	public Date getCreated() {
		return created;
	}

	//set方法
	public void setCreated(Date created) {
		this.created = created;
	}
	//get方法
	public Date getUpdated() {
		return updated;
	}

	//set方法
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	//get方法
	public String getStatus() {
		return status;
	}

	//set方法
	public void setStatus(String status) {
		this.status = status;
	}
	//get方法
	public String getPhone() {
		return phone;
	}

	//set方法
	public void setPhone(String phone) {
		this.phone = phone;
	}
	//get方法
	public String getEmail() {
		return email;
	}

	//set方法
	public void setEmail(String email) {
		this.email = email;
	}
	//get方法
	public String getIsMobileCheck() {
		return isMobileCheck;
	}

	//set方法
	public void setIsMobileCheck(String isMobileCheck) {
		this.isMobileCheck = isMobileCheck;
	}
	//get方法
	public String getIsEmailCheck() {
		return isEmailCheck;
	}

	//set方法
	public void setIsEmailCheck(String isEmailCheck) {
		this.isEmailCheck = isEmailCheck;
	}
	//get方法
	public String getSex() {
		return sex;
	}

	//set方法
	public void setSex(String sex) {
		this.sex = sex;
	}
	//get方法
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	//set方法
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


}
