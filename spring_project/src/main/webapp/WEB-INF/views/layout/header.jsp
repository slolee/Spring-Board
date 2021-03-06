<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String title = request.getParameter("title");
	if(title == null)
		title = "My Homepage";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><%= title %></title>
	<style>
		#header, #footer{
			background-color: #EEE6C4;
			font-family: "HY울릉도M";
			border: 1px solid black;
			width: 650px;
			height: 40px;
		}
		#main{
			border: 1px solid black;
			width: 650px;
			min-height: 400px;
			padding-top: 5px;
		}
		a{
			text-decoration: none;
		}
		
		input[type=button], input[type=submit], input[type=reset], input[type=file]{
			font-size: 9pt;
		}
		
		input[type="text"], input[type="password"], textarea{
			border: 0px;
			padding: 0px 0px 0px 5px;
		}
		
		table{
			font-size: small;
		}
		
		th{
			background-color: #EEE6C4;
		}
		
		td{
			background-color: #FFFFFF;
		}
	
	</style>
</head>
<body>
	<div align="center">
		<div id="header" align="center">
			<c:choose>
				<c:when test="${ sessionScope.login_id == null }">
					<a href="loginView.do">LOGIN</a> |
					<a href="joinView.do">JOIN</a> |
				</c:when>
				<c:otherwise>
					${ sessionScope.login_nickname } |
					<a href="logoutLogic.do">LOGOUT</a> |
				</c:otherwise>
			</c:choose>

			<a href="mypageView.do">MY PAGE</a> |
			<a href="boardView.do">BOARD</a> |		
			DOWNLOADS
		</div>
		<div id="main" align="center">
		
		