document.addEventListener("DOMContentLoaded", function() {
    const step1Container = document.getElementById("step1");
    const checkboxes = step1Container.querySelectorAll('input[type="checkbox"]');
    const nextButton = step1Container.querySelector("#next-button");

    updateNextButtonState();


    checkboxes.forEach(function(checkbox) {
        checkbox.addEventListener("change", function() {
            updateNextButtonState();
        });
    });

    function updateNextButtonState() {
        const atLeastOneChecked = Array.from(checkboxes).some(function(checkbox) {
            return checkbox.checked;
        });

        if (atLeastOneChecked) {
            nextButton.removeAttribute("disabled");
        } else {
            nextButton.setAttribute("disabled", "disabled");
        }
    }
});
const inputBegs = document.getElementById("count-bags");
const nextButton = document.getElementById("next-button-step-2");
nextButton.setAttribute("disabled", "disabled");

inputBegs.addEventListener("input", function () {
    if (!inputBegs.value) {
        nextButton.setAttribute("disabled", "disabled");
    } else {
        nextButton.removeAttribute("disabled");
    }
});
const step3Container = document.getElementById("step3");
const radioButtons = step3Container.querySelectorAll('input[type="radio"]');
const nextButtonInstitution = step3Container.querySelector(".next-step");


updateNextButtonState();


radioButtons.forEach(function(radioButton) {
    radioButton.addEventListener("change", function() {
        updateNextButtonState();
    });
});


function updateNextButtonState() {
    const isAnyRadioButtonChecked = Array.from(radioButtons).some(function(radioButton) {
        return radioButton.checked;
    });

    if (isAnyRadioButtonChecked) {
        nextButtonInstitution.removeAttribute("disabled");
    } else {
        nextButtonInstitution.setAttribute("disabled", "disabled");
    }
}
document.addEventListener("DOMContentLoaded", function() {
    const streetInput = document.getElementById("street");
    const cityInput = document.getElementById("city");
    const zipCodeInput = document.getElementById("zipCode");
    const pickUpDateInput = document.getElementById("pickUpDate");
    const pickUpTimeInput = document.getElementById("pickUpTime");
    const phoneNumberInput = document.getElementById("phoneNumber");
    const nextButtonData = document.querySelector("#next-button-step-4");

    nextButtonData.setAttribute("disabled", "disabled");

    streetInput.addEventListener("input", updateNextButtonState);
    cityInput.addEventListener("input", updateNextButtonState);
    zipCodeInput.addEventListener("input", updateNextButtonState);
    pickUpDateInput.addEventListener("input", updateNextButtonState);
    pickUpTimeInput.addEventListener("input", updateNextButtonState);
    phoneNumberInput.addEventListener("input", updateNextButtonState);

    function updateNextButtonState() {
        const streetValue = streetInput.value;
        const cityValue = cityInput.value;
        const zipCodeValue = zipCodeInput.value;
        const pickUpDateValue = pickUpDateInput.value;
        const pickUpTimeValue = pickUpTimeInput.value;
        const phoneNumberValue = phoneNumberInput.value;

        // Walidacja miasta i ulicy: Przynajmniej 3 znaki
        const isStreetValid = streetValue.length >= 3;
        const isCityValid = cityValue.length >= 3;

        // Walidacja kodu pocztowego: Format XX-XXX
        const zipCodePattern = /^\d{2}-\d{3}$/;
        const isZipCodeValid = zipCodePattern.test(zipCodeValue);

        // Walidacja daty i czasu: Zawartość niepusta
        const today = new Date();
        const selectedDate = new Date(pickUpDateValue);
        const isPickUpDateValid = selectedDate >= today;

        const isPickUpTimeValid = pickUpTimeValue.trim() !== "";

        // Walidacja numeru telefonu: Dokładnie 9 cyfr
        const phoneNumberPattern = /^\d{9}$/;
        const isPhoneNumberValid = phoneNumberPattern.test(phoneNumberValue);

        // Sprawdzenie, czy wszystkie pola są wypełnione i spełniają warunki walidacji
        const isFormValid =
            isStreetValid && isCityValid && isZipCodeValid &&
            isPickUpDateValid && isPickUpTimeValid && isPhoneNumberValid;

        if (isFormValid) {
            nextButtonData.removeAttribute("disabled");
        } else {
            nextButtonData.setAttribute("disabled", "disabled");
        }
    }
});


document.addEventListener("DOMContentLoaded", function() {
    const radioButtons = document.querySelectorAll('input[type="radio"]');
    const selectedOrganizationName = document.getElementById("inputOrganization");
    const quantityBegs = document.getElementById("quantityBegs");


    function updateSelectedOrganization() {
        const checkedRadioButton = document.querySelector('input[type="radio"]:checked');
        if (checkedRadioButton) {
            const organizationName = checkedRadioButton.parentElement.querySelector(".title").textContent.trim();
            selectedOrganizationName.textContent = `Dla fundacji ${organizationName}`;
        }
    }

    const bagsForm = document.getElementById("count-bags");
    bagsForm.addEventListener("input", function() {
        quantityBegs.textContent = bagsForm.value + ` worki`;
    });

    const streetInput = document.getElementById("street");
    streetInput.addEventListener("input", function() {
        updateStreet();
    });

    const cityInput = document.getElementById("city");
    cityInput.addEventListener("input", function() {
        updateCity();
    });

    const zipCodeInput = document.getElementById("zipCode");
    zipCodeInput.addEventListener("input", function() {
        updateZipCode();
    });
    const phoneNumberInput = document.getElementById("phoneNumber");
    phoneNumberInput.addEventListener("input", function (){
        updatePhoneNumber();
    });
    const dataInput = document.getElementById("pickUpDate");
    dataInput.addEventListener("input", function (){
        updateData();
    });
    const timeInput = document.getElementById("pickUpTime");
    timeInput.addEventListener("input", function (){
        updateTime();
    });
    const descriptionInput = document.getElementById("description");
    descriptionInput.addEventListener("input", function (){
        updateComment();
    });
    // Dodajmy nasłuchiwanie na zmiany w opcjach radio
    radioButtons.forEach(function(radioButton) {
        radioButton.addEventListener("change", function() {
            updateSelectedOrganization();
        });
    });


    updateSelectedOrganization();
    updateStreet();
    updateCity();
    updateZipCode();
    updatePhoneNumber();
    updateData();
    updateTime();
    updateComment();
});

function updateStreet() {
    const streetValue = document.getElementById("street").value;
    document.querySelector(".streetForm").textContent = streetValue;
}

function updateCity() {
    const cityValue = document.getElementById("city").value;
    document.querySelector(".cityForm").textContent = cityValue;
}

function updateZipCode() {
    const zipCodeValue = document.getElementById("zipCode").value;
    document.querySelector(".zipCodeForm").textContent = zipCodeValue;
}
function updatePhoneNumber(){
    const phoneNumberValue = document.getElementById("phoneNumber").value;
    document.querySelector(".phoneNumberForm").textContent = phoneNumberValue;

}

function updateData(){
    const dataValue = document.getElementById("pickUpDate").value;
    document.querySelector(".dataForm").textContent = dataValue;
}
function updateTime(){
    const timeValue = document.getElementById("pickUpTime").value;
    document.querySelector(".timeForm").textContent = timeValue;
}
function updateComment(){
    const commentValue = document.getElementById("description").value
    document.querySelector(".descriptionForm").textContent = commentValue;
}

