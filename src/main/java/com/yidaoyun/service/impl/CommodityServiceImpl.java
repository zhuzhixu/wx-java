/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.service.impl;

import com.yidaoyun.dao.CommodityDAO;
import com.yidaoyun.domain.Commodity;
import com.yidaoyun.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityDAO commodityDAO;

    @Override
    public List<Commodity> getAllCommodity() {
        return commodityDAO.findAllByFlag(0);
    }

	@Override
	public Commodity getCommodity(Long id) {
		return commodityDAO.getOne(id);
	}

    
    
}