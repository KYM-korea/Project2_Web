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
<title>집밥 천국 - 공지 상세 보기</title>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
			<div class="content">
				<h2>공지사항 상세보기</h2>
					<table width="600px">
						<colgroup>
							<col width="15%" />
							<col width="35%" />
							<col width="15%" />
							<col width="*" />
						</colgroup>
						<tr>
							<th>제목</th>
							<td>
								${dto.title }
							</td>
							<th>조회수</th>
							<td>
								${dto.vcnt }
							</td>
						</tr>
						<tr>
							<th>작성일</th>
							<td>
								${dto.postdate }
							</td>
							<th>첨부파일</th>
							<td>
								<c:if test='${ not empty dto.ofile }'>
									${dto.ofile }
									<a href="../zibbab/download.do?ofile=${dto.ofile }&sfile=${dto.sfile}&idx=${dto.idx}">[다운로드]</a>
								</c:if>
							</td>
						</tr>
						<c:if test="${sessionScope.UserId eq 'admin' && not empty dto.ofile }">
							<tr>
								<th>다운수</th>
								<td>${dto.dcnt }</td>
								<td></td>
								<td></td>
							</tr>
						</c:if>
						<tr>
							<th>공지 내용</th>
							<td colspan="3">
								${dto.content }
								<c:if test="${isImage eq true}">
						      		<p>
						      			<img src="../Uploads/${dto.sfile }" />
						      		</p>
						      	</c:if>
							</td>
						</tr>
					</table>
					<br /><br />
					<c:if test='${sessionScope.UserId eq "admin" }'>
						<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/nedit.do?idx=${param.idx}';">수정하기</button>
						<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/ndelete.do?idx=${param.idx}';">삭제하기</button>
					</c:if>
					<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/notice.do'">목록보기</button>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>