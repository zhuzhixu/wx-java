/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.service;

import java.util.List;

import com.yidaoyun.domain.Commodity;

/**
 * 商品Service
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
public interface CommodityService {
	
    List<Commodity> getAllCommodity();
    
    Commodity getCommodity(Long id);
    
    Commodity save(Commodity commodity);
    
    void delet(Long commodityID);
}