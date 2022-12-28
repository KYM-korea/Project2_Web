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
		if(fn.UserId.value==''){
			alert('아이디가 입력되지 않았습니다.');
			fn.UserId.focus();
			return false;
		}
		if(fn.UserPass.value==''){
			alert('비밀번호가 입력되지 않았습니다.');
			fn.UserPass.focus();
			return false;
		}
		if(fn.UserPass_ch.value==''){
			alert('비밀번호가 입력되지 않았습니다.');
			fn.UserPass.focus();
			return false;
		}
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
		if(!(fn.RegAgree.checked)){
			alert('약관 동의를 하지않았습니다.');
			return false;
		}
		if(!(fn.UserId2.value=='true')){
			alert('중복체크를 해주세요.')
			return false;
		}
	}
	function domain_chg(fn){
		var dom = fn.DomainList.value;
		
		if(dom==''){
			fn.Domain.value='';
			fn.Domain.readOnly=true;
		}else if(dom=='직접입력'){
			fn.Domain.value='';
			fn.Domain.readOnly=false;
			fn.Domain.focus();
		}else{
			fn.Domain.value=dom;
			fn.Domain.readOnly=true;
		}
	}
	function idCheck(frm){		
		if(frm.UserId.value==""){
			alert("아이디를 먼저 입력하셈");
			frm.UserId.focus();
		}
		else{
			window.name="regiform";
			open('../zibbab/idchk.do?UserId='+frm.UserId.value ,'idchk',
				'width=400, height=200, left=0, top=0, location=yes, toolbar=yes, menybar=yes, scrollbars=yes, resizable=yes');			
		}		
	}
	function PwdCheck(frm){
		var userpass = frm.UserPass.value;
		var userpass_ch = frm.UserPass_ch.value;
		var pwdchk = document.getElementById("pwdchk");
		
		if(userpass!="" || userpass_ch!=""){
			if(userpass != userpass_ch){
				pwdchk.innerHTML = "비밀번호가 다름";
				frm.pwdChkResult.value = "false";
			}else{
				pwdchk.innerHTML = "비밀번호가 같음";
				pwdchk.style = "color:green";
				frm.pwdChkResult.value = "true";
			}
		}else{
			pwdchk.innerHTML = "";
			frm.pwdChkResult.value = "false";
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
			<form action="../zibbab/regi.do" method="post" onsubmit="return checkForm(this);">
				<table width="600px">
					<colgroup>
						<col width="150px"/>
						<col width="*"/>
					</colgroup>
					<tr>
						<th>아이디</th>
						<td>
							<i class="member_input-group <member_input-group></member_input-group>-icon-label"></i>
							<input type="text" name="UserId" id="UserId" />
							<input type="hidden" name="UserId2" id="UserId2"/>
							<button type="button" class="btn btn-outline-danger btn-sm" onclick="idCheck(this.form)">중복확인</button>
						</td>
					</tr>
					<tr>
						<th>패스워드</th>
						<td>
							<input type="password" name="UserPass" onkeyup="PwdCheck(this.form);"/> <span id="pwdchk" style="color:red;"></span>
						</td>
					</tr>
					<tr>
						<th>패스워드 확인</th>
						<td>
							<input type="password" name="UserPass_ch" onkeyup="PwdCheck(this.form);"/>
							<input type="hidden" name="pwdChkResult" />
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" name="UserName" />
						</td>
					</tr>
					<tr>
						<th>연락처</th>
						<td>
							<input type="text" name="Phone" />
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="text" name="Email" />@<input type="text" name="Domain"/>
							<select name="DomainList" onchange="domain_chg(this.form)">
								<option value="">====선택하세요====</option>
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="daum.net">daum.net</option>
								<option value="hanmail.net">hanmail.net</option>
								<option value="직접입력">직접입력</option>
							</select>
						</td>
					</tr>
				</table>
				<br />
				<input type="checkbox" name="RegAgree" /> <b>개인정보 수집에 동의 확인합니다.</b>
				<br /><br />
				<button class="btn btn-outline-primary" >가입하기</button>
				<button type="button" class="btn btn-outline-primary" onclick="location.reload();">다시작성</button>
			</form>
		</div>
		<jsp:include page="../inc/Copyright.jsp"/>
	</div>
</div>
</body>
</html>