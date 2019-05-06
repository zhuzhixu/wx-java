/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.service.impl;

import com.yidaoyun.dao.ServeDAO;
import com.yidaoyun.domain.Serve;
import com.yidaoyun.service.ServeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServeServiceImpl implements ServeService {
    @Autowired
    private ServeDAO serveDAO;

}