/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.service;

import java.util.List;

import com.yidaoyun.domain.RechargeRecord;

/**
 * 充值记录Service
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
public interface RechargeRecordService {

	RechargeRecord createRechargeRecord(String ordernumber, String price);
	
	List<RechargeRecord> getAllRechargeRecords();
	
	RechargeRecord createREchargeRecordByRechargeRecord(RechargeRecord rechargeRecord);
	
	List<RechargeRecord> getRechargeRecordsByUserid(Long userid);
}