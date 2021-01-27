<%@page import="com.pxy.dao.UserDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//验证登录
	String loginID = request.getParameter("loginID");
	String loginPWD = request.getParameter("loginPWD");
	String isLogin	 = request.getParameter("islogin");
	//如果选择了“自动保存”，则把账号和密码保存到Cookie
	if(isLogin != null){
		Cookie idCookie = new Cookie("loginID", loginID);
		Cookie pwdCookie = new Cookie("loginPWD", loginPWD);
		idCookie.setMaxAge(30);
		pwdCookie.setMaxAge(30);
		response.addCookie(idCookie);
		response.addCookie(pwdCookie);
	}
	
	//Session保存用户状态
	if(loginID != null){
		session.setAttribute("loginID", loginID);
	}
	
	//1.验证是否有数据
	if(loginID != null && loginPWD !=null &&
		loginID.length()>0 && loginPWD.length()>0){
		//2.验证账号密码是否正确
		if(UserDao.login(loginID, loginPWD)>0){
			//3.验证通过，则跳转到个人中心页面，需要继续传递账号信息，所以使用重定向
			//request.getRequestDispatcher("userinfo.jsp").forward(request, response);
			//3.1 使用Session保存了用户信息后，可以直接转发，而不用重定向
			response.sendRedirect("userinfo.jsp");
		}
		else{
			//4.验证不通过，跳转回登录页面，并显示“账号密码错误”
			request.setAttribute("err", "账号或密码错误！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	else{
		//5.跳转回登录页面，并显示“请输入账号和密码”
		request.setAttribute("err", "请输入账号和密码！");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
%>

