package com.memory.vacancy.controllers;

import com.memory.models.Vacancy;
import com.memory.models.VacanciesResponse;
import com.memory.services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class VacancyController {

    private static final Logger logger = Logger.getLogger(VacancyController.class.getName());

    @Autowired
    private VacancyService vacancyService;

    @GetMapping("/")
    public String showSearchForm() {
        return "search"; // Шаблон с формой поиска
    }

    @GetMapping("/search")
    public String searchVacancies(
            @RequestParam("jobTitle") String jobTitle,
            @RequestParam("city") String city,
            @RequestParam("perPage") String perPage,
            Model model) {

        logger.log(Level.INFO, "Параметры поиска: jobTitle={0}, city={1}, perPage={2}", new Object[]{jobTitle, city, perPage});

        try {
            String apiUrl = "https://api.hh.ru/vacancies";
            Map<String, String> searchParams = new HashMap<>();
            searchParams.put("text", jobTitle);
            searchParams.put("area", city);
            searchParams.put("per_page", perPage);
            searchParams.put("page", "0");

            String searchUrl = buildSearchUrl(apiUrl, searchParams);
            logger.log(Level.INFO, "URL для запроса: {0}", searchUrl);

            HttpResponse<JsonNode> response = Unirest.get(searchUrl).asJson();
            logger.log(Level.INFO, "Статус ответа: {0}", response.getStatus());

            if (response.getStatus() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonResponse = response.getBody().toString();
                VacanciesResponse vacanciesResponse = mapper.readValue(jsonResponse, VacanciesResponse.class);
                List<Vacancy> vacancies = vacanciesResponse.getItems();

                model.addAttribute("vacancies", vacancies);
            } else {
                model.addAttribute("error", "Failed to fetch data: " + response.getStatusText());
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred during searchVacancies", e);
            model.addAttribute("error", "An error occurred while processing your request.");
        }

        logger.log(Level.INFO, "Returning vacancy-list template");
        return "list"; // Убедись, что этот шаблон существует
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
