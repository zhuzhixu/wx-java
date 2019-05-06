/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.yidaoyun.service.impl;

import com.yidaoyun.dao.UserDAO;
import com.yidaoyun.domain.User;
import com.yidaoyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUserByUserNameAndPasssword(String username, String password) {
        return userDAO.getByUsernameAndPasword(username, password);
    }

    @Override
    public User getUserById(Long userid) {
        return userDAO.getOne(userid);
    }

	@Override
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}

	@Override
	public User save(User user) {
		return userDAO.save(user);
	}

	@Override
	public void delUserById(Long id) {
		userDAO.deleteById(id);
	}

	
	
}
