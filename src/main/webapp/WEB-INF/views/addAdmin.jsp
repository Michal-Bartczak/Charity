<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<section class="login-page">
    <h2>Załóż konto</h2>
<form action="/admin" method="post">
    <input type="text" name="username"> Username
    <input type="password" name="password"> password
    <input type="email" name="email"> email
    <button type="submit">Wyślij</button>

</form>
</section>

<jsp:include page="footer.jsp"/>