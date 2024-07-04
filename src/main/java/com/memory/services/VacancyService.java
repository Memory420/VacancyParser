package com.memory.services;

import com.memory.models.Vacancy;
import org.springframework.stereotype.Service;

@Service
public class VacancyService {

    public String prettyPrintVacancy(Vacancy vacancy) {
        String requirement = vacancy.getSnippet() != null ? vacancy.getSnippet().getRequirement() : "не указаны";
        String responsibility = vacancy.getSnippet() != null ? vacancy.getSnippet().getResponsibility() : "не указаны";

        requirement = requirement.replaceAll("<highlighttext>", "").replaceAll("</highlighttext>", "");
        responsibility = responsibility.replaceAll("<highlighttext>", "").replaceAll("</highlighttext>", "");

        String salary = (vacancy.getSalary() != null && !vacancy.getSalary().toString().isEmpty()) ? vacancy.getSalary().toString() : "не указана";

        return "Вакансия: " + vacancy.getName() + "\n" +
                "Город: " + vacancy.getArea().getName() + "\n" +
                "Работодатель: " + vacancy.getEmployer().getName() + "\n" +
                "Требования: " + requirement + "\n" +
                "Обязанности: " + responsibility + "\n" +
                "Ссылка: " + vacancy.getUrl() + "\n" +
                "Альтернативная ссылка: " + vacancy.getAlternate_url() + "\n" +
                "Дата создания: " + vacancy.getCreated_at() + "\n" +
                "Дата публикации: " + vacancy.getPublished_at() + "\n" +
                "Зарплата: " + salary + "\n" +
                "График: " + (vacancy.getSchedule() != null ? vacancy.getSchedule().getName() : "не указан") + "\n" +
                "Архивирована: " + (vacancy.isArchived() ? "Да" : "Нет") + "\n" +
                "Премиальная: " + (vacancy.isPremium() ? "Да" : "Нет");
    }
}
