package GerenciadorDeAcademiWeb.Controllers;

import ControleDeAcesso.LoginApi.LoginApi;
import GerenciadorDeAcademiWeb.Models.Aluno;
import GerenciadorDeAcademiWeb.Models.Dados;
import GerenciadorDeAcademiWeb.Models.ResponseModels.ApiRetorno;
import Helper.GsonHelper;
import Helper.IndexHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Value("${api.baseUrl}")
    private String baseUrl;

    @RequestMapping(value = { "/Index" })
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("Home/Index");
        LoginApi loginApi = new LoginApi();

        try {
            String token = loginApi.logar();

            if (token == null) {
                return new ModelAndView("redirect:/logout");
            }

            ApiRetorno<List<Aluno>> alunosApi = getAlunos(token);
            IndexHelper helper = new IndexHelper();
            Dados dados = helper.coletaDados(alunosApi.getData());
            modelAndView.addObject("dados", dados);
            modelAndView.addObject("alunos", alunosApi.getData());
            return modelAndView;
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("redirect:/logout");
        }

    }

    @RequestMapping(value = { "/", "home", "/login" })
    public String login() {
        return "Home/Login";
    }

    private ApiRetorno<List<Aluno>> getAlunos(String token) {
        Gson gson = GsonHelper.getGson();
        String urlAluno = "Api/Aluno";

        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(baseUrl + urlAluno).get()
                    .addHeader("Authorization", "Bearer" + token).build();

            Response responseApi = client.newCall(request).execute();
            String retornoJson = responseApi.body().string();
            System.out.println(responseApi.code());
            ApiRetorno<List<Aluno>> response = gson.fromJson(retornoJson, new TypeToken<ApiRetorno<List<Aluno>>>() {
            }.getType());
            
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
