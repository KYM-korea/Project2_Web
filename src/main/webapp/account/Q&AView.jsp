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
<title>집밥 천국 - 문의 상세 보기</title>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
			<div class="content">
				<h2>문의사항 상세보기</h2>
					<table width="600px">
						<colgroup>
							<col width="150px"/>
							<col width="*"/>
						</colgroup>
						<tr>
							<th>제목</th>
							<td>
								${dto.title }
							</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>
								${dto.uname }
							</td>
						</tr>
						<tr>
							<th>문의 내용</th>
							<td>
								${dto.qtext }
							</td>
						</tr>
						<tr>
							<th>답변 내용</th>
							<td>
								${dto.atext }
							</td>
						</tr>
					</table>
					<br /><br />
					<c:if test="${sessionScope.UserId eq 'admin'  || not empty sessionScope.pass }">
						<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/Qpass.do?mode=edit&idx=${param.idx}';">수정하기</button>
						<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/Qpass.do?mode=delete&idx=${param.idx}';">삭제하기</button>
					</c:if>
					<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/questlist.do'">목록보기</button>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>