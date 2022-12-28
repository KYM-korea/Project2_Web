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
<title>집밥 천국 - 레시피 보기</title>
<style type="text/css">
textarea{
	resize: none;
}
</style>
<script>
	function commentChk(frm){
		if(frm.rcomment.value==""){
			alert("댓글을 작성하고 등록하여주세요.");
			frm.rcomment.focus();
			return false;
		}
	}
</script>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
			<div class="content">
				<h2>집밥 천국 레시피 상세 보기</h2>
				<img src="../Image/${dto.img }" />
				<table width="90%"  style="text-align: left;">
					<tr>
						<th style="text-align: center;"><h2>${dto.title }</h2></th>
					</tr>
					<tr>
						<th>재료</th>
					</tr>
					<tr>
						<td style="text-align: center;">${dto.ingre }</td>
					</tr>
					<tr>
						<th>양념</th>
					</tr>
					<tr>
						<td style="text-align: center;">${dto.src }</td>
					</tr>
					<tr>
						<th>조리 순서</th>
					</tr>
					<tr>
						<td style="text-align: center;">${dto.content }</td>
					</tr>
				</table>
				<br /><br />
				
				<c:if test="${not empty commentBoardLists }">
					<table>
						<c:forEach items="${commentBoardLists }" var="row" varStatus="loop">
							<tr>
								<td>${row.id }</td>	
								<td>${row.postdate }</td>	
							</tr>
							<tr>
								<td colspan="2">
									${row.rcomment }
								</td>								
							</tr>
						</c:forEach>
					</table>
				</c:if>
				
				<form action="../zibbab/comment.do" method="post" onsubmit="return commentChk(this);">
					<input type="hidden" name="id" value="${sessionScope.UserId }" />
					<input type="hidden" name="reciIndex" value="${dto.idx }" />
					<table>
						<tr>
							<td>
								<textarea name="rcomment" cols="65"></textarea>
							</td>
							<td>
								<button class="btn btn-primary">등록</button>
							</td>
						</tr>
					</table>
				</form>
				
				<button type="button" class="btn btn-outline-primary" onclick="location.href='../zibbab/reci.do?kind=${dto.kind}'">목록보기</button>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>