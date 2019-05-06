/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yidaoyun.common.base.BaseEntity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
public class User extends BaseEntity {

    //用户名
	@ApiModelProperty("用户名")
    private String username ;

    //密码
	@ApiModelProperty("密码")
    private String pasword;

    @Override
	public String toString() {
		return "User [username=" + username + ", pasword=" + pasword + ", name=" + name + ", cardnumber=" + cardnumber
				+ ", phone=" + phone + ", shippingaddresshome=" + shippingaddresshome + ", shippingaddresscompany="
				+ shippingaddresscompany + ", balance=" + balance + ", flag=" + flag + "]";
	}

	//姓名
	@ApiModelProperty("姓名")
    private String name;

    //会员卡号
	@ApiModelProperty("会员卡号")
    private String cardnumber;

    //电话
	@ApiModelProperty("电话")
    private String phone;

    //收货地址(家)
	@ApiModelProperty("收货地址(家)")
    private String shippingaddresshome;

    //收货地址(公司)
	@ApiModelProperty("收货地址(公司)")
    private String shippingaddresscompany;

    //余额
	@ApiModelProperty("余额")
    private String balance;
    
	//用户标识
	@ApiModelProperty("用户标识")
    private Integer flag;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getShippingaddresshome() {
		return shippingaddresshome;
	}

	public void setShippingaddresshome(String shippingaddresshome) {
		this.shippingaddresshome = shippingaddresshome;
	}

	public String getShippingaddresscompany() {
		return shippingaddresscompany;
	}

	public void setShippingaddresscompany(String shippingaddresscompany) {
		this.shippingaddresscompany = shippingaddresscompany;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
    
	
}