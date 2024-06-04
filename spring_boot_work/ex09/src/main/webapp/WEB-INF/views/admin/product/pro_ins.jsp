<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  <!-- 이걸해야 모델객체를 쓸 수 있음. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/views/comm/config.jsp" %>
</head>
<body>

<select id="btn_category">
	<option>1차 카테고리 선택</option>
	<c:forEach items="${catelist}" var="cateVo">
		<option value="${cateVo.c_code}">${cateVo.c_name}</option>
	</c:forEach>
</select>
<select id="btn_subcategory">
	<option>2차 카테고리</option>
</select>

</body>
<script>
	$(document).ready(function() {
   
	// [1차 카테고리 클릭시 2차 카테고리 출력]
	$("select#btn_category").on("change", function(){
		//e.preventDefault(); //a태그의 링크 제거 - select태그니까 필요가 없어짐.
		let c_pcode = $(this).val();  //value="${cateVo.c_code}"을 읽어온다 //사용자가 선택한 카테고리 값을 읽어오고 있다.
		

		$.ajax({
			url : '/admin/product/subcatelist',
			type : 'get',
			data : {c_pcode : c_pcode}, 	
			dataType : 'json',
			success : function(result) {
				let sub_cate_str = "<option>2차 카테고리</option>";

				for(let i = 0; i<result.length; i++){	//1차 카테고리에서 선택한 항목에 대한 2차 카테고리 갯수다.			
					sub_cate_str += '<option value="' + result[i].c_code + '">' + result[i].c_name + '</option>'
				}
				console.log(sub_cate_str);

				$("select#btn_subcategory").empty(); //이전요소 제거
				$("select#btn_subcategory").append(sub_cate_str)

			}
		});
	});	
   


   
});
</script>
</html>