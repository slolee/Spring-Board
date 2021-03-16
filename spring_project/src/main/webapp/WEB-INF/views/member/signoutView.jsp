<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp">
	<jsp:param name="title" value="SignOut Page"/>
</jsp:include>

	<h2> 회원탈퇴 </h2>
	<form name="signoutForm" action="signoutLogic.do" method="post">
		<table border=1 cellpadding="5" cellspacing="0" align="center" width="350px" height="120px">
			<tr>
				<th>ID</th>
				<td align="left">
					<input type="text" name="id" maxlength="20" size="35" placeholder="ID input" required>
				</td>
			</tr>
				
			<tr>
				<th>Password</th>
				<td align="left">
					<input type="password" name="pw" maxlength="20" size="35" placeholder="Password input" required>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<script type="text/javascript">
						function pardon(){
							if(confirm("정말 탈퇴하시겠습니까 ?") == true)
								document.signoutForm.submit();
							return;
						}
					</script>
					<input type="button" value="완료" onclick="pardon()">
					<input type="button" value="취소" onclick="history.back()">
				</td>
			</tr>
		
		</table>
	</form>

<%@ include file = "../layout/footer.jsp" %>