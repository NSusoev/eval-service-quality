package esq.application.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер интерфейса приложения
 */

@Controller
@RequestMapping(value = "/", produces = "text/html")
public class AppController {

}
