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
<title>집밥 천국 - 메뉴 보기</title>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
			<div class="content">
				<h2>집밥 메뉴 상세 보기</h2>
				<img src="../Image/${dto.img }" />
				<table width="90%">
					<tr>
						<th>메뉴 이름</th>
						<td>${dto.title }</td>
						<th>종류</th>
						<td>${dto.ftype }</td>
					</tr>
					<tr>
						<th>칼로리</th>
						<td>${dto.cntk}</td>
						<th>알러지</th>
						<td>${dto.allergy }</td>
					</tr>
					<tr>
						<th>메뉴설명</th>
						<td colspan="3">
							${dto.expr }
						</td>
					</tr>
					<tr>
						<th>원산지</th>
						<td colspan="3">
							${dto.origin }
						</td>
					</tr>
				</table>
				<br /><br />
				<c:if test="${sessionScope.UserId eq 'admin' }">
					<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/mpass.do?mode=edit&idx=${dto.idx}';">메뉴수정</button>
					<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/mpass.do?mode=delete&idx=${dto.idx}';">메뉴삭제</button>
				</c:if>
				<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/menu.do'">목록보기</button>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>