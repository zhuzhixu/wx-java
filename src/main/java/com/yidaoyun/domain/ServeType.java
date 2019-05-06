/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.domain;

import javax.persistence.Entity;

import com.yidaoyun.common.base.BaseEntity;

/**
 * 售后服务类型
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
@Entity
public class ServeType extends BaseEntity {

    //名称
   private String name;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

   
}