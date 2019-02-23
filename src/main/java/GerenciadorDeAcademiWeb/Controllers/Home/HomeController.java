package GerenciadorDeAcademiWeb.Controllers.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"/Index"})
    public String home() {
        return "Home/Index";
    }

    @RequestMapping(value = {"/", "home", "/login"})
    public String login() {
        return "Home/Login";
    }
}
