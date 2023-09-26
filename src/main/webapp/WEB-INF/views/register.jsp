<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form modelAttribute="registrationForm" action="/register" method="post">
        <div class="form-group">
            <form:input path="email" type="email" name="email" placeholder="Email" />
            <form:errors path="email" class="error"/>
        </div>
        <div class="form-group">
            <form:input path="password" type="password" name="password" placeholder="Hasło" />
            <form:errors path="password" class="error"/>
        </div>
        <div class="form-group">
            <form:input path="confirmPassword" type="password" name="password2" placeholder="Powtórz hasło" />
            <form:errors path="confirmPassword" class="error"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="#" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<jsp:include page="footer.jsp"/>