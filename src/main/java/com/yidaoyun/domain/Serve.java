/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.domain;

import javax.persistence.Entity;

import com.yidaoyun.common.base.BaseEntity;

/**
 * 售后服务
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */

@Entity
public class Serve extends BaseEntity {

    //服务项目ID
    private Long serveTypeId;

    //天
    private String dTime;

    //时分秒
    private String hTime;

    //备注
    private String message;

	public Long getServeTypeId() {
		return serveTypeId;
	}

	public void setServeTypeId(Long serveTypeId) {
		this.serveTypeId = serveTypeId;
	}

	public String getdTime() {
		return dTime;
	}

	public void setdTime(String dTime) {
		this.dTime = dTime;
	}

	public String gethTime() {
		return hTime;
	}

	public void sethTime(String hTime) {
		this.hTime = hTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    

}