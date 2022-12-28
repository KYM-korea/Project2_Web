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
<title>
<c:choose>
	<c:when test="${sessionScope.UserId eq 'admin' }">
	집밥 천국 - 문의 답변
	</c:when>
	<c:otherwise>
	집밥 천국 - 문의 수정
	</c:otherwise>
</c:choose>
</title>
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
	}
</script>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
			<div class="content">
				<h2>문의사항 - 수정</h2>
				<form action="../zibbab/qedit.do" method="post" onsubmit="return Qchk(this);">
				<input type="hidden" name="idx" value="${dto.idx }" />
					<table width="600px">
						<colgroup>
							<col width="150px"/>
							<col width="*"/>
						</colgroup>
						<tr>
							<th>제목</th>
							<td>
								<input type="text" name="Title" value="${dto.title }"/>
							</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>
								<input type="text" name="Uname" ReadOnly value="${dto.uname }" />
							</td>
						</tr>
						<tr>
							<th>문의 내용</th>
							<td>
								<textarea name="qcontent" style="width: 90%; height: 100px">${dto.qtext }</textarea>
							</td>
						</tr>
						<c:if test="${sessionScope.UserId eq 'admin' }">
							<tr>
								<th>답변 내용</th>
								<td>
									<textarea name="atext" style="width: 90%; height: 100px">${dto.atext }</textarea>
								</td>
							</tr>
						</c:if>
					</table>
					<br /><br />
					<button class="btn btn-outline-primary" >수정완료</button>
					<button type="reset" class="btn btn-outline-primary">다시작성</button>
					<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/questlist.do'">목록보기</button>
				</form>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>