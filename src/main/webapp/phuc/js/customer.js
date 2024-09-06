// const customers = [
//     {id: 1, username: 'phucSG', name: 'Hồ Văn Phúc', email: 'lmht721@gmail.com', phone: '0935641901', cccd: '123456789987', role: 'ROLE_USER'},
//     {id: 2, username: 'manDN', name: 'Mẫn', email: 'nguyenva@gmail.com', phone: '0912345678', cccd: '223456789987', role: 'ROLE_USER'},
//     {id: 3, username: 'hieuDN', name: 'Hiếu', email: 'tranvb@gmail.com', phone: '0923456789', cccd: '323456789987', role: 'ROLE_USER'},
//     {id: 4, username: 'congDN', name: 'Công', email: 'levanc@gmail.com', phone: '0934567890', cccd: '423456789987', role: 'ROLE_USER'},
//     {id: 5, username: 'linhDN', name: 'Linh', email: 'phamtd@gmail.com', phone: '0945678901', cccd: '523456789987', role: 'ROLE_USER'},
//     {id: 6, username: 'haoSmall', name: 'Hào nhỏ', email: 'doante@gmail.com', phone: '0956789012', cccd: '623456789987', role: 'ROLE_USER'},
//     {id: 7, username: 'haoBig', name: 'Hào lớn', email: 'vuongvf@gmail.com', phone: '0967890123', cccd: '723456789987', role: 'ROLE_USER'},
//     {id: 8, username: 'quangSG', name: 'Quang', email: 'ngotg@gmail.com', phone: '0978901234', cccd: '823456789987', role: 'ROLE_USER'},
//     {id: 9, username: 'tienSG', name: 'Tiến', email: 'huynhvh@gmail.com', phone: '0989012345', cccd: '923456789987', role: 'ROLE_USER'},
//     {id: 10, username: 'vyDN', name: 'Vỹ', email: 'huynhvh@gmail.com', phone: '0989012345', cccd: '923456789987', role: 'ROLE_USER'},
// ];
//
// const itemsPerPage = 8;
// let currentPage = 1;
//
// function renderTable() {
//     const tableBody = document.getElementById('customerTable');
//     tableBody.innerHTML = '';
//
//     const startIndex = (currentPage - 1) * itemsPerPage;
//     const endIndex = startIndex + itemsPerPage;
//
//     const paginatedCustomers = customers.slice(startIndex, endIndex);
//
//     paginatedCustomers.forEach(customer => {
//         const row = `<tr>
//             <th scope="row">${customer.id}</th>
//             <td>${customer.username}</td>
//             <td>${customer.name}</td>
//             <td>${customer.email}</td>
//             <td>${customer.phone}</td>
//             <td>${customer.cccd}</td>
//             <td>${customer.role}</td>
//             <td>
//                 <button class="btn btn-warning btn-sm">Sửa</button>
//                 <button class="btn btn-danger btn-sm">Xóa</button>
//             </td>
//         </tr>`;
//         tableBody.insertAdjacentHTML('beforeend', row);
//     });
//
//     document.getElementById('prevPage').parentElement.classList.toggle('disabled', currentPage === 1);
//     document.getElementById('nextPage').parentElement.classList.toggle('disabled', endIndex >= customers.length);
// }
//
// document.getElementById('prevPage').addEventListener('click', function(event) {
//     event.preventDefault();
//     if (currentPage > 1) {
//         currentPage--;
//         renderTable();
//     }
// });
//
// document.getElementById('nextPage').addEventListener('click', function(event) {
//     event.preventDefault();
//     if (currentPage * itemsPerPage < customers.length) {
//         currentPage++;
//         renderTable();
//     }
// });
//
// renderTable();

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

    // Thêm sự kiện input để xóa lỗi khi người dùng bắt đầu nhập lại
    const inputs = document.querySelectorAll("input");
    inputs.forEach(function(input) {
        input.addEventListener('input', function() {
            console.log(`Input event triggered on: ${input.id}`);  // Kiểm tra sự kiện input
            clearError(input.id);  // Xóa lỗi khi người dùng nhập lại
        });

        input.addEventListener('focus', function() {
            console.log(`Focus event triggered on: ${input.id}`);  // Kiểm tra sự kiện focus
            clearError(input.id);  // Xóa lỗi khi người dùng focus vào trường nhập
        });
    });

    customerForm.addEventListener("submit", function(event) {
        let isValid = true;

        // Reset tất cả các lỗi trước khi kiểm tra lại
        resetValidation();

        // Kiểm tra tên đăng nhập
        let username = document.getElementById("username").value.trim();
        if (username === "") {
            setError("username", "Tên đăng nhập không được để trống.");
            isValid = false;
        }

        // Kiểm tra họ và tên
        let customer_name = document.getElementById("customer_name").value.trim();
        if (customer_name === "") {
            setError("customer_name", "Họ và tên không được để trống.");
            isValid = false;
        }

        // Kiểm tra email
        let email = document.getElementById("customer_email").value.trim();
        let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            setError("customer_email", "Email không hợp lệ.");
            isValid = false;
        }

        // Kiểm tra số điện thoại
        let phone = document.getElementById("customer_phone").value.trim();
        let phonePattern = /^[0-9]{10}$/;
        if (!phonePattern.test(phone)) {
            setError("customer_phone", "Số điện thoại không hợp lệ. Số điện thoại phải có 10 chữ số.");
            isValid = false;
        }

        // Kiểm tra căn cước công dân
        let citizen = document.getElementById("customer_citizen").value.trim();
        let citizenPattern = /^[0-9]{12}$/;
        if (!citizenPattern.test(citizen)) {
            setError("customer_citizen", "Căn cước công dân không hợp lệ. Phải có 12 chữ số.");
            isValid = false;
        }

        // Ngăn form submit nếu không hợp lệ
        if (!isValid) {
            event.preventDefault();  // Ngăn form submit nếu không hợp lệ
            return false;  // Giữ modal mở
        }

        // Nếu hợp lệ, modal sẽ đóng và form sẽ gửi
        var modalElement = document.getElementById('customerFormModal');
        var modal = bootstrap.Modal.getInstance(modalElement);  // Lấy instance của modal Bootstrap
        modal.hide();  // Đóng modal khi form hợp lệ
    });

    // Hàm thêm lỗi vào input và hiển thị thông báo
    function setError(inputId, errorMessage) {
        let inputElement = document.getElementById(inputId);
        let errorElement = document.getElementById(inputId + "-error");

        // Thêm class lỗi vào input
        inputElement.classList.add("is-invalid");

        // Hiển thị thông báo lỗi
        errorElement.innerHTML = '<i class="fas fa-exclamation-triangle error-icon"></i>' + errorMessage;
    }

    // Hàm xóa lỗi khi người dùng bắt đầu nhập lại
    function clearError(inputId) {
        let inputElement = document.getElementById(inputId);
        let errorElement = document.getElementById(inputId + "-error");

        // Kiểm tra xem hàm có được gọi không
        console.log(`clearError called for: ${inputId}`);

        // Xóa class lỗi và thông báo lỗi
        inputElement.classList.remove("is-invalid");
        errorElement.innerHTML = "";
    }

    // Hàm reset tất cả các lỗi
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

