<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header" >
	<div class="top_user">
		<c:choose>
			<c:when test="${sessionScope.UserId eq null }">		
				<a href="../zibbab/login.do">로그인</a>
				<a href="../zibbab/regi.do">회원가입</a>
			</c:when>
			<c:otherwise>
				<b>${sessionScope.UserId }</b>님 환영합니다. &nbsp;&nbsp;&nbsp;&nbsp;
				<a href="../zibbab/logout.do">로그아웃</a>
				<c:if test="${sessionScope.UserId ne 'admin' }">
					<a href="../zibbab/myPage.do">마이페이지</a>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>
	<!-- 로고 및 메인 화면으로 이동 -->
	<div class="top_logo">
		<a href="../zibbab/main.do">
			<img src="../Image/Zip_logo.png" style="width:100px; height:100px;" />
		</a>
	</div>
	<!-- 상단 메뉴 -->
	<div class="top_menu">
		<ul class="nav nav-justified">
			<li class="nav-item">
				<a href="../zibbab/main.do" class="nav-link">홈<br />
				<span>Home</span>
				</a>
			</li>
			<li class="nav-item">
				<a href="../zibbab/menu.do" class="nav-link">메뉴 <br />
				<span>Menu</span>
				</a>
			</li>
			<li class="nav-item">
				<a href="../zibbab/reci.do?kind=all" class="nav-link">레시피 <br />
				<span>Recipe</span>
				</a>
			</li>
			<li class="nav-item">
				<a href="../zibbab/consul.do" class="nav-link">프랜차이즈 <br />
				<span>Franchise</span>
				</a>
			</li>
			<li class="nav-item">
				<a href="../zibbab/notice.do" class="nav-link">고객센터<br />
				Service Center
				</a>
			</li>
		</ul>
	</div>
</div>