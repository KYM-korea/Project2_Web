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
<title>집밥 천국</title>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<div class="body">
			<!-- 슬라이드 이미지 -->
			<div id="demo" class="carousel slide" data-bs-ride="carousel">
				<!-- 이미지 버튼 -->
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
					<button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
					<button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
				</div>

				<!-- 사용되는 이미지 -->
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="../Image/main1.jpg" class="d-block" style="width: 100%">
					</div>
					<div class="carousel-item">
						<img src="../Image/main2.jpg" class="d-block" style="width: 100%">
					</div>
					<div class="carousel-item">
						<img src="../Image/main3.jpg" class="d-block" style="width: 100%">
					</div>
				</div>
			</div>
			<div class="maincontent">
				<div class="notice">
					<span style="font-size:30px">공지사항</span>
					<a href="../zibbab/notice.do">
						<span class="badge bg-primary" id="nmore">more</span>
					</a>
					<table class="table">
						<colgroup>
							<col width="10%"/>
							<col width="*"/>
							<col width="10%"/>
							<col width="25%"/>
						</colgroup>
						<tr>
							<th>번호</th>				
							<th>제목</th>
							<th>조회수</th>
							<th>작성일</th>
						</tr>
						<c:forEach items="${nBoardLists }" var="row" varStatus="loop" end="5">
							<tr>
								<td>${map.totalCnt - (((map.pageNum-1) * map.pageSize) + loop.index) }</td>
								<td><a href="../zibbab/noview.do?idx=${row.idx }">${row.title}</a></td>
								<td>${row.vcnt }</td>
								<td>${row.postdate }</td>
							</tr>			
						</c:forEach>
					</table>
				</div>
				<div style="float:right; align-content: right;">
					<a href="../zibbab/menu.do"><img src="../Image/menu.png"/></a>
					<a href=""><img src="../Image/recipe.png"/></a><br />
					<a href="../zibbab/consul.do"><img src="../Image/partner.png"/></a>
					<a href="../zibbab/notice.do"><img src="../Image/account.png"/></a>
				</div>
			</div>
		</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>