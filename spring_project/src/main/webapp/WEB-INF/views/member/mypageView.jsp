<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../layout/header.jsp" %>

<h2>myPage</h2>
<form action="mypageLogic.do" method="post">
	<table border=1 cellpadding="5" cellspacing="0" align="center" width="270px" height="75px">
	   	<tr height="40px">
	   		<th width="45px">PW</th>
	   		<td>
	   			<input type="password" name="pw" placeholder="Password input" maxlength="20" size="25" required>
	   		</td>
	   	</tr>
	   	
	   	<tr height="35px">
	   		<th colspan="2">
	   			<input type="submit" value="확인">
	   			<input type="button" value="취소" onclick="history.back()">
	   		</th>
	   	</tr>	
	 </table>
</form>	
<%@ include file = "../layout/footer.jsp" %>