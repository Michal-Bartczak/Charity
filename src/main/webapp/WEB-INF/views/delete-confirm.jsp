<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<jsp:include page="headerForm.jsp"/>


<h1 class="text-center-header">
   Potwiedź usunięcie zbiórki
</h1>
<div class="content-details">
   <h3>Twoja zbiórka zostanie usunięta na zawsze, czy na pewno chcesz ją usunąć ?</h3>
    <p>Czy na pewno chcesz usunąć zbiórkę o ID: ${donationId}?</p>

    <form method="post" action="/user/delete/${donationId}">
    <div>   <button type="submit" class="confirm-button">Potwierdź usunięcie</button>
    </form>

    <a href="/user/collections" class="back-button">Anuluj</a></div>
</div>

</header>

<section class="form--steps">
    <div class="form--steps-instructions">
    </div>
</section>

<jsp:include page="footer.jsp"/>