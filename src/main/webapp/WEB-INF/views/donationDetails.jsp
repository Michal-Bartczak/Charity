<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<jsp:include page="headerForm.jsp"/>


<h1 class="text-center-header">
    Szczegóły zbiórki
</h1>
<div class="content-details">
ID: ${donationDetails.id}<br>
ILOŚĆ: ${donationDetails.quantity}<br>

</div>

</header>

<section class="form--steps">
    <div class="form--steps-instructions">
    </div>
</section>

<jsp:include page="footer.jsp"/>