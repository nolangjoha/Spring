<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>    
    
        <div class="col-4">
            <section>
                <h1>mypage</h1>  <!-- 여기 왼쪽 단락도 나중에 include로 처리 할 수 있다.-->
                <div>
                    <a class="dropdown-item" href="/userinfo/mypage">내 정보</a>
                    <a class="dropdown-item" href="/userinfo/changepw">비밀번호 변경</a>
                    <a class="dropdown-item" href="/userinfo/delete">회원탈퇴</a>
                </div>
            </section>
        </div>