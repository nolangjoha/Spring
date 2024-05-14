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
</head>
<body>
	
	<!-- 게시판 양식 -->
	<div class="card">
		<div class="card-header">
		<!-- 게시글 -->
		<h3 class="card-title">게시판</h3>
		</div>
		
		<div class="card-body">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th style="width: 10%">bno</th>
					<th style="width: 30%">title</th>
					<th style="width: 25%">writer</th>
					<th style="width: 25%">regdate</th>
					<th style="width: 25%">viewcount</th>
				</tr>
			</thead>
		<tbody>
			<c:forEach items="${list }" var="practiceVO">
				<tr>
					<td>${practiceVO.bno}</td>
					<td><a href="/practice/get?bno=${practice.bno }&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}&pageNum=${pageMaker.cri.pageNum}&amount${pageMaker.cri.amount}">${practiceVO.title }</a></td>
					<td>${practiceVO.writer}</td>
					<td>${practiceVO.bno}</td>
					<td>${practiceVO.viewcount}</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		</div>
		
		<!-- 게시글 페이지 목록 -->
		<div class="card-footer clearfix">
		<ul class="pagination pagination-sm m-0 float-right">
		<li class="page-item"><a class="page-link" href="#">«</a></li>
		<li class="page-item"><a class="page-link" href="#">1</a></li>
		<li class="page-item"><a class="page-link" href="#">2</a></li>
		<li class="page-item"><a class="page-link" href="#">3</a></li>
		<li class="page-item"><a class="page-link" href="#">»</a></li>
		</ul>
		</div>
	</div>
	
	
	
	
</body>
</html>