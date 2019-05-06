/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.service.impl;

import com.yidaoyun.dao.OrderDAO;
import com.yidaoyun.domain.Orders;
import com.yidaoyun.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

	@Override
	public Orders save(Long commodityId, String ordernumber, String flag, String price, Integer number, Date createDate) {
		Orders orders = new Orders();
		orders.setCommodityId(commodityId);
		orders.setOrdernumber(ordernumber);
		orders.setFlag(flag);
		orders.setPrice(price);
		orders.setNumber(number);
		orders.setCreateData(createDate);
		orders.setUserId(1L);
		return orderDAO.save(orders);
	}

	@Override
	public List<Orders> getAllOrders() {
		return orderDAO.findAll();
	}

	@Override
	public Orders getOne(String orderid) {
		List<Orders> lists = orderDAO.findAll();
		List<Orders> list = new ArrayList<Orders>();
		for(Orders orders : lists) {
			if(orders.getOrdernumber().equals(orderid)) {
				return orders;
			}
		}
		return null;
	}

	

	
    
}