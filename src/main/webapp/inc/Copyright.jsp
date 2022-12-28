<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="footer">
	<div class="copy_logo">
		<img src="../Image/Zip_logo.png" style="width:100px; height:100px;" />
	</div>
	<div class="copy_info">
		서울시 서초구 사임당로 52 &nbsp;&nbsp;&nbsp;
		전화 1577-6323 , 02-581-6323 <br />
		사업자 등록번호 214-88-85173 <br />
		Copyright ⓒ 2022 김밥천국 All rights reserved
	<ul class="nav">
		<li class="nav-item">
			<button type="button" class="btn btn-primary"
			data-bs-toggle="modal" data-bs-target="#TOSM">이용약관</button>
		</li>
		<li class="nav-item">
			<button type="button" class="btn btn-primary"
			data-bs-toggle="modal" data-bs-target="#PPM">개인정보처리 방침</button>
		</li>
	</ul>
	</div>
</div>
<jsp:include page="../inc/TOSM.jsp"/>
<jsp:include page="../inc/PPM.jsp" />