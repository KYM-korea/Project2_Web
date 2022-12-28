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
<title>
<c:choose>
	<c:when test="${param.ftype eq '신메뉴' }">집밥 천국 - 신메뉴</c:when>
	<c:when test="${param.ftype eq '김밥' }">집밥 천국 - 김밥</c:when>
	<c:when test="${param.ftype eq '식사' }">집밥 천국 - 식사</c:when>
	<c:when test="${param.ftype eq '분식' }">집밥 천국 - 분식</c:when>
	<c:when test="${param.ftype eq '돈까스' }">집밥 천국 - 돈까스</c:when>
	<c:when test="${param.ftype eq '계절메뉴' }">집밥 천국 - 계절메뉴</c:when>
	<c:otherwise>집밥 천국 - 전체 메뉴</c:otherwise>
</c:choose>
</title>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
		<div class="content">
			<h2>
			<c:choose>
				<c:when test="${param.ftype eq '신메뉴' }">집밥 천국 - 신메뉴</c:when>
				<c:when test="${param.ftype eq '김밥' }">집밥 천국 - 김밥</c:when>
				<c:when test="${param.ftype eq '식사' }">집밥 천국 - 식사</c:when>
				<c:when test="${param.ftype eq '분식' }">집밥 천국 - 분식</c:when>
				<c:when test="${param.ftype eq '돈까스' }">집밥 천국 - 돈까스</c:when>
				<c:when test="${param.ftype eq '계절메뉴' }">집밥 천국 - 계절메뉴</c:when>
				<c:otherwise>집밥 천국 - 전체 메뉴</c:otherwise>
			</c:choose>
			</h2>
			<c:forEach items="${mBoardLists }" var="row" varStatus="loop">
				<div class="card m-2" style="width:240px; float:left;">
					<a href="../zibbab/menuview.do?idx=${row.idx }">
						<img class="card-img-top mt-2" src="../Image/${row.img}" style="width:100%; height:260px;">
						<div class="card-body">
						    <h4 class="card-title">${row.title }</h4>
						    <p class="card-text">
						        ${row.expr } <hr>
						        칼로리 : ${row.cntk } kcal
						    </p>
						</div>
					</a>
				</div>
			</c:forEach>
			<c:if test="${sessionScope.UserId eq 'admin' }">
				<div name="buttonGroup" style="clear: both;">
					<button type="button" onclick="location.href='../zibbab/minsert.do';">메뉴 등록</button>
				</div>
			</c:if>
		</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>