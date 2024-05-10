<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
table, td, th{
	border: 1px solid;
}

table{
	width: 100%;
	border-collapse: collapse;
}

</style>
</head>
<body>

	<h3>리스트 출력만 해보기</h3>
	<table>
		<tr>
			<th>bno</th>
			<th>title</th>
			<th>writer</th>
			<th>regdate</th>
			<th>viewcount</th>
		</tr>
		<c:forEach items="${list }" var="practiceVO">
			<tr>
				<td>${practiceVO.bno }</td>
				<td>${practiceVO.title }</td>
				<td>${practiceVO.writer }</td>
				<td>${practiceVO.regdate }</td>
				<td>${practiceVO.viewcount }</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>