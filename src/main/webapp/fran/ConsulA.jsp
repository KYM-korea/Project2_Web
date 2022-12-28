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
<title>집밥 천국 - 창업 상담</title>
</head>
<body>
<div class="AllWrap">
	<div class="container">
	<jsp:include page="../inc/TopMenu.jsp" />
	<jsp:include page="../inc/LeftMenu.jsp" />
		<div class="content">
			<h2>창업 상담 신청자</h2>
			<table class="table">
					<colgroup>
						<col width="15%"/>
						<col width="*"/>
						<col width="20%"/>
						<col width="25%"/>
					</colgroup>
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>연락처</th>
						<th>작성일</th>
					</tr>
					<c:choose>
						<c:when test="${empty cBoardLists }">
							<tr>
								<td colspan="4" align="center">등록된 상담내역이 없습니다^^*</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${cBoardLists }" var="row" varStatus="loop">
								<tr align="center">
									<td>${map.totalCnt - (((map.pageNum-1) * map.pageSize) + loop.index) }</td>								
									<td>
										<a href="../zibbab/conview.do?idx=${row.idx }">${row.uname }</a>
									</td>								
									<td>${row.phone }</td>	
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
		                <option value="uname">이름</option> 
		                <option value="ctext">내용</option>
		            </select>
		            <input type="text" name="searchWord" />
		            <input type="submit" value="검색하기" />
				</form>
		</div>
	<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>