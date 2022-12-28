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
<title>집밥 천국 - 나만의 레시피</title>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
		<div class="content">
			<h2>나만의 레시피</h2>
			<c:forEach items="${rBoardLists }" var="row" varStatus="loop">
				<div class="card m-2" style="width:240px; float:left;">
					<a href="../zibbab/reciview.do?idx=${row.idx }">
						<img class="card-img-top mt-2" src="../Image/${row.img}" style="width:100%; height:260px;">
						<div class="card-body">
						    <h4 class="card-title">${row.title }</h4>
						</div>
						작성일 : ${row.postdate } <br />
						조회수 : ${row.visitcount } <br />
						추천수 : ${row.gc }
					</a>
				</div>
			</c:forEach>
			<c:if test="${not empty sessionScope.UserId }">
				<div name="buttonGroup" style="clear: both;">
					<button type="button" onclick="location.href='../zibbab/rinsert.do?kind=own';">메뉴 등록</button>
				</div>
			</c:if>
		</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>