<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp">
	<jsp:param name="title" value="Login Page!"/>
</jsp:include>
	
	<!-- 로그인 창 -->
	<h2> 로그인 </h2>
	<form action="loginLogic.do" method="post">
		<table border=1 cellpadding="2" cellspacing="0" align="center" width="270px" height="120px">
			<tr>
				<th width="45px">ID</th>
				<td>
					<input type="text" name="id" placeholder="ID input" maxlength="15" size="25" required>
				</td>
			</tr>
			<tr>
				<th width="45px">PW</th>
				<td>
					<input type="password" name="pw" placeholder="Password input" maxlength="20" size="25" required>
				</td>
			</tr>
			<tr>
				<th colspan="2" align="center" height="35px">
					<input type="submit" value="로그인"> &nbsp;
					<input type="button" value="회원가입" onclick="location.href='joinView.do'">
				</th>
			</tr>
		
		</table>
		
	</form>
<%@ include file = "../layout/footer.jsp" %>