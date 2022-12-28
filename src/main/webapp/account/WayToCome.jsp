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
<title>집밥 천국 - 오시는길</title>
<style>
* {  
  box-sizing: border-box;
}   

main {
  height: 100%;  
}

#map {
/* 탑 메뉴에 가려진 컨텐츠 보이게하기 */
     padding-bottom: 70px;
     display: block;   
}

h3, hr{
   display: block;   
   padding-left: 10px;
}
body {
    width: 100%;
    height: 100%;
    padding: 30px;
    margin: 0;
}

#contact_map {
    width: 800px;
    height: 500px;
}

#contact_map iframe {
    width: 100%;
    height: 100%;
    border:0;
}
    </style>
</head>
<body>
<div class="AllWrap">
	<div class="container">
		<jsp:include page="../inc/TopMenu.jsp" />
		<jsp:include page="../inc/LeftMenu.jsp" />
			<div class="content">
				<div id="map" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy">
				<h3>집밥 천국 - 본사</h3>
				<hr />
				<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3162.3922389631352!2d126.98227256556895!3d37.56938043172238!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca2e8cf2cf5f5%3A0x8304726aa55cd56d!2z642U7KGw7J2ASVTslYTsubTrjbDrr7jtlZnsm5Ag7KKF6rCB7KCQ!5e0!3m2!1sko!2skr!4v1672221578148!5m2!1sko!2skr" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
				</div>
			</div>
		<jsp:include page="../inc/Copyright.jsp" />
	</div>
</div>
</body>
</html>