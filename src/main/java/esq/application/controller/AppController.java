package esq.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер интерфейса приложения
 */

@Controller
public class AppController {

    @RequestMapping(value = "/")
    public String getHomePage() {
        return "master";
    }

    @RequestMapping(value = "/survey/list")
    public String surveyList(Model model) {
        model.addAttribute("name", "Survey list page");
        return "master";
    }

}
