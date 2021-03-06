<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../layout/header.jsp">
	<jsp:param name="title" value="Board Page!"/>
</jsp:include>
	<script type="text/javascript">
		function login_first(login_id){
			if( login_id == "" || login_id == null){
				alert("로그인을 먼저 해주세요.");
				location.href="loginView.do";
			} else {
				location.href="writeView.do";
			}
		}
		
		function is_password(login_id, idx, write_pw, now_page){
			if(login_id == "admin" || write_pw == ""){
				location.href="contentView.do?idx=" + idx;
			}else{
				var inputString = prompt("패스워드를 입력하세요.");
				if(inputString == write_pw){
					location.href="contentView.do?idx=" + idx;
				}else{
					alert("잘못된 패스워드입니다.");	
				}
			}
		}
	</script>
	
	<h3>게시판</h3>
	
	<table border=1 cellpadding="5" cellspacing="0" align="center" width="640px" >
		<tr>
			<th width="15px"><font size="2" >No.</font></th>
			<th><font size="2">Title</font></th>
			<th width="100px"><font size="2">Writer</font></th>
			<th width="20px"><font size="2">Hit</font></th>
		</tr>
		<c:forEach var="vo" items="${ voList }">
			<tr bgcolor="#FFFFFF">		
				<th width="15px"><font size="2">${ vo.idx }</font></th>
				<td align="center">
					<font size="2">
						<a href="javascript:is_password('${ sessionScope.login_id }', '${ vo.idx }', '${ vo.write_pw }', '${ now_page }')">${ vo.title }</a></font><font size="2" color="red">&nbsp[${ vo.comments }]
					</font>
				</td>
				<td width="100px" align="center"><font size="2">${ vo.id }</font></td>
				<td width="20px" align="center"><font size="2">${ vo.hit }</font></td>
			</tr>	
		</c:forEach>
	</table>
	
	<c:choose>
		<c:when test="${ now_page == 1 }">
			<input type="button" value="이전" onclick="alert('첫 페이지입니다.')">
		</c:when>
		<c:otherwise>
			<input type="button" value="이전" onclick="location.href='boardView.do?page=${ now_page - 1}'">
		</c:otherwise>
	</c:choose>
	
	<font size='2px'>${ now_page } / ${ max_page }</font>
	
	<c:choose>
		<c:when test="${ now_page == max_page }">
			<input type="button" value="다음" onclick="alert('마지막 페이지입니다.')">
		</c:when>
		<c:otherwise>
			<input type="button" value="다음" onclick="location.href='boardView.do?page=${ now_page + 1}'">
		</c:otherwise>
	</c:choose>
	
	<input type="button" value="글쓰기" onclick="login_first('${ sessionScope.login_id }')">
	
<%@ include file = "../layout/footer.jsp" %>