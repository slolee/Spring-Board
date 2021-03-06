<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp">
	<jsp:param name="title" value="SignUp Page!"/>
</jsp:include>
	<h2>회원가입</h2>
	<form name="joinForm" action="joinLogic.do" method="post">
		<table border=1 cellpadding="5" cellspacing="0" align="center" width="500px" height="270px">
			<tr>
				<th>ID<font color="red">*</font></th>
				<th align="left">
					<input type="text" name="id" maxlength="20" size="35" placeholder="ID input" required>
				</th>
			</tr>
						
			<tr>
				<th>NickName<font color="red">*</font></th>
				<th align="left">
					<input type="text" name="nickname" maxlength="15" size="35" placeholder="NickName input" required>
				</th>
			</tr>
			
			<tr>
				<th>Password <font color="red">*</font></th>
				<th align="left">
					<input type="password" name="pw" maxlength="20" size="35" placeholder="Password input" required>
				</th>
			</tr>
			
			<tr>
				<th>Confirm Password <font color="red">*</font></th>
				<th align="left">
					<input type="password" name="confirm_pw" maxlength="20" size="35" placeholder="Confirm Password" required>
				</th>
			</tr>
			
			<tr>
				<th>Email<font color="red">*</font></th>
				<th align="left">
					<input type="email" name="user_email1" size="7" placeholder="email@" required>
					@
					<select name="user_email2">
						<option value="@naver.com">naver.com</option>
						<option value="@google.com">google.com</option>
						<option value="@daum.net">daum.net</option>
						<option value="@nate.com">nate.com</option>
					</select>
				</th>
			</tr>
			
			<tr>
				<th>Phone Number</th>
				<th align="left">
					<select name="phone_number1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
					</select>
					-
					<input type="text" name="phone_number2" maxlength="4" size="4" pattern="[0-9]{4}">
					-
					<input type="text" name="phone_number3" maxlength="4" size="4" pattern="[0-9]{4}">
				</th>
			</tr>
			
			<tr>
				<th colspan="2" align="right">
					<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/script.js"></script>
					<input type="button" value="완료" onclick="checkJoinForm()">
					<input type="reset" value="취소">
					<input type="button" value="뒤로가기" onclick="history.back()">
				</th>
			</tr>
		</table>
	</form>
<%@ include file = "../layout/footer.jsp" %>