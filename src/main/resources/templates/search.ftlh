<!DOCTYPE html>
<html>
<head>
    <title>Поиск вакансий</title>
    <script>
        function validateForm(event) {
            const jobTitle = document.getElementById('jobTitle').value;
            const city = document.getElementById('city').value;
            const perPage = document.getElementById('perPage').value;

            if (!jobTitle || !city || perPage <= 0) {
                alert('Пожалуйста, заполните все поля и введите положительное число для количества вакансий на странице.');
                event.preventDefault(); // Отменяем отправку формы
            }
        }

        document.addEventListener('DOMContentLoaded', function() {
            const form = document.querySelector('form');
            form.addEventListener('submit', validateForm);
        });
    </script>
</head>
<body>
<h1>Поиск вакансий</h1>
<form action="/search" method="get">
    <label for="jobTitle">Должность:</label>
    <input type="text" id="jobTitle" name="jobTitle" required>
    <br><br>
    <label for="city">Город (ID):</label>
    <input type="text" id="city" name="city" required>
    <br><br>
    <label for="perPage">Количество вакансий на странице:</label>
    <input type="number" id="perPage" name="perPage" min="1" required>
    <br><br>
    <button type="submit">Поиск</button>
</form>
</body>
</html>
