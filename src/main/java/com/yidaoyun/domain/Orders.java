/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yidaoyun.common.base.BaseEntity;

import java.util.Date;

/**
 * 订单
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handle"})
public class Orders extends BaseEntity {

    //用户ID
    private Long userId;

    //商品id
    private Long commodityId;

    //单号
    private String ordernumber;

    //付款标记 0 未付款  1 付款
    private String flag;

    //订单价格
    private String price;

    //配送信息
    private String deliv;

    //数量
    private Integer number;

    //创建时间
    private Date createData;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDeliv() {
		return deliv;
	}

	public void setDeliv(String deliv) {
		this.deliv = deliv;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getCreateData() {
		return (Date)createData.clone();
	}

	public void setCreateData(Date createData) {
		this.createData = (Date)createData.clone();
	}
    
    
}