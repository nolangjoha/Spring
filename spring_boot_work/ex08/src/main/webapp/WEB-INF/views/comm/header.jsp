<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>    
    
<header>
  <!-- Fixed navbar -->
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="/">Ragister</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav mr-auto">
       <!--  인증 전 페이지 -->
       <c:if test="${sessionScope.login_status == null }">
	        <li class="nav-item active">       
	          <a class="nav-link" href="/userinfo/join">Join <!-- <span class="sr-only">(current)</span> --></a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="/userinfo/login">LogIn</a>
	        </li>
	   </c:if>     
         <!-- 인증 후 페이지 -->
         <c:if test="${sessionScope.login_status != null}">
         	<li class="nav-item">
	          <a class="nav-link" href="/userinfo/mypage">Mypage</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="/userinfo/logout">Logout</a>
	        </li>   
	        
	        <!-- 인터셉트 ajax요청 확인용 -->
	        <li class="nav-item">
	          <a class="nav-link" id="btnAjax" href="#">Ajax</a>
	        </li>   	      
	        
	        
	          
	     </c:if>                
        <li class="nav-item">
          <a class="nav-link disabled">Disabled</a>
        </li>
      </ul>
      <form class="form-inline mt-2 mt-md-0">
        <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>
    </div>
  </nav>
</header>

<script>
  $(document).ready(function(){

    // https://milenote.tistory.com/46 (참고)
    $("#btnAjax").on("click", function(e){
      e.preventDefault();  // <a>태그의 링크기능을 제거하는 기능
      
        $.ajax({
                url: '/userinfo/ajax',   
                type: 'get',
                dataType: 'text',  
                beforeSend : function(xhr) {  //XMLHttpRequest객체가 브라우저 내장. 
                  xhr.setRequestHeader("AJAX","true");  // (이름, 값) //이름은 임의적으로 지정
                },
                success: function(result) { 
                
                },
                error : function(xhr, status, error){
                  alert(status);
                  alert("로그인 페이지로 이동합니다.");
                  location.href = "/userinfo/login";
                }
            });
    });

  });

</script>



