<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${loginMember == null}">
		<!-- 로그인이 되어 있지 않다면 -->
		<form method="post" action="/login">
			<div>
				<div>memberId:</div>
				<div>
					<input type="text" name="id">
				</div>
				<div>memberPw:</div>
				<div><input type="password" name="password"></div>
				<div><button type="submit">로그인</button></div>
			</div>
		</form>
	</c:if>
	
	<c:if test="${loginMember != null}">
		<!-- 로그인 되어 있다면 -->
		<div>
			${loginMember.id}님 반갑습니다.
		</div>
		<div><a href="/logout">로그아웃</a></div>
	</c:if>
</body>
</html>