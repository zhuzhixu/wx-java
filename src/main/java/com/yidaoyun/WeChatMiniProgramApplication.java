/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SpringBoot启动类
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
@EnableSwagger2
@SpringBootApplication
public class WeChatMiniProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeChatMiniProgramApplication.class, args);
	}
}
