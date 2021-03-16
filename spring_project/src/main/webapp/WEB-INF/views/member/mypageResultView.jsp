<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp">
	<jsp:param name="title" value="MyPage"/>
</jsp:include>

	<h2>회원정보</h2>
	<table border=1 cellpadding="5" cellspacing="0" align="center" width="300px" height="170px" style="font-size:small;">
		<tr>
			<th>ID</th>
			<td>${ vo.id }</td>
		</tr>
		
		<tr>
			<th>NickName</th>
			<td>${ vo.nickname }</td>
		</tr>
		
		<tr>
			<th>Email</th>
			<td>${ vo.email }</td>
		</tr>
		
		<tr>
			<th>Phone Number</th>
			<td>${ vo.phone }</td>
		</tr>
		
		<tr>
			<th colspan="2">
				<input type="button" value="뒤로가기" onclick="history.back()">
				<input type="button" value="회원탈퇴" onclick="location.href='signoutView.do'">
			</th>
		</tr>
	</table>
<%@ include file = "../layout/footer.jsp" %>