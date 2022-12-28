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
<title>집밥 천국 - 공지사항</title>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
			<div class="content">
				<h2>공지사항</h2>
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
						<th>조회수</th>
						<th>작성일</th>
						<th>첨부파일</th>
					</tr>
					<c:choose>
						<c:when test="${empty nBoardLists }">
							<tr>
								<td colspan="4" align="center">등록된 공지가 없습니다^^*</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${nBoardLists }" var="row" varStatus="loop">
								<tr align="center">
									<td>${map.totalCnt - (((map.pageNum-1) * map.pageSize) + loop.index) }</td>								
									<td>
										<a href="../zibbab/noview.do?idx=${row.idx }">${row.title }</a>
									</td>								
									<td>${row.vcnt }</td>	
									<td>${row.postdate }</td>	
									<td>
										<c:if test="${not empty row.ofile }">
											<a href="../zibbab/download.do?ofile=${row.ofile }&sfile=${row.sfile}&idx=${row.idx}">[다운]</a>
										</c:if>
									</td>								
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
		                <option value="content">내용</option>
		            </select>
		            <input type="text" name="searchWord" />
		            <input type="submit" value="검색하기" />
				</form>
				<c:if test="${sessionScope.UserId eq 'admin' }">
					<button onclick="location.href='../zibbab/notiwrite.do?attr=N'">공지하기</button>
				</c:if>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>