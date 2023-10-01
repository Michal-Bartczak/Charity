<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<jsp:include page="headerForm.jsp"/>


        <h1 class="text-center-header">
           Zarządzaj swoimi zbiórkami

        </h1>
<div class="table-donations">
    <table class="table-width">
        <tr>
            <td class="table-item-donations">ID</td>
            <td class="table-item-donations">ORGANIZACJA</td>
            <td class="table-item-donations">DATA</td>
<%--            <td class="table-item-donations-3" style="width: 178px">AKCJA</td>--%>


        </tr>
   <c:forEach var="donation" items="${donations}">
       <tr>
      <td class="table-item-donations-2">${donation.id}</td>
      <td class="table-item-donations-2">${donation.institution.name}</td>
      <td class="table-item-donations-2">${donation.pickUpDate}</td>
           <td class="table-item-donations-2"><a href="/user/delete/${donation.id}">Usuń</a> </td>
           <td class="table-item-donations-2"><a href="/user/details/${donation.id}">Szczegóły</a> </td>
       </tr>
   </c:forEach>
    </table>
</div>



</header>

<section class="form--steps">
    <div class="form--steps-instructions">
    </div>
</section>

<jsp:include page="footer.jsp"/>