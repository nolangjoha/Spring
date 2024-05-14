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
    <div class="card">
        <div class="card-header">
        <h3 class="card-title">Board List</h3>
        </div>
        
        <div class="card-body">
	        <table class="table table-bordered">
	        <thead>
	            <tr>
	                <th style="width: 10%">bno</th>
	                <th style="width: 30%">title</th>
	                <th style="width: 25%">writer</th>
	                <th style="width: 25%">regdate</th>
	                <th style="width: 10%">viewcount</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<c:forEach items="${list}" var="boardVO"> <!--${list}로 받아온 10건을 boardVO메모리에 변수로 넣어 쓰겠다. (+) boardVO메모리구조가 boardVO.java의 구조이다. 이를 사용하며 아래 이엘문법은 작성은 저리되었지만 작동은 get메서드가 동작된다. -->
		            <tr>  <!-- 변수 하나 받았으면 아래 코드를 진행하고 또 변수 받아와서 아래 코드 진행하고 반복 -->
			            <td>${boardVO.bno }</td>
			            <td><%--<a href="/board/get?bno=${boardVO.bno }&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}&pageNum=${pageMaker.cri.pageNum}&amount=${pageMaker.cri.amount}">${boardVO.title }</a> --%>
			            	<a class="move" href="${boardVO.bno }">${boardVO.title }</a>
			            </td>
			            <td>${boardVO.writer }</td>
			            <td><fmt:formatDate value="${boardVO.regdate }" pattern="yyyy-MM-dd"/></td>
			            <td>${boardVO.viewcount }</td>
		            </tr>
	            </c:forEach>
	        </tbody>
	        <form id="actionForm" action="/board/list" method="get">
	        	<input type="hidden" name="bno" value="">
	        	<input type="hidden" name="type" value="${pageMaker.cri.type}">
	        	<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
	        	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
	        	<input type="hidden" name="amount" value="${pageMaker.cri.amount}">   	
	        </form>
        </table>
        
        </div>
        
        <!-- [이전] 1	2	3	4	5	6	7	8	9	10	[다음]-->     
        <div class="card-footer clearfix">
            <div class ="float-left">
                <form id="serchForm" action="/board/list" method="get">
                    <div class="form-group">
                        <select name="type"> <!-- type: 컨트롤 파일의 Criteria cri의 type -->
                       		<option value="" <c:out value="${pageMaker.cri.type == null ? 'selected' : ''}"/>>-------</option>
                            <option value="T" <c:out value="${pageMaker.cri.type == 'T'.toString() ? 'selected' : ''}"/>>제목</option>
                            <option value="C" <c:out value="${pageMaker.cri.type == 'C'.toString() ? 'selected' : ''}"/>>내용</option>
                            <option value="W" <c:out value="${pageMaker.cri.type == 'W'.toString() ? 'selected' : ''}"/>>작성자</option>
                            <option value="TC" <c:out value="${pageMaker.cri.type == 'TC'.toString() ? 'selected' : ''}"/>>제목 or 내용</option>
                            <option value="TW" <c:out value="${pageMaker.cri.type == 'TW'.toString() ? 'selected' : ''}"/>>제목 or 작성자</option>
                            <option value="TWC" <c:out value="${pageMaker.cri.type == 'TWC'.toString() ? 'selected' : ''}"/>>제목 or 작성자 or 내용</option>
                        </select>       
                        <input type="text" name="keyword" placeholder="검색어를 입력하세요" value="${pageMaker.cri.keyword }">
                                            <!-- keyword : 컨트롤 파일의 Criteria cri의 keyword-->
                        <input type="hidden" name="pageNum" value="1">
                        <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                        <input type="submit" class="btn btn-primary" value="Search">
                    </div>
                </form>
            </div>
	        <ul class="pagination pagination-sm m-0 float-right"> <!--이엘문법들이 pageDTO에 있는 필드를 사용하고 있다._-->
	        	<c:if test="${pageMaker.prev}">
	        		<%-- 
					 <li class="page-item"><a class="page-link" href="/board/list?type=${pageMaker.cri.type }&keyword=${pageMaker.cri.keyword }&pageNum=${pageMaker.startPage-1}&amount=${pageMaker.cri.amount}">[이전]</a></li>
					 --%>
					 <li class="page-item"><a class="page-link" href="${pageMaker.startPage-1}">[이전]</a></li>
					</c:if>
	        	
		        <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="page">
		        <li class='page-item ${pageMaker.cri.pageNum == page ? "active" : ""}'>
		        	<%-- 
					<a class="page-link" href="/board/list?type=${pageMaker.cri.type }&keyword=${pageMaker.cri.keyword }&pageNum=${page }&amount=${pageMaker.cri.amount}">${page }</a></li>
		        	--%>
					<a class="page-link" href="${page }">${page }</a></li>
								<!-- pageNum, ampunt는 BoardController.java의 list매서드 Criteria cri파라미터 즉, Criteria.java의 필드명과 일치해야 한다.-->
		        				<!-- 강사님 정리 : /board/list 매핑주소 요청시 메서드의 파라미터가 Criteria cri로 되어있기 때문에
		        								클라이언트에서 pageNum과 amount 두 파라미터로 정보를 보내야 한다.-->
		        </c:forEach>
		        
		        <c:if test="${pageMaker.next}">
	        	<%-- 	
				<li class="page-item"><a class="page-link" href="/board/list?type=${pageMaker.cri.type }&keyword=${pageMaker.cri.keyword }&pageNum=${pageMaker.endPage+1}&amount=${pageMaker.cri.amount}">[다음]</a></li>
				--%>
				<li class="page-item"><a class="page-link" href="${pageMaker.endPage+1}">[다음]</a></li>
				</c:if> 
	        </ul>
        </div>
        </div>
</div>


<script>
	$(document).ready(function(){

		let actionForm = $("#actionForm");

		//게시물 목록 제목 클릭시
		$("a.move").on("click", function(e) {
			e.preventDefault(); //<a>태그의 href속성에 의한 링크기능 제거.
			
			let bno = $(this).attr("href"); 
			$("input[name='bno']").val(bno);

			actionForm.attr("action", "/board/get");
			actionForm.submit();
		});

		//페이지번호 클릭시.
		$("ul.pagination a.page-link").on("click", function(e) {
			e.preventDefault(); //<a>태그의 href속성에 의한 링크기능 제거.
		
			//페이지 작업.(actionForm에 숨겨놓은 pageNum에 사용자가 선택한 페이지를 넣어주는 것이다.)
			$("input[name='pageNum']").val($(this).attr("href"));

			actionForm.attr("action", "/board/list");
			actionForm.submit();
		});

		

	});

</script>




</body>
</html>