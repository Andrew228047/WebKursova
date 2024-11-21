document.addEventListener("DOMContentLoaded", function() {
    // Видалення пацієнта
    $(document).on('click', '.delete-button', function() {
        const patientId = $(this).data('id');
        console.log("Видалення пацієнта з ID:", patientId);
        if (confirm("Ви впевнені, що хочете видалити пацієнта?")) {
            deletePatient(patientId);
        }
    });

    // Відкриття модального вікна редагування
    $(document).on('click', '.edit-button', function() {
        const patientId = $(this).data('id'); // Отримуємо ID пацієнта з data-атрибута
        console.log("Редагування пацієнта з ID:", patientId);
        openEditModal(patientId);
    });

    // Збереження змін після редагування
    $('#saveChangesBtn').click(function(e) {
        e.preventDefault();

        const patientId = $('#editPatientId').val();
        console.log("Збереження змін для пацієнта з ID:", patientId);

        const patientData = {
            patientName: $('#editPatientName').val(),
            patientIll: {
                illName: $('#editIllName').val(),
                illDescription: $('#editIllDescription').val(),
                patientRegistration: $('#editPatientRegistration').val()
            }
        };

        // Перевірка на наявність даних
        if (!patientData.patientName || !patientData.patientIll.illName) {
            alert('Будь ласка, заповніть всі обов\'язкові поля.');
            return;
        }

        $.ajax({
            url: `/patients/update/${patientId}`,  // Оновлений URL
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(patientData),  // Використовуй об'єкт patientData
            success: function(response) {
                console.log('Пацієнт оновлений успішно');
                location.reload();  // Оновлення сторінки після успішного оновлення
            },
            error: function(xhr, status, error) {
                console.error('Помилка при оновленні пацієнта: ' + error);
            }
        });


    });


    // Функція для відкриття модального вікна редагування
    function openEditModal(patientId) {
        console.log(`Редагування пацієнта з ID: ${patientId}`);

        $.ajax({
            url: `/patients/${patientId}`, // Використовуємо фактичний ID
            type: 'GET',
            success: function(data) {
                // Оновлюємо поля модального вікна з даними пацієнта
                $('#editPatientId').val(data.patientId);
                $('#editPatientName').val(data.patientName);
                $('#editIllName').val(data.patientIll.illName);
                $('#editIllDescription').val(data.patientIll.illDescription);
                $('#editPatientRegistration').val(data.patientIll.patientRegistration);
                $('#editModal').modal('show'); // Відкриваємо модальне вікно
            },
            error: function(error) {
                console.error("Помилка при отриманні даних пацієнта:", error);
            }
        });
    }

    // Функція для видалення пацієнта
    function deletePatient(patientId) {
        console.log(`Видалення пацієнта з ID: ${patientId}`);
        $.ajax({
            url: `/patients/delete/${patientId}`, // Використовуємо фактичний ID
            type: 'DELETE',
            success: function() {
                console.log("Пацієнт успішно видалений");
                location.reload(); // Оновлюємо сторінку після видалення
            },
            error: function(error) {
                // console.error("Помилка при видаленні пацієнта:", error);
                // alert('Помилка при видаленні пацієнта: ' + error.responseText);
                location.reload(); // Оновлюємо сторінку після видалення

            }
        });
    }

    // Обробник для відправки фільтра
    $('#filterForm').submit(function(e) {
        e.preventDefault(); // Запобігаємо стандартній відправці форми

        const formData = $(this).serialize(); // Збір даних з форми

        console.log('Отправка фильтра с даними:', formData); // Логування даних фільтрації

        $.ajax({
            url: '/patients/filter',
            method: 'GET',
            data: formData,
            success: function(response) {
                // Створення тимчасового DOM-елемента для роботи з отриманою сторінкою
                const tempDiv = $('<div>').html(response);

                // Витягуємо нову таблицю пацієнтів
                const newTable = tempDiv.find('#patientsTable');

                // Замінюємо стару таблицю новою
                $('#patientsTable').html(newTable.html());
            },
            error: function(xhr) {
                console.error("Помилка при фільтрації:", xhr);
                alert('Помилка при фільтрації пацієнтів');
            }
        });
    });




    // Функція для форматування дати
    function formatDate(date) {
        if (!date) return ''; // Перевірка на наявність дати
        const parsedDate = new Date(date); // Перетворення рядка дати в об'єкт Date
        if (isNaN(parsedDate)) return ''; // Перевірка на валідність дати
        // Форматуємо дату в форматі YYYY-MM-DD
        return parsedDate.toISOString().split('T')[0];
    }

});

$(document).ready(function() {
    $('#saveNewPatientBtn').on('click', function() {
        // Збираємо дані з форми
        var patientName = $('#createPatientName').val();
        var dateOfBirth = $('#createDob').val();
        var illName = $('#createIllName').val();
        var illDescription = $('#createIllDescription').val();
        var patientRegistration = $('#createPatientRegistration').val();

        // Створюємо URL-параметри для відправки через RequestParam
        var formData = {
            name: patientName,
            dob: dateOfBirth,
            illName: illName,
            illDescription: illDescription,
            regDate: patientRegistration
        };

        // Відправляємо AJAX запит з даними як параметри форми
        $.ajax({
            type: 'POST',
            url: '/patients/create',
            data: $.param(formData), // Використовуємо $.param для перетворення об'єкта в URL-параметри
            success: function(response) {
                console.log('Пацієнт успішно створений', response);
                location.reload(); // Оновлюємо сторінку після успішного створення пацієнта
            },
            error: function(xhr, status, error) {
                console.error('Помилка створення пацієнта: ', error);
            }
        });
    });
});

