<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <%@include file="/WEB-INF/views/comm/config.jsp" %>   <!-- 부트스트랩 코드 -->
    
    
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">DocMall</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
    

      
 	  <c:forEach items="${catelist}" var="vo">
	      <li class="nav-item dropdown">
	      	<!-- 1차 -->
	        <a class="nav-link dropdown-toggle btncategory" href="${vo.c_code}" role="button" data-toggle="dropdown" aria-expanded="false">
	        ${vo.c_name}
	        </a>
	        <!-- 2차 -->
	        <div class="dropdown-menu subcategory"></div>
	      </li>
      </c:forEach>
         
         
    </ul>
  </div>
</nav>




</body>

<script>
 $(document).ready(function() {

	// [1차 카테고리 클릭시 2차 카테고리 출력]
	$('a.btncategory').on("click", function(e){
		e.preventDefault(); //a태그의 링크 제거
		let c_pcode = $(this).attr("href");  //사용자가 선택한 카테고리 값을 읽어오고 있다.
		let cur_a = $(this);  //이걸 아래쪽에가서 사용하면 인식을 못한다.
		//console.log("c_code", c_code);

		$.ajax({
			url : '/category/subcatelist',
			type : 'get',
			data : {c_pcode : c_pcode}, 	
			dataType : 'json',
			success : function(result) {
				let sub_code = "";
				for(let i = 0; i<result.length; i++){				
					sub_code += '<a class="dropdown-item" href="' + result[i].c_code + '">' + result[i].c_name +'</a>';
				}
				cur_a.next().empty();  //상단의 2차로 표기한 div태그를 가리킨다.
				cur_a.next().append(sub_code);

			}
		});
	});


	//[2차 카테고리 클릭 이벤트 설정]
	$("div.subcategory").on("click","a", function(e){
		e.preventDefault();
		console.log("2차 카테고리", $(this).attr("href"));
		console.log("2차 카테고리", $(this).html());

		//location.href = "상품리스트주소?"; 	이건 뒷부분에 마저 할 것.

	});

 });
</script>




</html>