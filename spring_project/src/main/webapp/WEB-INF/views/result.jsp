<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "layout/header.jsp" %>
	<script>
		alert("${message}");
		location.href = "${location}";
	</script>

<%@ include file = "layout/footer.jsp" %>