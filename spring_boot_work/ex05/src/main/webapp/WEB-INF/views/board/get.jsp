<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    table, td, th {
      border: 1px solid;
    }
    
    table {
      width: 100%;
      border-collapse: collapse;
    }
    </style>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<!--<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


<!--1) Include Handlebars from a CDN --> <!--핸들바-->
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<!--2) Handlebar Template-->  <!--댓글표시-->
<!--rno_{{rno}}이름을 댓글번호화 합쳐 유일하게 만들었음. id값으로 넣어주자-->
<script id="reply-template" type="text/x-handlebars-template">
   <table id="replytable">
    {{#each .}}
    <tr>        
        <td>[<span id="rno_{{rno}}">{{rno}}</span>] <span id="replyer_{{rno}}">{{replyer}}</span> [{{convertDate replydate}}]</td>
    </tr>
    <tr>
        <td><span id="retext_{{rno}}">{{retext}}</span></td>
    </tr>
    <tr>
        <td>
            <button type="button" name="btnReplyModify" data-rno="{{rno}}" class="btn btn-primary btn-sm">수정</button>
            <button type="button" name="btnReplyDelete" data-rno="{{rno}}" class="btn btn-danger btn-sm">삭제</button>
        </td>  
    </tr>
    {{/each}}                  
   </table>
</script>

<script>
  //Handlebar Template에서 사용 할 사용자 정의 함수 작업
  Handlebars.registerHelper("convertDate", function(replydate){
    const date = new Date(replydate);  //날짜데이터 객체 만들기
    let month = (date.getMonth()+1 < 10 ? "0" + (date.getMonth()+1) : date.getMonth()+1);
    let day = (date.getDate() < 10 ? "0" + (date.getDate()) : date.getDate());

    return date.getFullYear() + "/" + month + "/" + day; 
  });
</script>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="card card-primary">
                <div class="card-header">
                <h3 class="card-title">게시물 조회</h3>
                </div>
                <form id="actionForm" action="/board/list" method="get">
                    <input type="hidden" name="bno" value="${boardVO.bno }">
                	<input type="hidden" name="pageNum" value="${cri.pageNum }">
               		<input type="hidden" name="amount" value="${cri.amount }">
               		<input type="hidden" name="type" value="${cri.type }">
               		<input type="hidden" name="keyword" value="${cri.keyword }">
                </form>
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
                    <button type="button" class="btn btn-primary" onclick="fn_modify()">Modify</button>
                    <button type="button" class="btn btn-danger" onclick="fn_delete(${boardVO.bno})">Delete</button>
                    <button type="button" class="btn btn-primary" onclick="fn_list()">List</button>
                </div>
                </div>
                </form>
                </div>  
        </div>
    </div>
    <div class ="row">
        <div class="col">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="btnReplyWrite">
                댓글쓰기
            </button>
            <!-- 댓글목록 위치 -->
            <div id="replyList"></div>
            <!-- 댓글페이지 위치 -->
            <div id="replyPager"></div>
        </div>    
    </div>
</div>

<script>
    let actionForm = document.getElementById("actionForm"); //form태그 참조

    function fn_modify() {
        actionForm.action = "/board/modify";
        actionForm.submit();
    }

    function fn_delete(bno){
        if(!confirm(bno + "번 게시물을 삭제하겠습니까?")) return;
    
        //location.href = "/board/delete?bno=" + bno;

        //<form id="actionForm" action="/board/list" method="get">
        //actionForm.setAttribute("action", "/board/delete");  //주소 바꿔 놓기 action폼의 /board/delete로 감
        actionForm.action = "/board/delete";  //criteria에서 getListLink 작업후 actionForm.setAttribute("action", "/board/delete");대신 써본것
        actionForm.submit();
    }

    function fn_list(){
	    actionForm.setAttribute("action", "/board/list");
        actionForm.submit();
    }



     //<jQuery 작업을 하기위한 기본.>
     //ready()이벤트 메서드 : 브라우저가 모든 웹페이지의 내용(태그들)을 읽고 시작되는 이벤트
    $(document).ready(function() {

        // [댓글페이지번호 클릭이벤트]
        //아래 선택자에 참조되는 태그가 동적으로 생성된 경우에는 이벤트 설정 불가.(매우중요)
        /*
        $("nave ul li a").on("click", fuction(){

        });
        */

        //동적태그에 이벤트를 설정하는 경우
        //$("정적태그 선택자").on("이벤트명", "동적태그선택자", fuction(){});
        $("div#replyPager").on("click", "li a", function(e) {
            e.preventDefault(); //a태그의 href속성의 링크기능 없애기
            //클릭했던 a태그를 참조
            replyPage = $(this).attr("href");
            
            //console.log("페이지", replyPage);  //확인용작업

            url = "/replies/pages/" + bno + "/" + replyPage;
            getPage(url);
        });

        // [댓글쓰기 대화상자 버튼 클릭]
        // $("댓글쓰기 버튼태그를 참조하는 선택자") : id값 btnReplyWrite을가진 태그를 참조하게 된다.
        // document.getElementById("btnReplyWrite") 기능과 유사.
        $("#btnReplyWrite").on("click", function(){
            //console.log("댓글버튼을 클릭했다."); //웹페이지>검사>기능확인해보면서 console부분 확인

            //댓글작성자, 내용 초기화
            $("#reply_rno").html("");
            $("#replyer").val("");
            $("#retext").val("");

            //모달버튼 화면보임/숨김작업
            // name에 btnModalReply값을 가지고 있는 버튼태그를 의미
            $("button[name='btnModalReply']").hide(); // 등록, 수정, 삭제3개 버튼을 화면에서 숨김
            //등록, 수정, 삭제 버튼을 모두 숨긴 상태로 다시 수정만 보여주게 하자
            $("#btnModalReplySave").show();

            $('#replyDialog').modal('show')

        });

        // 1)대화상자 댓글등록
        $("#btnModalReplySave").on("click", function(){
            //console.log("댓글등록 했다."); //확인용

            //$("#replyer").val() : <input type='text' id='replyer'>태그의 value값
            let replyer = $("#replyer").val();
            let retext = $("#retext").val(); 

            //댓글데이터를 json포맷으로 서버에 전송
            //게시글 번호가 ${boardVO.bno}로 만들어졌다는걸 기억하자.
            //서버에서 ${boardVO.bno}먼저 읽혀져서 게시글 번호가 먼저 출력될 것이다.
            //replyer : replyer, retext : retext} : 자바스크립트 object문법이다.
            //w3cschool가서 문법을 다시 확인해보자: 
            // 1)댓글데이터를 자바스크립트의 Object문법으로 표현
            let replyData = {bno : ${boardVO.bno }, replyer : replyer, retext : retext};

            // 2) 댓글데이터를 json으로 변환하여 서비스에 전송(json은 표현법이며 xml과 비교해서 공부하자)
            //console.log(JSON.stringify(replyData));
            

            $.ajax({
                type : 'post',
                url : '/replies/new',  //url : '댓글저장 매핑주소'
                headers : {
                    "Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"
                },
                dataType: 'text', //스프링주소의 메서드 리턴타입
                data: JSON.stringify(replyData), //서버로 전송할 JSON데이터
                success: function(data){
                    if(data == "success") {  //success는 replyController에서 넘어온다. 대소문자 주의
                        alert("댓글 등록됨.");
                        let url = "/replies/pages/" + bno + "/" + replyPage; 
                        getPage(url);

                        //댓글작성자, 내용초기화
                        $("#replyer").val("");
                        $("#retext").val("");

                        //modal diaLog화면에서 사라짐.
                        $("#replyDialog").modal('hide');
                    }
                }
            });

        });
    
        // 2) 모달 대화상자댓글 수정
        $("#btnModalReplyUpdate").on("click", function(){

            //$("#replyer").val() : <input type='text' id='replyer'>태그의 value값
            let rno = $("#reply_rno").html();
            let replyer = $("#replyer").val();
            let retext = $("#retext").val(); 

            // 1)댓글수정 데이터를 자바스크립트의 Object문법으로 표현
            let replyData = {rno : $("#reply_rno").html(), replyer : replyer, retext : retext};

            // 2) 댓글데이터를 json으로 변환하여 서비스에 전송(json은 표현법이며 xml과 비교해서 공부하자)
            //console.log(JSON.stringify(replyData));
            
            //return;

            $.ajax({  
                type : 'put',  //댓글 수정작업은 REST API에서는 put, patch요청방식 사용.
                url : '/replies/modify',  //url : '댓글저장 매핑주소'
                headers : {  //웹브라우저는 원래 get,post방식만 지원한다. 하지만 아래와 같이 자바스크립트로 
                            //작성을 해주면 마치 서버측에서 웹브라우저가 다른방식을 지원한느 것처럼 사용할수있다(트릭작업)
                    "Content-Type" : "application/json", "X-HTTP-Method-Override" : "PUT"
                },
                dataType: 'text', //스프링주소의 메서드 리턴타입
                data: JSON.stringify(replyData), //서버로 전송할 JSON데이터
                success: function(data){
                    if(data == "success") {  
                        alert("댓글 수정됨..");
                        let url = "/replies/pages/" + bno + "/" + replyPage; 
                        getPage(url);

                        //댓글작성자, 내용초기화
                        $("#reply_rno").html("");  //"" 공백
                        $("#replyer").val("");     //""공백
                        $("#retext").val("");      //""공백

                        //modal diaLog화면에서 사라짐.
                        $("#replyDialog").modal('hide');
                    }
                }
            });

        });

        // 3) 모달 대화상자댓글 삭제
        $("#btnModalReplyDelete").on("click", function(){

            // 1)댓글삭제 데이터(댓글번호만 있으면 된다.)
            //let replyData = {rno : $("#reply_rno").html()};

            // 2) 댓글데이터를 json으로 변환하여 서비스에 전송(json은 표현법이며 xml과 비교해서 공부하자)
            //console.log(JSON.stringify(replyData));

            //return;

            $.ajax({  
                type : 'delete',  //댓글 수정작업은 REST API에서는 delete요청방식 사용.
                url : '/replies/delete/'+ $("#reply_rno").html(),  //url : '댓글저장 매핑주소'
                headers : {  //웹브라우저는 원래 get,post방식만 지원한다. 하지만 아래와 같이 자바스크립트로 
                            //작성을 해주면 마치 서버측에서 웹브라우저가 다른방식을 지원한느 것처럼 사용할수있다(트릭작업)
                    "Content-Type" : "application/json", "X-HTTP-Method-Override" : "DELETE"
                },
                dataType: 'text', //스프링주소의 메서드 리턴타입
              //  data: JSON.stringify(replyData), //서버로 전송할 JSON데이터
                success: function(data){
                    if(data == "success") {  
                        alert("댓글 삭제됨..");
                        let url = "/replies/pages/" + bno + "/" + replyPage; 
                        getPage(url);

                        //댓글작성자, 내용초기화
                        $("#reply_rno").html("");  //"" 공백
                        $("#replyer").val("");     //""공백
                        $("#retext").val("");      //""공백

                        //modal diaLog화면에서 사라짐.
                        $("#replyDialog").modal('hide');
                    }
                }
            });

        });


        // [댓글 수정버튼 클릭시] 
        /*
        $("정적태그선택자").on("이벤트명","동적태그선택자", function(){

        });
        */
        $("div#replyList").on("click","button[name='btnReplyModify']", function(){
            //console.log("댓글 수정버튼 클릭");
            //$(this) : 클릭한 수정버튼 태그 참조
            let rno = $(this).data("rno");  //<button data-rno="500">수정</botton>
            let replyer = $(this).parents("table#replytable").find("#replyer_" + rno).html(); 
            let retext = $(this).parents("table#replytable").find("#retext_" + rno).html(); 

            //console.log("rno", rno);
            //console.log("replyer", replyer);
            //console.log("retext", retext);

            $("button[name='btnModalReply']").hide();
            $("#btnModalReplyUpdate").show();

            //모달 대화상자에 값을 출력하는 작업
            $("#reply_rno").html(rno);  // 일반태그 <span>은 html()메서드 사용

            //<input>태그는 val()메서드 사용
            $("#replyer").val(replyer);
            $("#retext").val(retext);  

            $("#replyDialog").modal('show');  

        });

        // [댓글 삭제버튼 클릭시] 
        $("div#replyList").on("click","button[name='btnReplyDelete']", function(){
            //console.log("댓글 삭제버튼 클릭");
            //$(this) : 클릭한 삭제버튼 태그 참조
            let rno = $(this).data("rno");  //<button data-rno="500">수정</botton>
            let replyer = $(this).parents("table#replytable").find("#replyer_" + rno).html(); 
            let retext = $(this).parents("table#replytable").find("#retext_" + rno).html(); 

            //console.log("rno", rno);
            //console.log("replyer", replyer);
            //console.log("retext", retext);

            $("button[name='btnModalReply']").hide();
            $("#btnModalReplyDelete").show();

            //모달 대화상자에 값을 출력하는 작업
            $("#reply_rno").html(rno);  // 일반태그 <span>은 html()메서드 사용

            //<input>태그는 val()메서드 사용
            $("#replyer").val(replyer);
            $("#retext").val(retext);  

            $("#replyDialog").modal('show');  

        });

    
            // 2) 대화상자(modal)댓글 수정
        $("#btnModalReplyUpdate").on("click", function(){

            //$("#replyer").val() : <input type='text' id='replyer'>태그의 value값
            let rno = $("#reply_rno").html();
            let replyer = $("#replyer").val();
            let retext = $("#retext").val(); 

            // 1)댓글수정 데이터를 자바스크립트의 Object문법으로 표현
            let replyData = {rno : $("#reply_rno").html(), replyer : replyer, retext : retext};

            // 2) 댓글데이터를 json으로 변환하여 서비스에 전송(json은 표현법이며 xml과 비교해서 공부하자)
            //console.log(JSON.stringify(replyData));
            
            //return;

            $.ajax({  
                type : 'put',  //댓글 수정작업은 REST API에서는 put, patch요청방식 사용.
                url : '/replies/modify',  //url : '댓글저장 매핑주소'
                headers : {  //웹브라우저는 원래 get,post방식만 지원한다. 하지만 아래와 같이 자바스크립트로 
                            //작성을 해주면 마치 서버측에서 웹브라우저가 다른방식을 지원한느 것처럼 사용할수있다(트릭작업)
                    "Content-Type" : "application/json", "X-HTTP-Method-Override" : "PUT"
                },
                dataType: 'text', //스프링주소의 메서드 리턴타입
                data: JSON.stringify(replyData), //서버로 전송할 JSON데이터
                success: function(data){
                    if(data == "success") {  
                        alert("댓글 수정됨..");
                        let url = "/replies/pages/" + bno + "/" + replyPage; 
                        getPage(url);

                        //댓글작성자, 내용초기화
                        $("#reply_rno").html("");  //"" 공백
                        $("#replyer").val("");     //""공백
                        $("#retext").val("");      //""공백

                        //modal diaLog화면에서 사라짐.
                        $("#replyDialog").modal('hide');
                    }
                }
            });

        });   

    });


    // [게시물 글번호 확보 작업](특정게시글에 대한 댓글 목록을 가져오므로)
    let bno = ${boardVO.bno};  //게시물번호 511에 일단 댓글데이터 넣어놨음.
    let replyPage = 1; //댓글목록의 1번째 페이지를 보여줌.(전역변수)
    let url = "/replies/pages/" + bno + "/" + replyPage; //"" 내부에 댓글목록과 댓글 페이지 정보를 요청하는 매핑주소를 작성

    //console.log("url", url); //스프링 툴에서 get 파일이 열려있어야 반영// 확인용 작업이므로 주석

    //아래fuction으로 만든 함수 호출// 위의 let url을 받아서 fuction getPage(url) > getJSON(url,)로 넣어준다.
    getPage(url);


     // [댓글목록 함수]
    //매개변수 이름 다르게 해줘도 상관 없음. 근데 저자리에 let url값이 들어올 예정임. 그래서 url로 이름지음.
    function getPage(url){
        
        
        //ajax기능 지원
        $.getJSON(url, function(data){  //전체정보는 data로 관리, 여기서 관리하고 있는 요소는 2가지(list,pageMaker)
            
            /*
            //data.list      data.pageMaker 로 사용된다. (controller에서 작업한 댓글목록작업의 list, 페이징 작업의 page)
            console.log("list", data.list);  //확인용작업
            console.log("pageMaker", data.pageMaker);  //확인용작업

            // 1) "자바스크립트"로 댓글 데이터를 하나씩 개별로 접근해서 불러오게 하는 작업
            let result = "";

            for(let i=0; i<data.list.length; i++){
                result += "댓글번호 :" + data.list[i].rno + "<br>";
                result += "댓글내용 :" + data.list[i].retext + "<br>";
            };

            //화면출력
            //이 한줄이 앞에 태그를 가리켜준다. // //css3문법 : html태그갸 id면 #, class면 '.'으로 참조 
            $("#replyList").html(result);

            */
            displayReplyData(data.list, $("#replyList"), $("#reply-template"));
            displayReplyPaging(data.pageMaker, $("#replyPager"));

        });
    }

    //(함수)댓글목록 데이터바인딩
    // replyData : 댓글목록 데이터
    // target : 댓글목록이 출력될 태그위치
    // template : 댓글목록 UI핸들바 템플릿
    // 핸들바 사용을 위한 교과서적인 코딩
    function displayReplyData(replyData, target, template){   //template 에는 위에 있는 템플릿 스크립트 정보가 들어올것이다.
        let templateObj = Handlebars.compile(template.html());  // template.html(): 위의 템플렛의 html코드의 내용을 읽어온다. 
                                                                // Handelbars.compile: compile로 문법검사 후 templateObj에 참조된다. ( {{}}가 핸들바 문법이다.)
        let replyHtml = templateObj(replyData);  //댓글목록데이터와 댓글 템플릿이 결합되어 replyHtml에 참조된다.

        //console.log("댓글목록:", replyHtml);

        // target은 <div id="replyList"></div> 위치를 참조할거다.
        target.empty();         //empty: target변수가 참조하는 태그위치에 내용을 지운다.
        target.append(replyHtml);  // append: target변수가 참조하는 태그위치에 자식레벨로 replyHtml변수의 내용을 추가한다.
    }

    // [댓글페이징 작업]
    // pageData : 페이징에 필요한 데이터
    // target : 페이지가 삽입 될 위치
    function displayReplyPaging(pageData, target){
        
        /*부트스트랩에서 원하는 페이지UI를 미리 가져왔다.(하나하나 치면 오히려 오타나니까)
        <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
        </nav>        
        */
        let pageStr = '<nav aria-label="Page navigation example">'; //복사해온 코드가 ""을 쓰고있어서 감싸는건 ''로
            pageStr += '<ul class="pagination">';

            // 1)이전표시여부 작업(지저분한 직업이나 안 할 수 없음..)
            //pageData에는 웹의 pageMaker의 정보들이 들어올 예정.
            if(pageData.prev) {
                pageStr += '<li class="page-item">';
                pageStr += '<a class="page-link" href="' + (pageData.startPage - 1) + '">Previous</a></li>';
            }

            // 2)페이지번호 작업
            for(let i=pageData.startPage; i <= pageData.endPage; i++){
                let curPageClass = (pageData.cri.pageNum == i) ? 'active' : '';
                pageStr += '<li class="page-item ' + curPageClass+ '">';
                pageStr += '<a class="page-link" href="' + i + '">' + i +'</a></li>';
            }

            // 3)다음페이지 여부 작업
            if(pageData.next) {
                pageStr += '<li class="page-item">';
                pageStr += '<a class="page-link" href="' + (pageData.endPage + 1) + '">Next</a></li>';
            }

            pageStr += '</ul></nav>';
            
            //target변수가 참조하는 태그내용에 pageStr변수의 값을 삽입(대입)
            target.html(pageStr);

    }
</script>


<!--댓글쓰기 기능 작업-->
  <!-- Modal -->
  <div class="modal fade" id="replyDialog" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">댓글<span id="reply_rno"></span></h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <label for="replyer">writer</label>
                <input type="text" class="form-control" id="replyer" placeholder="Enter writer...">
            </div>
            <div class="form-group">
                <label for="retext">content</label>
                <textarea class="form-control" id="retext" rows="3"></textarea>
            </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" name="btnModalReply" id="btnModalReplySave">등록</button>
          <button type="button" class="btn btn-primary" name="btnModalReply" id="btnModalReplyUpdate">수정</button>
          <button type="button" class="btn btn-danger" name="btnModalReply" id="btnModalReplyDelete">삭제</button>
        </div>
      </div>
    </div>
  </div>

</body>
</html>