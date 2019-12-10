package com.jxh.pojo;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
	private Integer roleid;//管理者id

    @Column(name = "rolename")
	private String rolename;//角色名字

    @Column(name = "password")
	private String password;//密码，加密

    @Column(name = "createtime")
	private Date createtime;//创建时间

    @Column(name = "updatetime")
	private Date updatetime;//更改时间

    @Column(name = "status")
	private String status;//状态



	//get方法
	public Integer getRoleid() {
		return roleid;
	}

	//set方法
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
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
	public Date getCreatetime() {
		return createtime;
	}

	//set方法
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	//get方法
	public Date getUpdatetime() {
		return updatetime;
	}

	//set方法
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	//get方法
	public String getStatus() {
		return status;
	}

	//set方法
	public void setStatus(String status) {
		this.status = status;
	}


}
