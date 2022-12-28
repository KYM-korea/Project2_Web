<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../inc/style/layout.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>집밥 천국 - 창업 상담 신청</title>
</head>
<body>
<div class="AllWrap">
	<div class="container">
	<jsp:include page="../inc/TopMenu.jsp" />
	<jsp:include page="../inc/LeftMenu.jsp" />
		<div class="content">
			<h2>창업 문의</h2>
			<form action="" method="post" onsubmit="return Cchk(this);" >
					<table width="600px">
						<tr>
							<th>이름</th>
							<td>
								<input type="text" name="uname"/>
							</td>
						</tr>
						<tr>
							<th>연락처</th>
							<td>
								<input type="text" name="phone"/>
							</td>
						</tr>
						<tr>
							<th>거주지</th>
							<td>
								<input type="text" name="addr"/>
							</td>
						</tr>
						<tr>
							<th>창업 관련 계획</th>
							<td>
								<textarea name="content" style="width: 90%; height: 100px;"></textarea>
							</td>
						</tr>
					</table>
					<br /><br />
					<button class="btn btn-outline-primary">작성하기</button>
					<button type="reset" class="btn btn-outline-primary">재작성</button>
				</form>
		</div>
	<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>