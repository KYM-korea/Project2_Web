<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="../inc/style/layout.css?after">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>집밥 천국 - 회원 가입</title>
<script>
	function checkForm(fn){
		if(fn.pwdChkResult.value=='false'){
			alert('비밀번호가 일치하지 않습니다.');
			fn.UserPass.focus();
			return false;
		}
		if(fn.UserName.value==''){
			alert('이름이 입력되지 않았습니다.');
			fn.UserName.focus();
			return false;
		}
		if(fn.Phone.value==''){
			alert('연락처가 입력되지 않았습니다.');
			fn.Phone.focus();
			return false;
		}
		if(fn.Email.value==''){
			alert('이메일이 입력되지 않았습니다.');
			fn.Email.focus();
			return false;
		}
		if(fn.Domain.value=='' || fn.DomainList.value=='none'){
			alert('도메인을 입력/선택하지 않았습니다.');
			return false;
		}
	}
	function PwdCheck(frm){
		var userpass = frm.UserPass.value;
		var userpass_ch = frm.UserPass_ch.value;
		var pwdchk = document.getElementById("pwdchk");
		if(userpass!="" && userpass_ch!=""){
			if(userpass != userpass_ch){
				pwdchk.innerHTML = "비밀번호가 다름";
				frm.pwdChkResult.value = "false";
			}else{
				pwdchk.innerHTML = "비밀번호가 같음";
				frm.pwdChkResult.value = "true";
			}
		}else{
			pwdchk.innerHTML = "";
			frm.pwdChkResult.value = "true";
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
			<form action="../zibbab/myPage.do" method="post" onsubmit="return checkForm(this);">
				<table width="600px">
					<colgroup>
						<col width="150px"/>
						<col width="*"/>
					</colgroup>
					<tr>
						<th>아이디</th>
						<td>
							<input type="text" name="UserId" readOnly value="${map.id }" />
						</td>
					</tr>
					<tr>
						<th>패스워드</th>
						<td>
							<input type="password" name="PrevUserPass" />
						</td>
					</tr>
					<tr>
						<th>변경 패스워드</th>
						<td>
							<input type="password" name="UserPass" onkeyup="PwdCheck(this.form);"/> <span id="pwdchk" style="color:red;"></span>
						</td>
					</tr>
					<tr>
						<th>패스워드 확인</th>
						<td>
							<input type="password" name="UserPass_ch" onkeyup="PwdCheck(this.form);"/>
							<input type="hidden" name="pwdChkResult" value="true"/>
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" name="UserName" value="${map.uname }"/>
						</td>
					</tr>
					<tr>
						<th>연락처</th>
						<td>
							<input type="text" name="Phone" value="${map.phone }"/>
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="text" name="Email" value="${map.email }"/>@<input type="text" name="Domain" value="${map.domain }"/>
						</td>
					</tr>
				</table>
				<br />
				<button class="btn btn-outline-primary" >수정완료</button>
				<button type="reset" class="btn btn-outline-primary">다시작성</button>
			</form>
		</div>
		<jsp:include page="../inc/Copyright.jsp"/>
	</div>
</div>
</body>
</html>