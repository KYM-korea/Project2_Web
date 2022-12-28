<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../inc/style/layout.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>집밥 천국 - 로그인</title>
<script>
	function inputChk(fn){
		if(fn.UserId.value==''){
			alert('아이디를 입력하세요.');
			fn.UserId.focus();
			return false;
		}
		if(fn.UserPass.value==''){
			alert('비밀번호를 입력하세요.');
			fn.UserPass.focus();
			return false;
		}
	}
</script>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp"/>
		<jsp:include page="../inc/LeftMenu.jsp" />
		<div class="content">
			<c:choose>
				<c:when test="${ sessionScope.UserId eq null }">
					<form action="../zibbab/login.do" method="post" onsubmit="return inputChk(this);">
						<h2>로그인</h2>
						<span>아이디 : </span>
						<input type="text" name="UserId" /><br />
						<span>비밀번호 : </span>
						<input type="password" name="UserPass" /><br />
						<button class="btn btn-outline-primary" >로그인</button>
						<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/accountFind.do'">
						아이디/패스워드 찾기</button>
					</form>
				</c:when>
				<c:otherwise>
					${UserId }님 안녕하세요.
				</c:otherwise>
			</c:choose>
			<c:if test="${not empty requestScope.LoginErrMsg}">
				로그인 실패
			</c:if>
		</div> 
		<jsp:include page="../inc/Copyright.jsp"/>
	</div>
</div>
</body>
</html>