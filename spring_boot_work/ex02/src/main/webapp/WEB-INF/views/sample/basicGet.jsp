<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>basicGet</h1>
<h5>로그인 정보</h5>
<form action="/sample/basicPost" method="post">
	아이디 : <input type = "text" name="u_id"><br> <!-- u_id 파마리터로 데이터가 전송된다.  -->
	비밀번호 : <input type = "password" name="u_pw"><br>
	<input type = "submit" value="로그인">
</form>

</body>
</html>