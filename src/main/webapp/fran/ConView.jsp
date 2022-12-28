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
<title>집밥 천국 - 창업 상담 상세 보기</title>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
			<div class="content">
				<h2>창업 상담 신청 상세보기</h2>
					<table width="600px">
						<colgroup>
							<col width="35%" />
							<col width="*" />
						</colgroup>
						<tr>
							<th>이름</th>
							<td>
								${dto.uname }
							</td>
						</tr>
						<tr>
							<th>연락처</th>
							<td>
								${dto.phone}
							</td>
						</tr>
						<tr>
							<th>거주지</th>
							<td>
								${dto.addr}
							</td>
						</tr>
						<tr>
							<th>작성일</th>
							<td>
								${dto.postdate }
							</td>
						</tr>
						<tr>
							<th>상담 신청 내용</th>
							<td>
								${dto.ctext }
							</td>
						</tr>
					</table>
					<br /><br />
					<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/cdelete.do?idx=${param.idx}';">삭제하기</button>
					<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/consul.do'">목록보기</button>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>