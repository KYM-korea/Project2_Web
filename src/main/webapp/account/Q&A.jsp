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
<title>집밥 천국 - 문의사항</title>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
			<div class="content">
				<h2>문의사항</h2>
				<table class="table">
					<colgroup>
						<col width="15%"/>
						<col width="*"/>
						<col width="20%"/>
						<col width="25%"/>
					</colgroup>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
					<c:choose>
						<c:when test="${empty qBoardLists }">
							<tr>
								<td colspan="4" align="center">등록된 게시물이 없습니다^^*</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${qBoardLists }" var="row" varStatus="loop">
								<tr align="center">
									<td>${map.totalCnt - (((map.pageNum-1) * map.pageSize) + loop.index) }</td>								
									<td>
										<c:choose>
											<c:when test="${UserId eq 'admin' }">
												<a href="../zibbab/qedit.do?idx=${row.idx }">${row.title }</a>
											</c:when>
											<c:otherwise>
												<a href="../zibbab/Qpass.do?mode=view&idx=${row.idx }">${row.title }</a>
											</c:otherwise>
										</c:choose>
									</td>								
									<td>${row.uname }</td>								
									<td>${row.postdate }</td>								
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
				<table class="table">
					<tr align="center">
						<td>
							${map.pagingImg }
						</td>
					</tr>
				</table>
				<form method="get">
					<select name="searchField"> 
		                <option value="title">제목</option> 
		                <option value="qtext">내용</option>
		            </select>
		            <input type="text" name="searchWord" />
		            <input type="submit" value="검색하기" />
				</form>
				<button onclick="location.href='../zibbab/questwrite.do'">문의하기</button>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>