<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Vacancies List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .vacancy {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 10px;
            border-radius: 5px;
        }
        .vacancy-title {
            font-size: 20px;
            font-weight: bold;
            color: #007BFF;
            text-decoration: none;
        }
        .vacancy-title:hover {
            text-decoration: underline;
        }
        .vacancy-detail {
            margin-top: 5px;
        }
        .vacancy-label {
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>Vacancies List</h1>
<hr>
<#if vacancies?? && (vacancies?size > 0)>
<#list vacancies as vacancy>
<div class="vacancy">
    <a href="${vacancy.alternate_url}" class="vacancy-title">${vacancy.name?replace("<highlighttext>", "")?replace("</highlighttext>", "")}</a><br>
    <div class="vacancy-detail">
        <span class="vacancy-label">Зарплата:</span> <#if vacancy.salary??>${vacancy.salary.from?c} - ${vacancy.salary.to?c} ${vacancy.salary.currency}</#if><#if !vacancy.salary??>Не указана</#if>
</div>
<div class="vacancy-detail">
    <span class="vacancy-label">Опыт работы:</span> <#if vacancy.experience??>${vacancy.experience.name!"Не указан"}</#if><#if !vacancy.experience??>Не указан</#if>
</div>
<div class="vacancy-detail">
    <span class="vacancy-label">Обязанности:</span> <#if vacancy.snippet.responsibility??>${vacancy.snippet.responsibility?replace("<highlighttext>", "")?replace("</highlighttext>", "")!"Не указаны"}</#if><#if !vacancy.snippet.responsibility??>Не указаны</#if>
</div>
</div>
</#list>
<#else>
<p>No vacancies found.</p>
</#if>
<a href="/">Back to Search</a>
</body>
</html>
