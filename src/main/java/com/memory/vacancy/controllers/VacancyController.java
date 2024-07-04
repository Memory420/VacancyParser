package com.memory.vacancy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memory.models.VacanciesResponse;
import com.memory.models.Vacancy;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VacancyController {

    @GetMapping("/")
    public String searchForm(Model model) {
        model.addAttribute("vacancies", null);
        return "search";
    }

    @GetMapping("/vacancies")
    public String getVacancies(@RequestParam(name = "jobTitle", required = false) String jobTitle,
                               @RequestParam(name = "city", required = false) String city,
                               @RequestParam(name = "perPage", required = false) String perPage,
                               @RequestParam(name = "salary", required = false) String salary,
                               @RequestParam(name = "experience", required = false) String experience,
                               Model model) throws Exception {

        // Логи
        System.out.println("Received request for vacancies with params:");
        System.out.println("Job Title: " + jobTitle);
        System.out.println("City: " + city);
        System.out.println("Per Page: " + perPage);
        System.out.println("Salary: " + salary);
        System.out.println("Experience: " + experience);

        // Проверка на наличие обязательных параметров
        if ((jobTitle == null || jobTitle.isEmpty()) && (city == null || city.isEmpty())) {
            model.addAttribute("error", "Please provide at least Job Title or City.");
            return "search";
        }

        String apiUrl = "https://api.hh.ru/vacancies";
        Map<String, String> searchParams = new HashMap<>();
        if (jobTitle != null && !jobTitle.isEmpty()) {
            searchParams.put("text", jobTitle);
        }
        if (city != null && !city.isEmpty()) {
            searchParams.put("area", city);
        }
        if (perPage != null && !perPage.isEmpty()) {
            searchParams.put("per_page", perPage);
        }
        searchParams.put("page", "0");

        if (salary != null && !salary.isEmpty()) {
            searchParams.put("salary", salary);
        }

        if (experience != null && !experience.isEmpty()) {
            searchParams.put("experience", experience);
        }

        String searchUrl = buildSearchUrl(apiUrl, searchParams);

        HttpResponse<JsonNode> response = Unirest.get(searchUrl).asJson();

        // Логи
        System.out.println("Request URL: " + searchUrl);
        System.out.println("Response Status: " + response.getStatus());

        if (response.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = response.getBody().toString();

            // Логи
            System.out.println("Response JSON: " + jsonResponse);

            // Преобразование JSON-ответа в список объектов Vacancy
            VacanciesResponse vacanciesResponse = mapper.readValue(jsonResponse, VacanciesResponse.class);
            List<Vacancy> vacancies = vacanciesResponse.getItems();

            // Логи
            System.out.println("Vacancies found: " + vacancies.size());

            model.addAttribute("vacancies", vacancies);
        } else {
            model.addAttribute("error", "Failed to fetch data: " + response.getStatusText());
            model.addAttribute("vacancies", null);
        }

        return "list";
    }

    private String buildSearchUrl(String apiUrl, Map<String, String> params) {
        StringBuilder url = new StringBuilder(apiUrl);
        if (!params.isEmpty()) {
            url.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                url.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            url.setLength(url.length() - 1);
        }
        return url.toString();
    }
}
