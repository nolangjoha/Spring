<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>form 태그를 이용한 파일</h3>
<!--form 태그의 enctype="application/x-www-form-urlencoded" 기본값. 생략함.  -->
<!-- 파일전송에서는 반드시 enctype="multipart/form-data"값을 사용해야 한다. -->
<form method="post" action="uploadFormAction" enctype="multipart/form-data"> 
	<input type="file" name="uploadFile" multiple>
	<button type="submit">전송</button>
	
	 
</form>

</body>
</html>