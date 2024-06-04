<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <%@include file="/WEB-INF/views/comm/config.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/views/comm/header.jsp" %>
	
	<div class="card">
		<div class="card-header">
		<h3 class="card-title">Bordered Table</h3>
		</div>
		
		<div class="card-body">
		<table class="table table-bordered">
			<thead>
				<tr> 
	                <th style="width: 10%">u_id</th>
	                <th style="width: 10%">u_name</th>
	                <th style="width: 15%">u_email</th>
	                <th style="width: 10%">u_zip_code</th>
	                <th style="width: 15%">u_addr</th>
	                <th style="width: 10%">u_addrdetail</th>
	                <th style="width: 10%">u_phone</th>
	                <th style="width: 10%">u_regdate</th>
				</tr>
			</thead>
			
<!-- u_id, u_pwd, u_name, u_email, u_zip_code, u_addr, u_addrdetail, u_phone, u_regdate -->				
			
			<tbody>
				<c:forEach items="${memlist}" var="UserInfoVO">
					<tr>
						<td>${UserInfoVO.u_id}</td>
						<td>${UserInfoVO.u_name}</td>
						<td>${UserInfoVO.u_email}</td>
						<td>${UserInfoVO.u_zip_code}</td>
						<td>${UserInfoVO.u_addr}</td>
						<td>${UserInfoVO.u_addrdetail}</td>
						<td>${UserInfoVO.u_phone}</td>
						<td>${UserInfoVO.u_regdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	
		<div class="card-footer clearfix">
			<ul class="pagination pagination-sm m-0 float-right">
		        <c:if test="${pageMaker.next}">
	        		<li class="page-item"><a class="page-link" href="/board/list?type=${pageMaker.cri.type }&keyword=${pageMaker.cri.keyword }&pageNum=${pageMaker.endPage+1}&amount=${pageMaker.cri.amount}">[다음]</a></li>
	        	</c:if> 
			</ul>
		</div>
	</div>


</body>
</html>