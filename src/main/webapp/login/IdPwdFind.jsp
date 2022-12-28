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
<title>집밥 천국 - 계정 찾기</title>
<script>
	function inputChk(fn){
		if(fn.isfind.value==1){
			if(fn.email.value==''){
				alert('이메일 입력');
				fn.email.focus();
				return false;
			}
			if(fn.domain.value==''){
				alert('도메인 입력');
				fn.domain.focus();
				return false;
			}
			if(fn.phone.value==''){
				alert('연락처 입력');
				fn.phone.focus();
				return false;
			}
		}else{
			if(fn.UserId.value==''){
				alert('아이디 입력');
				fn.UserId.focus();
				return false;
			}
			if(fn.email.value==''){
				alert('이메일 입력');
				fn.email.focus();
				return false;
			}
			if(fn.domain.value==''){
				alert('도메인 입력');
				fn.domain.focus();
				return false;
			}
			if(fn.phone.value==''){
				alert('연락처 입력');
				fn.phone.focus();
				return false;
			}
			
		}
	}
	function hide(fn){
		var val = fn.isfind.value;
		if(val == 1){
			document.getElementById("inputId").style.display="none";
		}else{
			document.getElementById("inputId").style.display="";
		}
	}
	function domain_chg(fn){
		var dom = fn.domainList.value;
		if(dom==''){
			fn.domain.value='';
			fn.domain.readOnly=true;
		}else if(dom=='직접입력'){
			fn.domain.value='';
			fn.domain.readOnly=false;
			fn.domain.focus();
		}else{
			fn.domain.value=dom;
			fn.domain.readOnly=true;
		}
	}
</script>
</head>
<body>
	<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp"/>
		<jsp:include page="../inc/LeftMenu.jsp" />
		<div class="content">
			<form action="../zibbab/accountFind.do" method="post" onsubmit="return inputChk(this);">
				<input type="radio" name="isfind" value="1" onclick="hide(this.form)" checked/>아이디 찾기
				<input type="radio" name="isfind" value="2" onclick="hide(this.form)"/>패스워드 찾기
				<table>
					<tr id="inputId" style="display: none;">
						<th><span>아이디</span></th>
						<td><input type="text" name="UserId" /></td>
					</tr>
					<tr>
						<th>
							<span>이메일</span>
						</th>
						<td>
							<input type="text" name="email"/>@<input type="text" name="domain"/>
							<select name="domainList" onchange="domain_chg(this.form)">
								<option value="">====선택하세요====</option>
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="daum.net">daum.net</option>
								<option value="hanmail.net">hanmail.net</option>
								<option value="직접입력">직접입력</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><span>연락처</span></th>
						<td><input type="text" name="phone"/></td>
					</tr>
				</table>
				<button class="btn btn-primary">계정찾기</button>
				<button type="reset" class="btn btn-primary">다시작성</button>
			</form>
			<c:if test="${not empty UserId && param.isfind eq 1}">
				찾으시는 아이디는 ${UserId}입니다.
			</c:if>
			<c:if test="${not empty UserPass && param.isfind eq 2}">
				찾으시는 비밀번호는 ${UserPass}입니다.
			</c:if>
			<c:if test="${not empty requestScope.SearchErrMsg}">
				찾으시는 계정이 존재하지 않습니다.
			</c:if>
		</div> 
		<jsp:include page="../inc/Copyright.jsp"/>
	</div>
</div>
</body>
</html>