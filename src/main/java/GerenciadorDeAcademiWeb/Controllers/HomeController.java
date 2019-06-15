package GerenciadorDeAcademiWeb.Controllers;

import ControleDeAcesso.LoginApi.LoginApi;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Value("${api.baseUrl}")
    private String baseUrl;

    @RequestMapping(value = {"/Index"})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("Home/Index");
        LoginApi loginApi = new LoginApi();

        try {
            String token = loginApi.logar();

            if (token == null) {
                return new ModelAndView("redirect:/logout");
            }

            return modelAndView;

        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("redirect:/logout");
        }

    }

    @RequestMapping(value = {"/", "home", "/login"})
    public String login() {
        return "Home/Login";
    }
}
