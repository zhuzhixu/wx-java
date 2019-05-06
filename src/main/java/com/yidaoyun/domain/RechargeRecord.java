/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.domain;
import com.yidaoyun.common.base.BaseEntity;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 充值记录
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
@Entity
public class RechargeRecord extends BaseEntity {

    //用户ID
    private Long userid;

    //充值时间
    private Date createdate;

    //单号
    private String ordernumber;

    //充值价钱
    private String price;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Date getCreatedate() {
		return (Date)createdate.clone();
	}

	public void setCreatedate(Date createdate) {
		this.createdate = (Date)createdate.clone();
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

    
    
}