/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.service;

import java.util.Date;
import java.util.List;

import com.yidaoyun.domain.Orders;


/**
 * 订单Service
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
public interface OrderService {

	Orders save(Long commodityId, String ordernumber, String flag, String price, Integer number, Date createDate);
	List<Orders> getAllOrders();
	Orders getOne(String orderid);
}