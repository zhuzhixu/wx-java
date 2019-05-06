/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.controller;
import com.yidaoyun.common.JsonResult;
import com.yidaoyun.common.MD5;
import com.yidaoyun.common.Util;
import com.yidaoyun.domain.*;
import com.yidaoyun.service.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * WeChatMiniProgram后台
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
@RequestMapping("/wechat")
@RestController
@Api(description = "wx小程序相关接口")
public class CommonController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private RechargeRecordService rechargeRecordService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ServeTypeService serveTypeService;

    @Autowired
    private ServeService serveService;




    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "登陆", notes = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping("/login")
    public JsonResult login(String username, String password) {
        MD5 md5 = new MD5();
        password = md5.getMD5ofStr(password);
        User user = userService.getUserByUserNameAndPasssword(username, password);
        if (user != null) {
            return JsonResult.renderSuccess(user);
        }
        return  JsonResult.renderFail();
    }

    /**
     * 获取所有商品
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "获取所有商品", notes = "获取所有商品")
    @PostMapping("/getallcommodity")
    public JsonResult getAllCommodity() {
        List<Commodity> commodities = commodityService.getAllCommodity();
        List<Map> goods = new ArrayList<>();
        for (Commodity commodity : commodities) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", commodity.getId());
            map.put("corver", commodity.getImg());
            map.put("name", commodity.getName());
            map.put("show_price", commodity.getPrice());
            map.put("sale_price", commodity.getPrice());
            goods.add(map);
        }
        return JsonResult.renderSuccess(goods);
    }

    
    /**
     * 
     * @return wechat获得用户信息
     */
    @ApiOperation(httpMethod = "POST", value = "获取用户信息", notes = "获取用户信息")
    @PostMapping("/getUserInfo/{userid}")
    public JsonResult<User> getUserInfo(@PathVariable Long userid) {
    	User user = userService.getUserById(userid);
    	if (user != null) {
    		return JsonResult.renderSuccess(user);
		}
    	return JsonResult.renderError("不存在用户");
    }
    
    /**
     * @return wechat得到单一物品信息
     */
    @PostMapping("/getone/{id}")
    public JsonResult<Commodity> getOne(@PathVariable Long id) {
    	Commodity commodity = commodityService.getCommodity(id);
		if(commodity.getId()==id && commodity.getFlag()== 0) {
			return JsonResult.renderSuccess(commodity);
		}
    	return JsonResult.renderError("不存在商品");
    }
    
    /**
     * 
     * @return wechat创建订单
     */
    @PostMapping("/createOrder/{commodityId}/{number}/{price}")
    public JsonResult createOrder(@PathVariable Long commodityId, @PathVariable Integer number, @PathVariable String price) {
    	String ordernumber = Util.game();
    	String flag = "0";
    	Date createDate = new Date();
    	Orders orders = orderService.save(commodityId, ordernumber, flag, price, number, createDate);
    	if (orders!=null) {
			return JsonResult.renderSuccess("创建订单成功", orders);
		}
    	return JsonResult.renderError("创建订单失败");
    }
    
    /**
     * @return wechat得到全部订单信息
     */
    @PostMapping("/getAllOrders")
    public JsonResult getAllOrders() {
    	List<Orders> lists = orderService.getAllOrders();
    	if (!lists.isEmpty()) {
			return JsonResult.renderSuccess("全部订单查询成功", lists);
		}
    	return JsonResult.renderFail("无查询内容");
    }	
    
    /**
     * @return wechat 充值或支出
     * 
     */
    @PostMapping("/pay/{price}")
    public JsonResult pay(@PathVariable String price) {
    	RechargeRecord rechargeRecord = null;
    	String ordernumber = Util.game();
    	rechargeRecord = rechargeRecordService.createRechargeRecord(ordernumber,price);
    	if (rechargeRecord != null) {
    		return JsonResult.renderSuccess("充值成功", rechargeRecord);
		}
    	return JsonResult.renderFail("充值失败");
    }
    
    /**
     * @return wechat 查询余额
     * 
     */
    @PostMapping("/myaccount")
    public JsonResult myaccount() {
    	List<RechargeRecord> rechargeRecords = rechargeRecordService.getAllRechargeRecords();
    	Long price = 0L;
    	for(RechargeRecord rechargeRecord : rechargeRecords) {
    		price += Long.parseLong(rechargeRecord.getPrice()); 
    		System.out.println(rechargeRecord.getPrice());
    	}
    	System.out.println(price);
    	return JsonResult.renderSuccess("查询成功", price);
    }
    
    /**
     * @return wechat 查询充值记录
     * 
     */
    @PostMapping("/payin")
    public JsonResult payin() {
    	List<RechargeRecord> rechargeRecords = rechargeRecordService.getAllRechargeRecords();
    	List<RechargeRecord> needData =	new ArrayList<RechargeRecord>();
    	for(RechargeRecord rechargeRecord : rechargeRecords) {
    		if(Long.parseLong(rechargeRecord.getPrice()) >= 0) {
    			needData.add(rechargeRecord);
    		}
    	}
    	return JsonResult.renderSuccess("查询成功", needData);
    }
    
    /**
     * @return wechat 查询账户支出记录
     * 
     */
    @PostMapping("/payout")
    public JsonResult payout() {
    	List<Orders> lists = orderService.getAllOrders();
    	List<Orders> needData = new ArrayList<Orders>();
    	for(Orders orders : lists) {
    		if(Long.parseLong(orders.getFlag()) == 1L) {
    			needData.add(orders);
    		}
    	}
    	return JsonResult.renderSuccess(needData);
    }
}
