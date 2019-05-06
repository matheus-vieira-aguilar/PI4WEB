package GerenciadorDeAcademiWeb.Controllers.Home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ControleDeAcesso.LoginApi.LoginApi;
import GerenciadorDeAcademiWeb.Models.Aluno;
import GerenciadorDeAcademiWeb.Models.ResponseModels.ApiRetorno;
import Helper.GsonHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


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

            ApiRetorno<List<Aluno>> alunosApi = getAlunos(token);

            modelAndView.addObject("alunos", alunosApi.getData());

            return modelAndView;
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("Home/Index");
        }
    }

    @RequestMapping(value = {"/", "home", "/login"})
    public String login() {
        return "Home/Login";
    }

    private ApiRetorno<List<Aluno>> getAlunos(String token) {
        Gson gson = GsonHelper.getGson();
        String urlAluno = "Api/Aluno";
        
        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(baseUrl + urlAluno)
                    .get()
                    .addHeader("Authorization", "Bearer" + token + "asd")
                    .build();

            Response responseApi = client.newCall(request).execute();
            String retornoJson = responseApi.body().string();

            ApiRetorno<List<Aluno>> response = gson.fromJson(retornoJson, new TypeToken<ApiRetorno<List<Aluno>>>(){}.getType());
            return response;
        } catch (Exception ex) {
            ApiRetorno<List<Aluno>> retorno = new ApiRetorno<List<Aluno>>();
            
            List<String> erros = new ArrayList<String>();
            erros.add(ex.getMessage());
            retorno.setErrorMessages(erros);
            retorno.setSucess(false);            
            return retorno;
        }
    }   
}