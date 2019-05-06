/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.service.impl;

import com.yidaoyun.dao.ServeTypeDAO;
import com.yidaoyun.domain.ServeType;
import com.yidaoyun.service.ServeService;
import com.yidaoyun.service.ServeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServeTpeServiceImpl implements ServeTypeService {
    @Autowired
    private ServeTypeDAO serveTypeDAO;
}