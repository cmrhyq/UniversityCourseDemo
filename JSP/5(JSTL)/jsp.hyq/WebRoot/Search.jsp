<%@page import="page.SingerDao"%>
<%@page import="page.Singer"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>歌手列表</title>

  </head>
  
  <body>
    			<%
    					List<Singer> singerList = SingerDao.selectAll();
    					session.setAttribute("singerList", singerList);
    			 %>
    			 
    			 <form action="Search.jsp"  method="post">
    			 				类型：<select name="singerType">
												<option value="1">编号</option>
												<option value="2">歌手</option>
											</select>
    			 				内容：<input type="text" name="singerName" />
    			 				<input type="submit" value="查找" />
    			 </form>
    			 
    			 <table border="1" cellspacing="0" cellpadding="0" style="width:400px ;margin:0px auto" >
    			 		<tr>
								<th>编号</th>
								<th>名称</th>
								<th>类型</th>
						</tr>
						<c:if var="result"  test="${singerList.size()>0 }"> 
								<c:forEach items="${singerList }" var="singer" varStatus="status">
											<tr <c:if test="${status.index%2==0 }">style="background-color: brown"</c:if>>
													<td>${singer.singerID }</td>
													<td align="center">${singer.singerName }</td>
													<td>${singer.singerType }</td>
											</tr>
								</c:forEach>
						</c:if>
						<c:if test="${!result }">
									<tr>
											<td colspan="3" style="height: 80px; font-size: 24px; text-align: center; background-color: #cccccc">
													查无数据！
											</td>
									</tr>
						</c:if>
						
    			 </table>
  </body>
</html>