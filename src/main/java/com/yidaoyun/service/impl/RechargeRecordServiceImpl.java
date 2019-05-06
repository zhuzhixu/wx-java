/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.service.impl;

import com.yidaoyun.dao.RechargeRecordDAO;
import com.yidaoyun.domain.RechargeRecord;
import com.yidaoyun.service.RechargeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RechargeRecordServiceImpl implements RechargeRecordService {

    @Autowired
    RechargeRecordDAO rechargeRecordDAO;

	@Override
	public RechargeRecord createRechargeRecord(String ordernumber, String price) {
		RechargeRecord rechargeRecord = new RechargeRecord();
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dString = simpleDateFormat.format(date);
		Date ceDate  = null;
		try {
			ceDate = simpleDateFormat.parse(dString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	rechargeRecord.setCreatedate(ceDate);
    	rechargeRecord.setOrdernumber(ordernumber);
    	rechargeRecord.setPrice(price);
    	rechargeRecord.setUserid(1L);
		return rechargeRecordDAO.save(rechargeRecord);
	}

	@Override
	public List<RechargeRecord> getAllRechargeRecords() {
		return rechargeRecordDAO.findAll();
	}

	@Override
	public RechargeRecord createREchargeRecordByRechargeRecord(RechargeRecord rechargeRecord) {
		return rechargeRecordDAO.save(rechargeRecord);
	}

	@Override
	public List<RechargeRecord> getRechargeRecordsByUserid(Long userid) {
		List<RechargeRecord> lists =rechargeRecordDAO.findAll();
		List<RechargeRecord> list = new ArrayList<RechargeRecord>();
		for(RechargeRecord rechargeRecord : lists) {
			if(rechargeRecord.getUserid() == userid) {
				list.add(rechargeRecord);
			}
		}
		return list;
	}


}