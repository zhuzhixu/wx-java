/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yidaoyun.domain.Serve;

/**
 * 售后服务DAO
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
public interface ServeDAO extends JpaRepository<Serve, Long> {
	
}