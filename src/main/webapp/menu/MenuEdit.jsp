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
<title>집밥 천국 - 메뉴 수정</title>
<script type="text/javascript">
function Mchk(fn){
	if(fn.title.value == ''){
		alert("메뉴 이름을 작성해주세요");
		fn.title.focus();
		return false;
	}
	if(fn.cntk.value == ''){
		alert("칼로리를 작성해주세요");
		fn.cntk.focus();
		return false;
	}
	if(fn.allergy.value == ''){
		alert("알러지를 작성해주세요");
		fn.allergy.focus();
		return false;
	}
	if(fn.expr.value == ''){
		alert("메뉴 설명을 작성해주세요");
		fn.expr.focus();
		return false;
	}
	if(fn.origin.value == ''){
		alert("원산지를 작성해주세요");
		fn.origin.focus();
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
				<h2>집밥 메뉴 수정</h2>
				<form action="../zibbab/mpass.do" method="post" enctype="multipart/form-data"
					 onsubmit="return Mchk(this);" >
					<input type="hidden" name="idx" value="${dto.idx }" />
					<input type="hidden" name="prevImg" value="${dto.img }" />
					<table width="90%">
						<tr>
							<th>메뉴 이름</th>
							<td>
								<input type="text" name="title" value="${dto.title }"/>
							</td>
							<th>칼로리 정도</th>
							<td>
								<input type="text" name="cntk" value="${dto.cntk }" /> kcal
							</td>
						</tr>
						<tr>
							<th>알러지</th>
							<td colspan="3">
								<input type="text" name="allergy" value="${dto.allergy }"/>
							</td>
						</tr>
						<tr>
							<th>이미지</th>
							<td>
								<input type="file" name="img" />
							</td>
							<th>분류</th>
							<td>
								<select name="ftype">
									<option value="신메뉴">신메뉴</option>
									<option value="김밥">김밥</option>
									<option value="식사">식사</option>
									<option value="분식">분식</option>
									<option value="돈까스">돈까스</option>
									<option value="계절메뉴">계절메뉴</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>메뉴 설명</th>
							<td colspan="3">
								<textarea name="expr" style="width: 90%; height: 100px;">${dto.expr }</textarea>
							</td>
						</tr>
						<tr>
							<th>원산지</th>
							<td colspan="3">
								<textarea name="origin" style="width: 90%; height: 100px;">${dto.origin }</textarea>
							</td>
						</tr>
					</table>
					<br /><br />
					<button class="btn btn-outline-primary">수정완료</button>
					<button type="reset" class="btn btn-outline-primary">재작성</button>
					<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/menu.do'">목록보기</button>
				</form>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>