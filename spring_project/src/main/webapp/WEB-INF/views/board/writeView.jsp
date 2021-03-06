<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../layout/header.jsp">
	<jsp:param name="title" value="Board Page!"/>
</jsp:include>

<script type="text/javascript">
	var checked = false;
	
	function checkbox_click(){
		var textfield = document.getElementById("pw_input");
		if(checked){
			textfield.disabled = true;
			checked = false;
		}else{
			textfield.disabled = false;
			checked = true;
		}
	}
</script>

<h3>게시글 작성</h3>
<form action="writeLogic.do" method="post" enctype="multipart/form-data">
	<table border=1 cellpadding="5" cellspacing="0" align="center" width="640px" higth="400px">
		<tr>
			<th><font size="2px">제목</font></th>
			<td colspan="3">
				<input type="text" name="title" placeholder="Title input" maxlength="150" size="67" required>
			</td>
		</tr>
		<tr>
			<th><font size="2px">패스워드</font><input type="checkbox" name="pw_check" onclick="checkbox_click()"></th>
			<td>
				<input type="password" name="pw" id="pw_input" placeholder="password input" maxlength="20" size="20" disabled required>
			</td>
			<th><font size="2px">첨부파일</font></th>
			<td>
				<input type="file" name="file" >
			</td>
		</tr>
		
		<tr>
			<th colspan="4"><font size="2px">내용</font></th>
		</tr>
		
		<tr>
			<td colspan="4">
				<textarea rows="12" cols="85" name="context" style="resize:none;" required></textarea>
			</td>
		</tr>
	</table>
	<div align="right" style="margin-top:5px;">
		<input type="button" value="취소" onclick="location.href='boardView.do'">
		<input type="submit" value="완료">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
</form>


<%@ include file = "../layout/footer.jsp" %>