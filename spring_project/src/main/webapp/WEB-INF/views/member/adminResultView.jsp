<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../layout/header.jsp">
	<jsp:param name="title" value="AdminPage"/>
</jsp:include>
	<h4>관리자 페이지</h4>
	
	<table border=1 cellpadding="5" cellspacing="0" align="center" width="640px">
		<tr>
			<th><font size="2">Num</font></th>
			<th><font size="2">ID</font></th>
			<th><font size="2">Password</font></th>
			<th><font size="2">NickName</font></th>
			<th><font size="2">Email</font></th>
			<th><font size="2">PhoneNum</font></th>
			<th><font size="2"></font>
		</tr>
		<c:set var="i" value="1" />
		<c:forEach var="vo" items="${ voList }">
			<tr bgcolor="#FFFFFF">		
				<th><font size="2">${ i }</font></th>
				<td align="center"><font size="2">${ vo.id }</font></td>
				<td align="center"><font size="2">${ vo.pw }</font></td>
				<td align="center"><font size="2">${ vo.nickname }</font></td>
				<td align="center"><font size="2">${ vo.email }</font></td>
				<td align="center"><font size="2">${ vo.phone }</font></td>
				<td align="center"><input type="button" value="추방" style='font-size:8pt' onclick="location.href='explusionLogic.do?id=${ vo.id }&pw=${ vo.pw }'">
				<c:set var="i" value="${ i + 1 }" />
			</tr>	
		</c:forEach>
	</table>
	<div align="center">
		<br>
		<input type="button" value="뒤로가기" onclick="location.href='home.do'">
	</div>

<%@ include file = "../layout/footer.jsp" %>