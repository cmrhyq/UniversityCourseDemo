<%@page import="com.pxy.dao.UserDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String loginID = request.getParameter("loginID");
	String loginPWD = request.getParameter("loginPWD");
	
	if(UserDao.login(loginID, loginPWD)==1){
		//跳转到首页
%>
	<jsp:forward page="index.jsp">
		<jsp:param value="<%=loginID %>" name="loginID"/>
	</jsp:forward>
<%
	}
	else{
		//回到登录页
%>
	<jsp:forward page="login.jsp">
		<jsp:param value="账号或密码错误！" name="err"/>
	</jsp:forward>
<%
	}
%>