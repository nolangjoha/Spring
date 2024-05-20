<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .uploadResult {
       width: 100%;
       background-color: gray;
    }
    
    .uploadResult ul {
       display: flex;
       flex-flow: row;
       justify-content: center;
       align-items: center;
    }
    
    .uploadResult ul li {
       list-style: none;
       padding: 10px;
    }
    
    .uploadResult ul li img {
       width: 100px;
    }
    .bigPictureWrapper {
      position: absolute;
      display: none;
      justify-content: center;
      align-items: center;
      top:0%;
      width:100%;
      height:100%;
      background-color: gray; 
      z-index: 100;
    }
    
    .bigPicture {
      position: relative;
      display:flex;
      justify-content: center;
      align-items: center;
    }
 </style>
 <!-- jquery 사용 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>  
</head>
<body>
	<h3>ajax를 이용한 업로드</h3>
    <div class="bigPictureWrapper">
        <div class="bigPicture"></div>
    </div>

    <div class="uploadDiv">
        <input type="file" name="uploadFile" multiple>
    </div>

    <!--댓글은 핸들바써서 화면에 뿌려줌-->
    <!--파일전송을 uploadResult에 뿌려주겠다.-->
    <!-- 서버로부터 받아온 파일목록정보를 출력하는 위치 //헤딩작업은 ul태그 안에 append된다.-->
    <div class="uploadResult">
        <ul></ul>
    </div>
    <button id="uploadBtn">Upload</button>


<script>
    function checkExtension(fileName, fileSize){
        //파일의 확장자 제한, 파일크기 제한
        let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$"); //파일제한
        let maxSize = 5 * 1024 *1024; //5mb : 기본용량 제한

        if(fileSize >= maxSize){
            alert("파일 크기 용량 초과");
            return false;
        }

        if(regex.test(fileName)){
            alert("해당종류의 파일은 업로드 불가");
            return false;
        }

        return true;
    }
    
    function showImage(fileCalPath){
      //$(".원본이미지 보여줄 선택자").css("display","flex").show();
        $(".bigPictureWrapper").css("display","flex").show();  //실제 보여주는 위치는 bigPicture
        $(".bigPicture").html("<img src='display?fileName=" + fileCalPath + "'>").animate({width: '100%', height: '100%'}, 1000);

    }






    $(document).ready(function(){

        // [파일전송 클릭버튼]
        $("#uploadBtn").on("click", function(){
            let formData = new FormData();  //form태그에 해당하는 객체.
            
            //<input type="file" name="uploadFile" multiple>을 복수로 참조
            //ㅣet inputFile = $("input[#uploadFile]"); //단수의 의미 (위에서 uploadFile이 name이 아니라 id일 경우)
            let inputFile = $("input[name='uploadFile']"); //복수
            //inputFile[0] : 첫번재 태그
            //inputFile[0].files : 선택한 파일들
            
            let files = inputFile[0].files;

            console.log(files);
            
            for(let i = 0; i<files.length; i++){

            //files[i].name : 파일이름, files[i].size : 파일크기
            if(!checkExtension(files[i].name, files[i].size)){
                return false;
            }

            	//uploadFile은 스프링에서 참조하게 된다. 파일들을 uploadFile의 이름으로 보내는 것이다.
                formData.append("uploadFile", files[i]);
            }				

            $.ajax({
                url : '/upload/uploadAjaxAction',
                processData: false, //기본값 true. false 의미? key : value값의 구조를 QueryString(주소 ? 뒤에 나오는 문자열)으로 변환
                contentType: false, //기본값 true. false 의미? "application/x-www-form-urlencoded;charset=UTF-8"  "multipart/form-data" 인코딩을 사용하여 전송.
                data: formData, //스프링으로(서버)로 전송할 데이터
                type: 'post', // 요청방식
                dataType: 'json', //스프링에서 호출된 메서드의 리턴타입
                success: function(result){
                    
                    //업로드 파일 목록정보
                	for(let i=0; i < result.length; i++) {

                    console.log("날짜폴더명 : " + result[i].uploadPath);    
                    console.log("클라이언트에서 보낸 파일명: " + result[i].fileName);
                    console.log("중복되지 않는 파일명", result[i].uuid);
                    console.log("이미지파일 여부", result[i].image);
                	}  
                    
					showUploadedFile(result);
                }
            });
        });

        // [파일삭제 클릭(x클릭)]
        /*
        $("정적선택자").on("click", "동적선택자", function() {

        });
        동적선택자에는 이벤트를 직접적으로 넣을 수 없다.
        */
        $(".uploadResult").on("click", "span", function() {
            console.log("삭제이벤트");

            // $(this).data("file") : 2024%5C05%5C20/s_d35c7e8d-dd10-4f20-8b12-692c1c161674_에그타르트.jpg
            //  '/'는 dateFolderName
            let targetFile = $(this).data("file").split('/');  //data-file
            let type = $(this).data("type");  // data-type


            //파일명에서 날짜폴더명을 분리해야 한다.
            console.log("targetFile[0]" + targetFile[0]);
            console.log("targetFile[1]" + targetFile[1]);
            console.log("type", type);

            //return;

            $.ajax({
                url: 'deleteFile',
                data: {dateFolderName: targetFile[0], fileName : targetFile[1], type: type}, // 자바스크립트  object문법
                dataType: 'text', //스프링메서드 리턴타입
                type: 'post',
                success: function(result){
                    if(result = "success"){
                        alert("이미지가 삭제됨.");
                    }
                }
            });
        });
    });  // ready end


	//파일 업로드목록 정보가 출력될 위치를 참조.
	let uploadResult = $(".uploadResult ul");

	// 업로드한 파일정보를 리스트형태로 출력
	function showUploadedFile(uploadResultArr) {
		let str = "";

		$(uploadResultArr).each(function(i, obj) {
						  //each는 for문과 같은 역할

			if(!obj.image) {  // 일반파일
				let fileCalPath = encodeURIComponent(obj.uploadPath) + "/" + obj.uuid;

				str += "<li><div><a href='/upload/download?fileName=" + fileCalPath + "'><img src='/img/attach.png'>" +
				obj.fileName + "</a><span style='cursor:pointer;' data-file=\'" + fileCalPath + "\' data-type='file'> X </span></div></li>";
			}else {			// 이미지파일

                //let fileCalPath = obj.uploadPath + "/" + "s_" + obj.uuid;  // 에러발생 400. 역슬래시 문자를 서버에서 허용안함.
				let fileCalPath = encodeURIComponent(obj.uploadPath) + "/" + "s_" + obj.uuid;
				let originPath = obj.uploadPath + "\\" + obj.uuid;

				originPath = originPath.replace(new RegExp(/\\/g), "/");

				str += "<li><a href=\"javascript:showImage('" + originPath + "')\"><img src='display?fileName=" + fileCalPath + "'></a>" +
					"<span style='cursor:pointer;' data-file=\'" + fileCalPath + "\' data-type='image'> X </span></li>";
			}
		});
		//파일정보 리스트형태의 작업한 태그내용을 자식레벨로 추가하는 작업
		uploadResult.append(str);
	}



    


</script>    
</body>
</html>





