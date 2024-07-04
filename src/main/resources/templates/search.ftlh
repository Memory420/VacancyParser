<!DOCTYPE html>
<html>
<head>
    <title>Search Vacancies</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        function validateForm() {
            const jobTitle = document.getElementById('jobTitle').value.trim();
            const city = document.getElementById('city').value.trim();
            if (jobTitle === '' && city === '') {
                alert('Please enter either Job Title or City.');
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<h1>Search Vacancies</h1><hr>
<form action="/vacancies" method="get" onsubmit="return validateForm()">
    <label for="jobTitle">Job Title:</label>
    <input type="text" id="jobTitle" name="jobTitle">
    <br>
    <label for="city">City:</label>
    <input type="number" id="city" name="city">
    <br>
    <label for="perPage">Number of Vacancies:</label>
    <input type="number" id="perPage" name="perPage">
    <br>

    <label for="salary">Salary:</label>
    <select id="salary" name="salary">
        <option value="">Не имеет значения</option>
        <option value="5000">от 5 000 ₽</option>
        <option value="65000">от 65 000 ₽</option>
        <option value="125000">от 125 000 ₽</option>
        <option value="185000">от 185 000 ₽</option>
        <option value="245000">от 245 000 ₽</option>
        <option value="305000">от 305 000 ₽</option>
    </select>
    <br>

    <label for="experience">Experience:</label>
    <select id="experience" name="experience">
        <option value="">Не имеет значения</option>
        <option value="noExperience">Нет опыта</option>
        <option value="between1And3">От 1 года до 3 лет</option>
        <option value="between3And6">От 3 до 6 лет</option>
        <option value="moreThan6">Более 6 лет</option>
    </select>
    <br>

    <button type="submit">Find</button>
</form>
</body>
</html>
