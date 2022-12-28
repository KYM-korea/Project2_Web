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
<title>집밥 천국 - 공지 사항 수정</title>
<script type="text/javascript">
function Nchk(fn){
	if(fn.title.value == ''){
		alert("제목을 작성해주세요");
		fn.title.focus();
		return false;
	}
	if(fn.content.value==''){
		alert("문의내용을 작성해주세요");
		fn.content.focus();
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
				<h2>공지사항</h2>
				<form action="../zibbab/nedit.do" method="post" enctype="multipart/form-data"
					 onsubmit="return Nchk(this);" >
					<input type="hidden" name="idx" value="${dto.idx }" />
					<input type="hidden" name="prevOfile" value="${dto.ofile }" />
					<input type="hidden" name="prevSfile" value="${dto.sfile }" />
					<table width="600px">
						<tr>
							<th>제목</th>
							<td colspan="3">
								<input type="text" name="title" value="${dto.title }"/>
							</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td>
								<input type="file" name="ofile" />
							</td>
							<th>태그</th>
							<td>
								<select name="attr">
								<c:choose>
									<c:when test="${dto.attr eq 'C' }">
											<option value="N">공지사항</option>
											<option value="C" selected>창업관련</option>
									</c:when>
									<c:otherwise>
											<option value="N" selected>공지사항</option>
											<option value="C">창업관련</option>
									</c:otherwise>
								</c:choose>
								</select>
							</td>
						</tr>
						<tr>
							<th>공지 내용</th>
							<td colspan="3">
								<textarea name="content" style="width: 90%; height: 100px;">${dto.content }</textarea>
							</td>
						</tr>
					</table>
					<br /><br />
					<button class="btn btn-outline-primary">수정완료</button>
					<button type="reset" class="btn btn-outline-primary">재작성</button>
					<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/notice.do'">목록보기</button>
				</form>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>