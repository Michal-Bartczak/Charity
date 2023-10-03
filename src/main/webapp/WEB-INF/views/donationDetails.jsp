<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<jsp:include page="headerForm.jsp"/>


<h1 class="text-center-header">
    Szczegóły zbiórki
</h1>
<div class="content-details">
    <h3 class="data-header">
        Dane zbiórki:
    </h3>
    <p>ID: ${donationDetails.id}</p>
    <p>ILOŚĆ: ${donationDetails.quantity}</p>
    <p> KATEGORIE: <c:forEach var="category" items="${donationDetails.categories}">
        ${category.name}
    </c:forEach></p>

    <h3>Adres odbioru:</h3>
    <p> Instytucja: ${donationDetails.institution.name}</p>
    <p>Ulica: ${donationDetails.street}</p>
    <p> Miasto: ${donationDetails.city}</p>
    <p> Kod pocztowy: ${donationDetails.zipCode}</p>

    <h3>Termin odbioru:</h3>
    <p> Data odbioru: ${donationDetails.pickUpDate}</p>
    <p> Godzina odbioru: ${donationDetails.pickUpTime}</p>
    <p> Komentarz: ${donationDetails.pickUpComment}</p>

    <h3>Kontakt:</h3>
    <p> Telefon: ${donationDetails.phoneNumber} <a href="/user/collections" class="back-button">Wróć</a></p>
</div>

</header>

<section class="form--steps">
    <div class="form--steps-instructions">
    </div>
</section>

<jsp:include page="footer.jsp"/>