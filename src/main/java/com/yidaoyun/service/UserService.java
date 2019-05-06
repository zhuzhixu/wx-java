/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.service;

import java.util.List;

import com.yidaoyun.domain.User;

/**
 * 用户Service
 * @author 云计算应用与开发项目组
 * @since  V2.0
 */
public interface UserService {


    User getUserByUserNameAndPasssword(String username, String password);

    User getUserById(Long userid);
    
    List<User> getAllUsers();
    
    User save(User user);
    
    void delUserById(Long id);
    
    
}
