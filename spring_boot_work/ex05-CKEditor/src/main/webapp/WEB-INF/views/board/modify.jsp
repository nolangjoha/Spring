<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
    
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

<!-- CKEditor4 -->
<script src="/js/ckeditor/ckeditor.js"></script>  <!-- 파일로 하는 방법 -->
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="card card-primary">
                <div class="card-header">
                <h3 class="card-title">글수정 폼</h3>
                </div>
                <form action="/board/modify" method="post">
                <div class="card-body">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Bno</label>
                        <input type="text" class="form-control" id="bno" name="bno" value="${boardVO.bno}" readonly>
                        <input type="hidden" name="pageNum" value="${cri.pageNum }">
	               		<input type="hidden" name="amount" value="${cri.amount }">
	               		<input type="hidden" name="type" value="${cri.type }">
	               		<input type="hidden" name="keyword" value="${cri.keyword }">
	                </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Title</label>
                        <input type="text" class="form-control" id="title" name="title" placeholder="Enter title" value="${boardVO.title}">
                    </div>
                    <div class="form-group">
                        <label for="content">Content</label>
                        <textarea type="text" class="form-control" rows="3" id="content" name="content" placeholder="Enter Content">${boardVO.content}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="writer">Writer</label>
                        <input type="text" class="form-control" id="writer" name="writer" placeholder="Enter writer"  value="${boardVO.writer}">
                    </div>

                <div class="card-footer">
                    <button type="submit" class="btn btn-primary">Modify</button>
                </div>
                </form>
                </div>  
        </div>
    </div>
</div>



<script>
	$(document).ready(function() {
	    // ckeditor 환경설정. 자바스크립트 Ojbect문법
	    var ckeditor_config = {   // 이 변수가 바로 아래 줄에 들어간다.
	            resize_enabled : false,   //입력창 늘리고 줄이고, true로 하면 늘릴 수 있음.
	            enterMode : CKEDITOR.ENTER_BR,  // 엔터를 누르면 br이 작동 되도록 해놨음.
	            shiftEnterMode : CKEDITOR.ENTER_P, // shift enter을 누르면 p태그가 작동 되도록 해놨음.
	            toolbarCanCollapse : true,
	            removePlugins : "elementspath", 
	            //업로드 탭기능추가 속성. CKEditor에서 파일업로드해서 서버로 전송클릭하면 , 이 주소가 동작된다.
	            filebrowserUploadUrl: '/board/imageupload' 
	    }
	
	    CKEDITOR.replace("content", ckeditor_config);  //content는 textarea의 content를 가리키는 것이다.
	
	    console.log("ckediotr 버전: ", CKEDITOR.version);  //ckediotr 버전:  4.12.1 (Standard) // 웹 > 검사 > console에서 확인할 수 있음. 버전등이 항상 바뀌니까 확인할 수 있도록
	  });
</script>

</body>
</html>