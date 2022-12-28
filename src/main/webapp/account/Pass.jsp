<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="../inc/style/layout.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>집밥 천국 - 비밀번호 인증</title>
<script type="text/javascript">
	function validateForm(fn){
		if (form.pass.value == "") {
	        alert("비밀번호를 입력하세요.");
	        form.pass.focus();
	        return false;
	    }
	}
</script>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
			<div class="content">
				<h2>인증~</h2>
				<form method="post" action="../zibbab/Qpass.do" onsubmit="return validateForm(this);">
					<input type="hidden" name="idx" value="${param.idx }" />
					<input type="hidden" name="mode" value="${mode }" />
					비밀번호 : <input type="text" name="pass"/><br />
					<button class="btn btn-primary" >확인</button>
					<button type="button" class="btn btn-primary" onclick="location.href='../zibbab/questlist.do';">목록보기</button>
				</form>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>