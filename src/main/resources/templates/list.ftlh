<!DOCTYPE html>
<html>
<head>
    <title>Вакансии</title>
</head>
<body>
<h1>Результаты поиска вакансий</h1>
<hr>
<#if error??>
<div>
    <p>${error}</p>
</div>
</#if>
<#if vacancies??>
<#list vacancies as vacancy>
<div>
    <p><b>${vacancy.name}</b></p>
    <p>Город: ${vacancy.area.name}</p>
    <p>Работодатель: ${vacancy.employer.name}</p>
    <p>Требования: ${vacancy.snippet.requirement?replace("<highlighttext>", "")?replace("</highlighttext>", "")}</p>
    <p>Обязанности: ${vacancy.snippet.responsibility?replace("<highlighttext>", "")?replace("</highlighttext>", "")}</p>
    <p>Ссылка: <a href="${vacancy.url}">${vacancy.url}</a></p>
    <p>Альтернативная ссылка: <a href="${vacancy.alternate_url}">${vacancy.alternate_url}</a></p>
    <p>Дата создания: ${vacancy.created_at}</p>
    <p>Дата публикации: ${vacancy.published_at}</p>
    <p>Зарплата: <#if vacancy.salary?? && vacancy.salary?has_content>${vacancy.salary}<#else>не указана</#if></p>
    <p>График: <#if vacancy.schedule?? && vacancy.schedule.name?has_content>${vacancy.schedule.name}<#else>не указан</#if></p>
    <p>Архивирована: ${vacancy.archived?string('Да', 'Нет')}</p>
    <p>Премиальная: ${vacancy.premium?string('Да', 'Нет')}</p>
    <hr>
</div>
</#list>
<#else>
<p>Вакансии не найдены.</p>
</#if>
</body>
</html>
