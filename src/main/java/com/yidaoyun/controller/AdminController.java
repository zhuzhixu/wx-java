/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Directory;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yidaoyun.common.JsonResult;
import com.yidaoyun.common.MD5;
import com.yidaoyun.common.Util;
import com.yidaoyun.domain.Commodity;
import com.yidaoyun.domain.Orders;
import com.yidaoyun.domain.RechargeRecord;
import com.yidaoyun.domain.User;
import com.yidaoyun.service.CommodityService;
import com.yidaoyun.service.OrderService;
import com.yidaoyun.service.RechargeRecordService;
import com.yidaoyun.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * web后台
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
@RequestMapping
@Controller
@Api(description = "web后台")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private RechargeRecordService rechargeRecordService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/login")
	@ApiOperation(httpMethod = "GET", value = "跳转登陆页面", notes = "跳转登陆页面")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
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
	@ResponseBody
    @PostMapping("/login")
    public JsonResult login(String username, String password, HttpSession session){
        MD5 md5 = new MD5();
        password = md5.getMD5ofStr(password);
        User user = userService.getUserByUserNameAndPasssword(username, password);
        if(user != null){
        	if(user.getFlag().equals(1)) {
        		//将user信息存入session
        		session.setAttribute("user", user);
        		return JsonResult.renderSuccess("",user);
        	}else {
        		return JsonResult.renderFail("权限不足");
        	}
        }
        return  JsonResult.renderFail("用户名或者密码错误");
    }
	
	/**
	 * 商品列表
	 * @param userid
	 * @return
	 */
	 @ApiOperation(httpMethod = "POST", value = "商品列表", notes = "商品列表")
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "userId", value = "用户id"),
	    })
	 @RequestMapping("/commoditylist/{userid}")
	 public ModelAndView getCommoditylist(@PathVariable Long userid) {
		User user = userService.getUserById(userid);
		ModelAndView mv = new ModelAndView();
		if (user != null && user.getFlag().equals(1)) {
			mv.setViewName("commoditylist");
			List<Commodity> commodities = commodityService.getAllCommodity();
			mv.addObject("commoditys", commodities);
			mv.addObject("user", user);
			return mv;
		}
		mv.setViewName("redirect:/login");
		return mv;
	}
	 
	/**
	 * 后台用户退出
	 * 
	 */
	@RequestMapping("/loginout")
	public ModelAndView loginout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/login");
	}
	 
	/**
	 * 所有用户信息
	 */
	@RequestMapping("/userlist/{userid}")
	public ModelAndView getUserInfo(Long userid, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return new ModelAndView("redirect:/login");
		}
		List<User> list = userService.getAllUsers();
		ModelAndView modelAndView = new ModelAndView();
		if (!list.isEmpty()) {
			modelAndView.setViewName("userlist");
			modelAndView.addObject("users",list);
			return modelAndView;
		}
		return new ModelAndView("login");
	}
	
	/**
	 * 添加用户
	 * 
	 */
	@ResponseBody
	@PostMapping("/adduser")
	public JsonResult addUser(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = new MD5().getMD5ofStr(request.getParameter("password"));
		String name = request.getParameter("name");
		String shippingaddresshome = request.getParameter("shippingaddresshome");
		String shippingaddresscompany = request.getParameter("shippingaddresscompany");
		User user = new User();
		user.setCardnumber(Util.game());
		user.setUsername(username);
		user.setName(name);
		user.setFlag(1);
		user.setPasword(password);
		user.setShippingaddresshome(shippingaddresshome);
		user.setShippingaddresscompany(shippingaddresscompany);
		User user1 = userService.save(user);
		if(user1 != null) {
			return JsonResult.renderSuccess("添加成功", user1);
		}
		return JsonResult.renderFail("添加失败");
	}
	
	/**
	 * 删除用户
	 * 
	 */
	@ResponseBody
	@PostMapping("/deleteuser")
	public JsonResult delUser(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		userService.delUserById(Long.parseLong(userid));
		return JsonResult.renderSuccess("删除成功");
	}
	
	/**
	 * 修改用户信息
	 * 
	 */
	@ResponseBody
	@PostMapping("/changeuser/{userid}")
	public JsonResult changeUser(HttpServletRequest request,@PathVariable Long userid) {
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String password = new MD5().getMD5ofStr(request.getParameter("password"));
		String name = request.getParameter("name");
		String shippingaddresshome = request.getParameter("shippingaddresshome");
		String shippingaddresscompany = request.getParameter("shippingaddresscompany");
		User user = userService.getUserById(userid);
		user.setUsername(username);
		user.setName(name);
		user.setFlag(1);
		user.setPasword(password);
		user.setShippingaddresshome(shippingaddresshome);
		user.setShippingaddresscompany(shippingaddresscompany);
		user.setPhone(phone);
		userService.save(user);
		return JsonResult.renderSuccess("修改成功");
	}
	
	/**
     * 
     * 获得用户信息
     */
	@ResponseBody
    @PostMapping("/getUserInfo/{userid}")
    public JsonResult<User> getUserInfo(@PathVariable Long userid) {
    	User user = userService.getUserById(userid);
    	System.out.println(userid);
    	System.out.println(user.toString());
    	if (user != null) {
    		return JsonResult.renderSuccess(user);
		}
    	return JsonResult.renderSuccess("不存在用户");
    }
	
	/**
	 * 
	 * 充值
	 */
	@ResponseBody
	@PostMapping("/pay/{userid}/{price}")
	public JsonResult<RechargeRecord> pay(@PathVariable Long userid, @PathVariable String price) {
		RechargeRecord rechargeRecord = new RechargeRecord();
		rechargeRecord.setCreatedate(new Date());
		rechargeRecord.setOrdernumber(Util.game());
		rechargeRecord.setPrice(price);
		rechargeRecord.setUserid(userid);
		RechargeRecord flag = rechargeRecordService.createREchargeRecordByRechargeRecord(rechargeRecord);
		User user = userService.getUserById(userid);
		Integer needprice = Integer.parseInt(user.getBalance()==null? "0":user.getBalance());
		user.setBalance(String.valueOf( needprice+ Integer.parseInt(price)));
		User user2 = userService.save(user);
		if(flag !=null && user2 != null) {
			return JsonResult.renderSuccess("充值成功", flag);
		}
		return JsonResult.renderFail("充值失败");
	}
	
	/**
	 * 
	 * 获取某个用户充值记录
	 */
	@ResponseBody
	@PostMapping("/getpay/{userid}")
	public JsonResult getPay(@PathVariable Long userid, HttpServletRequest request) {
		List<RechargeRecord> list = rechargeRecordService.getRechargeRecordsByUserid(userid);
		if(list != null) {
			
			return JsonResult.renderSuccess("查询成功",list);
		}
		return JsonResult.renderSuccess("查询不到记录");
	}
	
	/**
	 * 获得单一物品信息
	 * 
	 */
	@ResponseBody
	@PostMapping("/getone/{id}")
    public JsonResult<Commodity> getOne(@PathVariable Long id) {
    	Commodity commodity = commodityService.getCommodity(id);
		if(commodity.getId()==id && commodity.getFlag()== 0) {
			return JsonResult.renderSuccess(commodity);
		}
    	return JsonResult.renderError("不存在商品");
    }
	
	/**
	 * 获得全部订单信息
	 * 
	 */
	@RequestMapping("/orderlist/{userid}")
	 public ModelAndView getOrderList(@PathVariable Long userid) {
		User user = userService.getUserById(userid);
		ModelAndView mv = new ModelAndView();
		if (user != null && user.getFlag().equals(1)) {
			mv.setViewName("order");
			List<Orders> orders = orderService.getAllOrders();
			mv.addObject("orders", orders);
			mv.addObject("user", user);
			return mv;
		}
		mv.setViewName("redirect:/login");
		return mv;
	}
	
//	/**
//	 * 添加订单
//	 * 
//	 */
//	@ResponseBody
//	@PostMapping("/addorder")
//	public JsonResult addorder(HttpServletRequest request) {
//		Orders orders = new Orders();
//		orders.setOrdernumber(Util.game());
//		
//	}
	
	/**
	 * 订单单独详情
	 * 
	 */
	@ResponseBody
	@PostMapping("/order/{orderid}")
	public JsonResult addorder(@PathVariable String orderid) {
		Orders orders = orderService.getOne(orderid);
		if(orders != null) {
			return JsonResult.renderSuccess(orders);
		}
		return  JsonResult.renderFail("订单不存在");
		
	}
	
	/**
	 * 上传文件
	 * 
	 */
	@ResponseBody
	@PostMapping("/upload")
	public JsonResult upload(@RequestParam MultipartFile file, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("/") + "img/";
		File dirFile  = new File(path);
		String oldName = file.getOriginalFilename();
		String first = oldName.substring(0, oldName.indexOf("."));
		String second = oldName.substring(oldName.indexOf("."),oldName.length());
		String newName = new MD5().getMD5ofStr(first) + second;
		File file2 = new File(path + newName);
		if(!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		if(!file.isEmpty()) {
			try {
				FileUtil.copyStream(file.getInputStream(), new FileOutputStream(file2));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return JsonResult.renderSuccess("/img/"+ newName);
		
	}
}
