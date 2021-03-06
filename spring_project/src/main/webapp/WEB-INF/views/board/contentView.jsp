<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../layout/header.jsp">
	<jsp:param name="title" value="Board Page!"/>
</jsp:include>

<script type="text/javascript">
	function board_delete(idx){
		if(confirm("정말 삭제하시겠습니까 ?") == true)
			location.href="deleteLogic.do?idx="+idx;
		return;
	}
</script>

<h3>게 시 글</h3>
<table border=1 cellpadding="5" cellspacing="0" align="center" width="640px" higth="400px" style="font-size:small;">
		<tr>
			<th>제목</th>
			<td colspan="5">${ vo.title }</td>
			
		</tr>
		<tr>
			<th>작성자</th>
			<td>${ vo.id }</td>
			<th>작성일</th>
			<td>${ vo.regdate }</td>
			<th width="40px">조회수</th>
			<td width="40px">${ vo.hit }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="5"><a href="fileDownload.do?idx=${ vo.idx }"><font size="2">${ vo.file_path.split('_')[1] }</font></a></td>
		</tr>
		
		<tr>
			<th colspan="6">내용</th>
		</tr>
		
		<tr>
			<td colspan="6" height="150px" style="vertical-align:top; margin-top:5px;">${ vo.context }</td>
		</tr>
</table>
<div align="right" style="margin-top:5px;">
		<input type="button" value="뒤로가기" onclick="location.href='boardView.do'">
		<c:choose>
			<c:when test="${ sessionScope.login_id ne 'admin' }">
				<c:if test="${ self eq 1 }">
					<input type="button" value="수정" onclick="location.href='updateView.do?idx=${ vo.idx }'">
					<input type="button" value="삭제" onclick="board_delete('${ vo.idx }')">
				</c:if>
			</c:when>
			<c:otherwise>
				<c:if test="${ self eq 1 }">
					<input type="button" value="수정" onclick="location.href='updateView.do?idx=${ vo.idx }'">
				</c:if>
				<input type="button" value="삭제" onclick="board_delete('${ vo.idx }')">
			</c:otherwise>
		</c:choose>
		&nbsp;&nbsp;&nbsp;
</div>

<%@ include file = "../layout/footer.jsp" %>