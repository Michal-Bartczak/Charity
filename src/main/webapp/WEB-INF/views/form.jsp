<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<jsp:include page="headerForm.jsp"/>
<div class="slogan container container--90">
    <div class="slogan--item">
        <h1>
            Oddaj rzeczy, których już nie chcesz<br/>
            <span class="uppercase">potrzebującym</span>
        </h1>

        <div class="slogan--steps">
            <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
            <ul class="slogan--steps-boxes">
                <li>
                    <div><em>1</em><span>Wybierz rzeczy</span></div>
                </li>
                <li>
                    <div><em>2</em><span>Spakuj je w worki</span></div>
                </li>
                <li>
                    <div><em>3</em><span>Wybierz fundację</span></div>
                </li>
                <li>
                    <div><em>4</em><span>Zamów kuriera</span></div>
                </li>
            </ul>
        </div>
    </div>
</div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form modelAttribute="donation" action="/user/form" method="post">
            <div data-step="1" class="active" id="step1">
                <h3>Zaznacz co chcesz oddać:</h3>
                <div class="empty-things"></div>
                <c:forEach var="category" items="${categoriesThings}">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:checkbox path="categories" value="${category.id}"/>
                            <span class="checkbox"></span>
                            <span class="description">${category.name}</span>

                        </label>
                    </div>
                </c:forEach>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step" id="next-button">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                        <form:input path="quantity" type="number" id="count-bags" step="1" min="1"/>
                        <form:errors path="quantity" class="error"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step" id="next-button-step-2">Dalej</button>
                </div>
            </div>


            <!-- STEP 4 -->
            <div data-step="3" id="step3">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>
                <c:forEach var="item" items="${institutions}" varStatus="loop">
                    <div class="form-group form-group--checkbox">
                        <label>
                                <%--                            zmień id--%>
                            <form:radiobutton path="institution" id="organization${loop.index}" value="${item.id}"/>
                            <form:errors path="institution" class="error"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                <div class="title">${item.name}</div>
                <div class="subtitle">${item.description}</div>
            </span>
                        </label>
                    </div>
                </c:forEach>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>

            </div>

            <!-- STEP 5 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label for="street"> Ulica <form:input path="street"/> </label>
                            <form:errors path="street" class="error"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label for="city"> Miasto <form:input path="city"/> </label>
                            <form:errors path="city" class="error"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label for="zipCode">
                                Kod pocztowy <form:input path="zipCode"/>
                                <form:errors path="zipCode" class="error"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label for="phoneNumber">
                                Numer telefonu <form:input path="phoneNumber"/>
                                <form:errors path="phoneNumber" class="error"/>
                            </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label for="pickUpDate"> Data <form:input path="pickUpDate" type="date"/> </label>
                            <form:errors path="pickUpDate" class="error"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label for="pickUpTime"> Godzina <form:input path="pickUpTime" type="time"/> </label>
                            <form:errors path="pickUpTime" class="error"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera
                                <form:textarea path="pickUpComment" rows="5" id="description"/>
                                <form:errors path="pickUpComment" class="error"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step" id="next-button-step-4">Dalej</button>
                </div>
            </div>

            <!-- STEP 6 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text" id="quantityBegs"></span
                                >
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text" id="inputOrganization"></span
                                >
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul>
                                <li><span class="streetForm"></span></li>
                                <li><span class="cityForm"></span></li>
                                <li><span class="zipCodeForm"></span></li>
                                <li><span class="phoneNumberForm"></span></li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul>
                                <li><span class="dataForm"></span></li>
                                <li><span class="timeForm"></span></li>
                                <li><span class="descriptionForm"></span></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn">Potwierdzam</button>
                </div>
            </div>
        </form:form>
    </div>
</section>

<jsp:include page="footer.jsp"/>