document.getElementById('addCustomerBtn').addEventListener('click', function() {
    var modal = new bootstrap.Modal(document.getElementById('customerFormModal'));
    modal.show();
});

function editCustomer(id, edit_username, edit_customer_name, edit_customer_email, edit_customer_phone, edit_customer_citizen, edit_customer_role) {

    document.getElementById('editCustomerId').value = id;
    document.getElementById('edit_username').value = edit_username;
    document.getElementById('edit_customer_name').value = edit_customer_name;
    document.getElementById('edit_customer_email').value = edit_customer_email;
    document.getElementById('edit_customer_phone').value = edit_customer_phone;
    document.getElementById('edit_customer_citizen').value = edit_customer_citizen;
    document.getElementById('edit_customer_role').value = edit_customer_role;

    const editModal = new bootstrap.Modal(document.getElementById('editCustomerFormModal'));
    editModal.show();
}

document.addEventListener('DOMContentLoaded', function() {
    const customerForm = document.getElementById("customerForm");

    const inputs = document.querySelectorAll("input");
    inputs.forEach(function(input) {
        input.addEventListener('input', function() {
            console.log(`Input event triggered on: ${input.id}`);
            clearError(input.id);
        });

        input.addEventListener('focus', function() {
            console.log(`Focus event triggered on: ${input.id}`);
            clearError(input.id);
        });
    });

    customerForm.addEventListener("submit", function(event) {
        let isValid = true;

        resetValidation();

        let username = document.getElementById("username").value.trim();
        if (username === "") {
            setError("username", "Tên đăng nhập không được để trống.");
            isValid = false;
        }

        let customer_name = document.getElementById("customer_name").value.trim();
        if (customer_name === "") {
            setError("customer_name", "Họ và tên không được để trống.");
            isValid = false;
        }

        let email = document.getElementById("customer_email").value.trim();
        let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            setError("customer_email", "Email không hợp lệ.");
            isValid = false;
        }

        let phone = document.getElementById("customer_phone").value.trim();
        let phonePattern = /^[0-9]{10}$/;
        if (!phonePattern.test(phone)) {
            setError("customer_phone", "Số điện thoại không hợp lệ. Số điện thoại phải có 10 chữ số.");
            isValid = false;
        }

        let citizen = document.getElementById("customer_citizen").value.trim();
        let citizenPattern = /^[0-9]{12}$/;
        if (!citizenPattern.test(citizen)) {
            setError("customer_citizen", "Căn cước công dân không hợp lệ. Phải có 12 chữ số.");
            isValid = false;
        }

        if (!isValid) {
            event.preventDefault();
            return false;
        }

        var modalElement = document.getElementById('customerFormModal');
        var modal = bootstrap.Modal.getInstance(modalElement);
        modal.hide();
    });

    function setError(inputId, errorMessage) {
        let inputElement = document.getElementById(inputId);
        let errorElement = document.getElementById(inputId + "-error");

        inputElement.classList.add("is-invalid");

        errorElement.innerHTML = '<i class="fas fa-exclamation-triangle error-icon"></i>' + errorMessage;
    }

    function clearError(inputId) {
        let inputElement = document.getElementById(inputId);
        let errorElement = document.getElementById(inputId + "-error");

        console.log(`clearError called for: ${inputId}`);

        inputElement.classList.remove("is-invalid");
        errorElement.innerHTML = "";
    }

    function resetValidation() {
        let inputs = document.querySelectorAll(".is-invalid");
        inputs.forEach(function(input) {
            input.classList.remove("is-invalid");
        });

        let errors = document.querySelectorAll(".error-message");
        errors.forEach(function(error) {
            error.innerHTML = "";
        });
    }
});

