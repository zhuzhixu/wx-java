package com.yidaoyun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.yidaoyun.domain.User;

public class LoginIntercepet implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
}
