package GerenciadorDeAcademiWeb;

import ControleDeAcesso.SecurityConfig;
import GerenciadorDeAcademiWeb.Controllers.HomeController;
import GerenciadorDeAcademiWeb.Controllers.AlunoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {SecurityConfig.class, HomeController.class})
public class GerenciadorDeAcademiWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorDeAcademiWebApplication.class, args);
    }
}
