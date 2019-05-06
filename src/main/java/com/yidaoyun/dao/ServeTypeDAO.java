/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.yidaoyun.domain.ServeType;

/**
 * 售后服务种类DAO
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
@Component
public interface ServeTypeDAO extends JpaRepository<ServeType, Long> {

}