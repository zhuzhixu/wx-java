/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.common;

import java.util.Random;


/**
 * 工具
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
public class Util{

    public static String game( ){
        StringBuffer code= new StringBuffer();
        //生成随机数
        Random rand=new Random();
        for(int a=0;a<12;a++){
            //生成12位验证码
            code.append(rand.nextInt(10));
        }
        return code.toString();
    }
}