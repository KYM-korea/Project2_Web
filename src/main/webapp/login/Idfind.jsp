<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복체크</title>
<script>
	function setId(fn){
		opener.document.getElementById("UserId").value = fn.UserId.value;
		opener.document.getElementById("UserId").readOnly = true;
		opener.document.getElementById("UserId2").value = "true";
		self.close();
	}
</script>
</head>
<body>
	<form action="">
		<span>아이디</span>
		<c:choose>
			<c:when test="${result eq '0'}">
				사용할 수 있습니다. <br />
				<input type="text" name="UserId" value="${param.UserId }"/><br />
				<button type="button" onclick="setId(this.form)">사용하기</button>
			</c:when>
			<c:otherwise>
				중복된 아이디가 있습니다. <br />
				<input type="text" name="UserId" /><br />
				<button type="submit">중복확인</button>
			</c:otherwise>
		</c:choose>
		<button type="button" onclick="self.close();">닫기</button>
	</form>
</body>
</html>