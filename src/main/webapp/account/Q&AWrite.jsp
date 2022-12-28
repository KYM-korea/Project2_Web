<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../inc/style/layout.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>집밥 천국 - 문의사항</title>
<script>
	function Qchk(fn){
		if(fn.Title.value == ''){
			alert("제목을 작성해주세요");
			fn.Title.focus();
			return false;
		}
		if(fn.Uname.value==''){
			alert("작성자명을 작성해주세요");
			fn.Uname.focus();
			return false;
		}
		if(fn.qcontent.value==''){
			alert("문의내용을 작성해주세요");
			fn.qcontent.focus();
			return false;
		}
		if(!(fn.Agree.checked)){
			alert("약관에 동의하지 않으셨습니다.");
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
				<h2>문의사항</h2>
				<form action="../zibbab/questwrite.do" method="post" onsubmit="return Qchk(this);">
					<table width="600px">
						<colgroup>
							<col width="150px"/>
							<col width="*"/>
						</colgroup>
						<tr>
							<th>제목</th>
							<td>
								<input type="text" name="Title"/>
							</td>
						</tr>
						<tr>
							<c:choose>
								<c:when test="${sessionScope.UserId ne null}">
									<th>이름</th>
									<td>
										<c:choose>
											<c:when test="${sessionScope.UserId ne 'admin' }">
												<input type="text" name="Uname" readOnly value='${sessionScope.UserId}' />
											</c:when>
											<c:otherwise>
												<input type="text" name="Uname" />
											</c:otherwise>
										</c:choose>
									</td>
								</c:when>
								<c:otherwise>
									<th>이름</th>
									<td>
										<input type="text" name="Uname" />
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td>
								<input type="text" name="UserPass"/>
								<p>비밀번호 입력 시 비공개글로 변환됩니다.</p>
								<p>공개글은 수정이 불가능합니다.</p>
							</td>
						</tr> 
						<tr>
							<th>문의 내용</th>
							<td>
								<textarea name="qcontent" style="width: 90%; height: 100px"></textarea>
							</td>
						</tr>
					</table>
					<br />
					<input type="checkbox" name="Agree" /> <b>개인정보 수집에 동의 확인합니다.</b>
					<br /><br />
					<button class="btn btn-outline-primary" >작성완료</button>
					<button type="reset" class="btn btn-outline-primary">다시작성</button>
					<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/questlist.do'">목록보기</button>
				</form>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>