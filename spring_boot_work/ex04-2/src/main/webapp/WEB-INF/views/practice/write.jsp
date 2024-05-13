<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<div class = "container-fluid">
	<div class="row">
		<div class="col-12">
		
			<div class="card card-primary">
			<div class="card-header">
			<h3 class="card-title">글쓰기 폼</h3>
			</div>	
			<form action="/practice/write" method="post">
				<div class="card-body">
					<div class="form-group">
						<label for="exampleInputPassword1">Title(제목)</label>
						<input type="text" class="form-control" id="title" placeholder="Enter title">
					</div>
					<div class="form-group">
						<label for="content">Content(내용)</label>
						<input type="text" class="form-control" row="3" id="content" name ="content" placeholder="Enter Content">
					</div>
					<div class="form-group">
						<label for="writer">Writer(작성자)</label>
						<input type="text" class="form-control" id="writer" name="writer" placeholder="Enter writer">
					</div>					
				</div>
				
				<div class="card-footer">
				<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>
			</div>		
		
		</div>
	</div>
</div>



</body>
</html>