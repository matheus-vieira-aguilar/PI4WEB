package GerenciadorDeAcademiWeb.Controllers;

import ControleDeAcesso.LoginApi.LoginApi;
import GerenciadorDeAcademiWeb.Enums.AutorEnum;
import GerenciadorDeAcademiWeb.Models.Aluno;
import GerenciadorDeAcademiWeb.Models.Dados;
import GerenciadorDeAcademiWeb.Models.MediaPorcentagemGordura;
import GerenciadorDeAcademiWeb.Models.ObterTotalAvaliacoes;
import GerenciadorDeAcademiWeb.Models.PorcentagemDeGordura;
import GerenciadorDeAcademiWeb.Models.ResponseModels.ApiRetorno;
import static java.util.stream.Collectors.toList;
import Helper.GsonHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
            ApiRetorno<List<MediaPorcentagemGordura>> mediaGordura = getMediaGorduras(token);
            ApiRetorno<Dados> dados = getDados(token);

            modelAndView.addObject("media", mediaGordura.getData());
            modelAndView.addObject("dados", dados.getData());
            ApiRetorno<List<ObterTotalAvaliacoes>> avaliacoes = obterTotalAvaliacoes(token);
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

    private ApiRetorno<List<ObterTotalAvaliacoes>> obterTotalAvaliacoes(String token){
        Gson gson = GsonHelper.getGson();
        String urlTotalAvalicoes = "Api/Dados/TotalAvalicoes";

        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(baseUrl + urlTotalAvalicoes).get()
                    .addHeader("Authorization", "Bearer" + token).build();

            Response responseApi = client.newCall(request).execute();
            String retornoJson = responseApi.body().string();
            
            ApiRetorno<List<ObterTotalAvaliacoes>> response = gson.fromJson(retornoJson, new TypeToken<ApiRetorno<List<ObterTotalAvaliacoes>>>() {
            }.getType());

            return response;

        } catch (Exception ex) {
            ApiRetorno<List<ObterTotalAvaliacoes>> retorno = new ApiRetorno<List<ObterTotalAvaliacoes>>();

            List<String> erros = new ArrayList<String>();
            erros.add(ex.getMessage());
            retorno.setErrorMessages(erros);
            retorno.setSucess(false);
            return retorno;
        }
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

    private ApiRetorno<List<MediaPorcentagemGordura>> getMediaGorduras(String token) {
        Gson gson = GsonHelper.getGson();
        String urlDados = "Api/MediaPorcentagem";

        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(baseUrl + urlDados).get()
                    .addHeader("Authorization", "Bearer" + token).build();

            Response responseApi = client.newCall(request).execute();
            String retornoJson = responseApi.body().string();
            
            ApiRetorno<List<MediaPorcentagemGordura>> response = gson.fromJson(retornoJson, new TypeToken<ApiRetorno<List<MediaPorcentagemGordura>>>() {
            }.getType());

            return response;

        } catch (Exception ex) {
            ApiRetorno<List<MediaPorcentagemGordura>> retorno = new ApiRetorno<List<MediaPorcentagemGordura>>();

            List<String> erros = new ArrayList<String>();
            erros.add(ex.getMessage());
            retorno.setErrorMessages(erros);
            retorno.setSucess(false);
            return retorno;
        }
    }

    private ApiRetorno<Dados> getDados(String token) {
        Gson gson = GsonHelper.getGson();
        String urlDados = "Api/Dados";

        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(baseUrl + urlDados).get()
                    .addHeader("Authorization", "Bearer" + token).build();

            Response responseApi = client.newCall(request).execute();
            String retornoJson = responseApi.body().string();
            
            ApiRetorno<Dados> response = gson.fromJson(retornoJson, new TypeToken<ApiRetorno<Dados>>() {
            }.getType());

            return response;

        } catch (Exception ex) {
            ApiRetorno<Dados> retorno = new ApiRetorno<Dados>();

            List<String> erros = new ArrayList<String>();
            erros.add(ex.getMessage());
            retorno.setErrorMessages(erros);
            retorno.setSucess(false);
            return retorno;
        }
    }
}
