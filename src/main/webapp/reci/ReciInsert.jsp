<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../inc/style/layout.css?after">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>집밥 천국 - 레시피 등록</title>
<script type="text/javascript">
function Mchk(fn){
	if(fn.title.value == ''){
		alert("레시피 이름을 작성해주세요");
		fn.title.focus();
		return false;
	}
	if(fn.ingre.value==''){
		alert("재료를 입력해주세요");
		fn.ingre.focus();
		return false;
	}
	if(fn.src.value==''){
		alert("양념을 입력해주세요");
		fn.src.focus();
		return false;
	}
	if(fn.content.value==''){
		alert("재료를 입력해주세요");
		fn.content.focus();
		return false;
	}
	if(fn.img.value == ''){
		alert("완성 이미지를 선택해주세요");
		fn.img.focus();
		return false;
	}
} 
</script>
<style>
textarea{
	resize : none;
}
</style>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
			<div class="content" >
				<h2>집밥 레시피 등록</h2>
				<form action="../zibbab/rinsert.do" method="post" enctype="multipart/form-data"
					 onsubmit="return Rchk(this);" >
					<input type="hidden" name="kind" value="${requestScope.kind }"/>
					<table style="text-align: left; width: 90%;">
						<tr>
							<th>레시피 이름</th>
							<td><input type="text" name="title" /></td>
							<th>작성자</th>
							<td><input type="text" readOnly name="id" value="${sessionScope.UserId }"/></td>
						</tr>
						<tr>
							<th>재료</th>
							<td colspan="3"><textarea name="ingre" cols="65"></textarea></td>
						</tr>
						<tr>
							<th>양념</th>
							<td colspan="3"><textarea name="src" cols="65"></textarea></td>
						</tr>
						<tr>
							<th>조리방법</th>
							<td colspan="3"><textarea name="content" cols="65" rows="10"></textarea></td>
						</tr>
						<tr>
							<th>완성된 이미지</th>
							<td colspan="3"><input type="file" name="img" /></td>
						</tr>
					</table>
					<br /><br />
					<button class="btn btn-outline-primary">등록하기</button>
					<button type="reset" class="btn btn-outline-primary">재작성</button>
					<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/menu.do'">목록보기</button>
				</form>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>