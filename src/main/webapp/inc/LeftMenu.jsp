<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="leftmenu">
	<h5>사이드 메뉴</h5>
	<ul>
		<li>
			<a href="../zibbab/menu.do">
				<label>메뉴</label>
			</a>
			<ul>
				<li>
					<a href="../zibbab/menu.do?ftype=신메뉴">
						신메뉴
					</a>
				</li>
				<li>
					<a href="../zibbab/menu.do?ftype=김밥">
						김밥
					</a>
				</li>
				<li>
					<a href="../zibbab/menu.do?ftype=식사">
						식사
					</a>
				</li>
				<li>
					<a href="../zibbab/menu.do?ftype=분식">
						분식
					</a>
				</li>
				<li>
					<a href="../zibbab/menu.do?ftype=돈까스">
						돈까스
					</a>
				</li>
				<li>
					<a href="../zibbab/menu.do?ftype=계절메뉴">
						계절메뉴
					</a>
				</li>
			</ul>
		</li>
		<li>
			<label>레시피</label>
			<ul>
				<li>
					<a href="../zibbab/reci.do?kind=all">
						집밥천국 레시피
					</a>
				</li>
				<li>
					<a href="../zibbab/reci.do?kind=own">
						나만의 레시피
					</a>
				</li>
			</ul>
		</li>
		<li>
			<a href="../zibbab/consul.do">
				<label>프랜차이즈</label>
			</a>
			<ul>
				<li>
					<c:choose>
						<c:when test="${sessionScope.UserId eq 'admin' }">
							<a href="../zibbab/consul.do">창업관련답변</a>
						</c:when>
						<c:otherwise>
							<a href="../zibbab/consul.do">창업문의</a>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<a href="../zibbab/conlist.do">창업관련자료</a>
				</li>
			</ul>
		</li>
		<li>
			<a href="../zibbab/notice.do">
				<label>고객센터</label>
			</a>
			<ul>
				<li>
					<a href="../zibbab/notice.do">공지사항</a></li>
				<li>
					<a href="../zibbab/questlist.do">문의사항</a>
				</li>
				<li>
					오시는길
				</li>
			</ul>
		</li>
	</ul>
</div>