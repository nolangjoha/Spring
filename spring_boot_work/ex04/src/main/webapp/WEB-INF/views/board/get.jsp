<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
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
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="card card-primary">
                <div class="card-header">
                <h3 class="card-title">게시물 조회</h3>
                </div>
                <form action="/board/get" method="post">
                <div class="card-body">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Title</label>
                        <input type="text" class="form-control" id="bno" name="bno" readonly value="${boardVO.bno}">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Title</label>
                        <input type="text" class="form-control" id="title" name="title" readonly value="${boardVO.title}">
                    </div>
                    <div class="form-group">
                        <label for="content">Content</label>
                        <textarea type="text" class="form-control" rows="3" id="content" name="content" readonly >"${boardVO.content}"</textarea>
                    </div>
                    <div class="form-group">
                        <label for="writer">Writer</label>
                        <input type="text" class="form-control" id="writer" name="writer" readonly value="${boardVO.writer}">
                    </div>

                    <div class="form-group">
                        <label for="writer">Regdate</label>
                        <input type="text" class="form-control" id="regdate" name="regdate" readonly value="<fmt:formatDate value="${boardVO.regdate}" pattern="yyyy-MM-dd"/>">
                    </div>

                    <div class="form-group">
                        <label for="writer">Updatedate</label>
                        <input type="text" class="form-control" id="updatedate" name="updatedate" readonly value="<fmt:formatDate value="${boardVO.updatedate}" pattern="yyyy-MM-dd"/>">
                    </div>

                    <div class="form-group">
                        <label for="writer">Viewcount</label>
                        <input type="text" class="form-control" id="viewcount" name="viewcount" readonly value="${boardVO.viewcount}">
                    </div>


                <div class="card-footer">
                    <a class="btn btn-primary" href="/board/modify?bno=${boardVO.bno}" role="button">Modify</a>
                    <button type="button" class="btn btn-danger" onclick="fn_delete(${boardVO.bno})">Delete</button>
                    <a class="btn btn-primary" href="/board/list" role="button">List</a>
                </div>
                </div>
                </form>
                </div>  
        </div>
    </div>
</div>

<script>
     function fn_delete(bno){
        if(!confirm(bno + "번 게시물을 삭제하겠습니까?")) return;
     
        location.href = "/board/delete?bno=" + bno;
     }
</script>

</body>
</html>