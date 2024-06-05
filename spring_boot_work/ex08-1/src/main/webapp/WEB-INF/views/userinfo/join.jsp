<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en" class="h-100">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>Sticky Footer Navbar Template · Bootstrap v4.6</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/sticky-footer-navbar/">

    <%@include file="/WEB-INF/views/comm/config.jsp" %>


    <!-- Favicons -->
<link rel="apple-touch-icon" href="/docs/4.6/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="
https://getbootstrap.com/docs/4.6/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/4.6/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
<link rel="icon" href="/docs/4.6/assets/img/favicons/favicon.ico">
<meta name="msapplication-config" content="/docs/4.6/assets/img/favicons/browserconfig.xml">
<meta name="theme-color" content="#563d7c">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    <!-- Custom styles for this template -->
    <link href="/sticky-footer-navbar.css" rel="stylesheet">
  </head>
  <body class="d-flex flex-column h-100">
    
<%@include file="/WEB-INF/views/comm/header.jsp" %>



<!-- Begin page content -->
<main role="main" class="flex-shrink-0">
  <div class="container">
    <h1 class="mt-5">Register</h1>
    <div class="row">
      <div class ="col">

        <div class="card card-info">
          <div class="card-header">
          <h3 class="card-title">Join Form</h3>
          </div>
          
          <!-- --u_id, u_pwd, u_name, u_email, u_zip_code, u_addr, u_addrdetail, u_phone, u_regdate  -->
          <!-- U_ID, U_PWD, U_NAME, U_EMAIL, U_ZIP_CODE, U_ADDR, U_ADDRDETAIL, U_PHONE, U_REGDATE -->
          <!-- id="joinForm" -->
          <form class="form-horizontal" id="joinForm" action="/userinfo/join" method="post">
          <div class="card-body">
              <div class="form-group row">
                  <label for="u_id" class="col-sm-2 col-form-label">ID</label>
                  <div class="col-sm-4">
                  <input type="text" class="form-control" id="u_id" name="u_id" placeholder="아이디">
                  </div>
              <div class="col-sm-2">
                <button type="button" class="btn btn-outline-primary" id="btnIDCheck">ID Check</button>
              </div>
              <span for="u_id" class="col-sm-4 col-form-label" id="idCheckMsg" style="color : red"></span>
              </div>
              <div class="form-group row">
                  <label for="u_pwd" class="col-sm-2 col-form-label">PWD</label>
                  <div class="col-sm-4">
                  <input type="password" class="form-control" id="u_pwd" name="u_pwd" placeholder="비밀번호">
                  </div>
                  <label for="u_confirm_pwd" class="col-sm-2 col-form-label">PWD 확인</label>
                  <div class="col-sm-4">
                  <input type="password" class="form-control" id="u_confirm_pwd" placeholder="비밀번호 확인">
                  </div>
              </div>
              <div class="form-group row">
                  <label for="u_name" class="col-sm-2 col-form-label">NAME</label>
                  <div class="col-sm-10">
                  <input type="text" class="form-control" id="u_name" name="u_name" placeholder="이름">
                  </div>
              </div>
              <div class="form-group row">
                  <label for="u_email" class="col-sm-2 col-form-label">EMAIL</label>
                  <div class="col-sm-3">
                  <input type="text" class="form-control" id="u_email" name="u_email" placeholder="이메일">
                  </div>
                  <div class="col-sm-2">
                    <button type="button" class="btn btn-outline-primary" id="btnMailAuthcode">메일인증 요청</button>
                  </div>
                  <div class="col-sm-3">
                    <input type="text" class="form-control" id="u_authcode" placeholder="인증코드 입력">
                  </div>
                  <div class="col-sm-2">
                    <button type="button" class="btn btn-outline-primary" id="btnConfirmAuth">인증확인</button>
                  </div>
              </div>
              <div class="form-group row">
                  <label for="u_zip_code" class="col-sm-2 col-form-label">우편번호</label>
                  <div class="col-sm-4">
                  <input type="text" class="form-control" id="sample2_postcode" name="u_zip_code" placeholder="우편번호">
                  </div>
                  <div class="col-sm-4">
                    <button type="button" class="btn btn-outline-primary" onclick="sample2_execDaumPostcode()">우편번호</button>
                  </div>
              </div>
              <div class="form-group row">
                  <label for="u_addr" class="col-sm-2 col-form-label">주소</label>
                  <div class="col-sm-10">
                  <input type="text" class="form-control" id="sample2_address" name="u_addr" placeholder="주소">
                  </div>
              </div>
              <div class="form-group row">
                  <label for="u_addrdetail" class="col-sm-2 col-form-label">세부주소</label>
                  <div class="col-sm-10">
                  <input type="text" class="form-control" id="sample2_detailAddress" name="u_addrdetail" placeholder="세부 주소">
                  <input type="hidden" id="sample2_extraAddress" placeholder="참고항목">
                </div>
              </div>
              <div class="form-group row">
                  <label for="u_phone" class="col-sm-2 col-form-label">연락처</label>
                  <div class="col-sm-10">
                  <input type="text" class="form-control" id="u_phone" name="u_phone" placeholder="연락처">
                  </div>
              </div>
          </div>
          
          <div class="card-footer">   <!-- id="btnJoin" -->
            <button type="button" class="btn btn-info" id="btnJoin">Sign up</button>
            <button type="reset" class="btn btn-default float-right">Cancel</button>
          </div>
          
          </form>
        </div>

      </div>
    </div>
  </div>
</main>



<%@include file="/WEB-INF/views/comm/footer.jsp" %>






<!-- [우편번호 기능 daum post api]-->
<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 화면을 넣을 element
    var element_layer = document.getElementById('layer');

    function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
    }

    function sample2_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample2_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample2_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample2_postcode').value = data.zonecode;
                document.getElementById("sample2_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample2_detailAddress").focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_layer.style.display = 'none';
            },
            width : '100%',
            height : '100%',
            maxSuggestItems : 5
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
    }

    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
    function initLayerPosition(){
        var width = 300; //우편번호서비스가 들어갈 element의 width
        var height = 400; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 5; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    }
</script>


<!--[제이쿼리사용]-->
<script>
    $(document).ready(function(){
        
        let useIDCheck = false;  // 아이디 중복체크 기능을 사용했는지 유무 확인(전역변수)
          
        // [1. 아이디 체크 기능]
        $("#btnIDCheck").on("click", function(){
            
            //회원가입칸에 입력한 값을 읽어온다.
            if($("#u_id").val() == "") {
                alert("아이디를 입력하세요");
                $("#u_id").focus();
                
                return; //아이디 값이 들어가지 않았을 때 아래가 진행되면 안되므로 return을 넣어줌.
            } 
            
            // [DB와 연결]
            $.ajax({
                url: '/userinfo/idCheck',   //주소는 소문자를 권장한다. (대문자를 써도 문제가 되진 않음.) 
                type: 'get',
                data: {u_id : $("#u_id").val()}, //여기 있는 u_id는 DB에 있는 u_id와 반드시 일치해야 한다.  
                dataType: 'text',  // 이자리는 text(기본값), html, xml, json도 올수 있는 자리다.
                success: function(result) {  //result에는 idUse값 혹은 'no' 둘중 하나가 들어온다. 
                    if(result == "yes"){
                        alert("사용 가능한 아이디 입니다.")
                        $("#idCheckMsg").html("※ 사용 가능한 아이디 입니다.");
                    }else{
                        alert("중복된 아이디 입니다.")
                        $("#idCheckMsg").html("※ 중복된 아이디 입니다.");
                        $("#u_id").val(""); //val값에 아무것도 안써있으면 u_id값을 읽어오고, 무언가 있으면 그 값을 u_id에 넣어준다. 여기선 다시 공백으로 만들기 위해 ""을 넣어준다.
                        $("#u_id").focus();
                        
                    }
                }
            });
        });


        // [2. 이메일 인증코드 요청]
        $("#btnMailAuthcode").on("click", function() {

            if($("#u_email").val() == "") {
                alert("메일을 입력하세요.");
                $("#u_email").focus();
                return;
            }

            $.ajax({
                url: '/email/authcode',
                type:'get',
                data: {receiverMail : $("#u_email").val(), type : "emailJoin"},
                dataType: 'text',
                success: function(result) {
                  if(result =="success"){
                    alert("메일로 인증코드가 발급되었습니다.")
                  }
                }
              
            });
        });


        // [3. 이메일 인증확인]
        $("#btnConfirmAuth").on("click", function() {
            
            if($("#u_authcode").val() == "") {
                alert("인증코드를 입력하세요");
                $("#u_authcode").focus();
                return;
            }

            $.ajax({
                url : '/email/confirm_authcode',
                type : 'get',
                data : {authcode : $("#u_authcode").val()},
                dataType: 'text',
                success : function(result){
                    if(result == "success") {
                        alert("인증이 확인되었습니다");
                    }else if(result == "fail") {
                        alert("인증코드 값을 확인해주세요.");
                    }else if(result == "request") {
                        alert("인증코드가 소멸되었습니다. \n인증요청메일을 다시 진행해주세요.")
                    }

                },
                //스프링쪽에서 에러가 발생해서 클라이언트쪽에 표시될 때 아래코드가 실행 됨.
                error : function(){

                }
            });
        });


        // [4. 회원가입 클릭] 
        // 아래 두 코드는 양립할 수 없다.
        // 1) <button type="submit" class="btn btn-info" id="btnJoin">Sign up</button>  --> click이벤트 사용안됨 // 폼 submit이벤트 사용       
        /*
        $('#joinForm').on("submit", function(e){
            e.preventDefault();
            console.log("submit event");
            return;
        });
        */

        // 2) <button type="button" class="btn btn-info" id="btnJoin">Sign up</button>  --> click이벤트 사용가능        
        $('#btnJoin').on("click", function(){
            console.log("click event");

            //회원가입 유효성검사.
            $("#joinForm").submit();
        });













    });  //ready event end
</script>



  </body>
</html>
    