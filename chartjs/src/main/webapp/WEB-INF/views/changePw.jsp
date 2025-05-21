<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>

<script>
    // DOMContentLoaded 이벤트로 DOM이 완전히 로드된 후 코드 실행
    window.addEventListener("DOMContentLoaded", function() {
        const passwordInput = document.querySelector("input[name='password']");
        
        if (passwordInput) {
            passwordInput.addEventListener("blur", function() {
                const pw = this.value;
                fetch("/checkDuplicatePw", {
                    method: "POST",
                    headers: { "Content-Type": "application/x-www-form-urlencoded" },
                    body: "password=" + encodeURIComponent(pw)
                })
                .then(res => res.text())
                .then(data => {
                    if (data === 'true') {
                        alert("이미 사용한 비밀번호입니다.");
                    }
                });
            });
        }
    });
</script>

</head>
<body>
	<c:if test="${loginMember != null}">
		<h1>비밀번호변경</h1>
		<form method="post" action="/changePw">
			<div>
				<div>memberPw:</div>
				<div><input type="password" name="password"></div>
				<div><button type="submit">비밀번호변경</button></div>
			</div>
		</form>
	</c:if>
	
	<c:if test="${loginMember == null}">
		<script>
			window.location.href = '/login';
		</script>
	</c:if>
</body>
</html>
