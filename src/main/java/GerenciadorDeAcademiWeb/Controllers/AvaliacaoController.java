package GerenciadorDeAcademiWeb.Controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ControleDeAcesso.LoginApi.LoginApi;
import GerenciadorDeAcademiWeb.Models.Aluno;
import GerenciadorDeAcademiWeb.Models.Avaliacao;
import GerenciadorDeAcademiWeb.Models.AvaliacaoDTO;
import GerenciadorDeAcademiWeb.Models.AvaliacaoRequest;
import GerenciadorDeAcademiWeb.Models.ResponseModels.ApiRetorno;
import Helper.GsonHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AvaliacaoController {
    
    @Value("${api.baseUrl}")
    private String baseUrl;

    @RequestMapping(value = {"/avaliacao/{idAluno}"})
    public ModelAndView avaliacao(@Valid @PathVariable("idAluno") UUID idAluno, @RequestParam int avaliacao,  RedirectAttributes redirectAttributes) {
        Gson gson = GsonHelper.getGson();
        ModelAndView modelAndView = new ModelAndView("Avaliacao/Avaliacao");  
        LoginApi loginApi = new LoginApi();
        String urlAluno = "Api/Avaliacao";

        try {
            String token = loginApi.logar();
            
            OkHttpClient client = new OkHttpClient();
            
            Request request = new Request.Builder()
                    .url(baseUrl + urlAluno +"?id="+ idAluno)
                    .get()
                    .addHeader("Authorization", "Bearer" + token)
                    .build();
            
            Response responseApi = client.newCall(request).execute();
            String retornoJson = responseApi.body().string();

            responseApi.body().close();       

            ApiRetorno<List<Aluno>> alunosApi = getAlunos(token, idAluno);
            ApiRetorno<List<AvaliacaoDTO>> avaliacoes = gson.fromJson(retornoJson, new TypeToken<ApiRetorno<List<AvaliacaoDTO>>>(){}.getType());
            
            avaliacoes.setData(avaliacoes
                                    .getData()
                                    .stream()
                                    .filter(x -> x.getAvaliacao() >= avaliacao)
                                    .map(x -> x)
                                    .sorted(Comparator.comparing(AvaliacaoDTO::getAvaliacao))
                                    .collect(toList()));
            
            modelAndView.addObject("avaliacoes", avaliacoes.getData());
            modelAndView.addObject("aluno", alunosApi.getData().stream().findFirst().get());

            return modelAndView;


        } catch (Exception ex) {

            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("Aluno/ListAluno").addObject("error", true);
        }
    }    
    
    @RequestMapping(value = {"/avaliar/{id}"})
    public ModelAndView avaliar(@Valid @PathVariable("id") UUID idAluno) throws Exception {
        ModelAndView modelAndView = new ModelAndView("Avaliacao/FormAvaliacao");
        LoginApi loginApi = new LoginApi();

        String token = loginApi.logar();

        ApiRetorno<List<Aluno>> alunosApi = getAlunos(token, idAluno);
        Aluno aluno = alunosApi.getData().stream().findFirst().get();
        
        Period periodo = Period.between(aluno.getDataNascimento(), LocalDate.now());
        int idade = periodo.getYears();    
        AvaliacaoRequest avaliacao = new AvaliacaoRequest();    
        avaliacao.setIdAluno(aluno.getId().toString());
        avaliacao.setIdade(idade);
        avaliacao.setSexo(aluno.getSexo());

        modelAndView.addObject("aluno", aluno);
        modelAndView.addObject("avaliacao", avaliacao);
        return modelAndView;             
    }

    @RequestMapping(value = {"/salvar-avaliacao"}, method = RequestMethod.POST)
    public ModelAndView salvarAvaliacao(@Valid @ModelAttribute("avaliacao") AvaliacaoRequest avaliacao, RedirectAttributes redirectAttributes) throws Exception {
        Gson gson = GsonHelper.getGson();
        LoginApi loginApi = new LoginApi();

        try {
            String token = loginApi.logar();            
            
            String urlAvaliacao = "Api/Avaliacao";

            OkHttpClient client = new OkHttpClient();

            MediaType media = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(media, gson.toJson(avaliacao));
            
            Request request = new Request.Builder()
                    .url(baseUrl + urlAvaliacao)
                    .post(body)
                    .addHeader("Authorization", "Bearer" + token)
                    .build();
        
            Response responseApi = client.newCall(request).execute();

            if (!responseApi.isSuccessful()) {
                
                redirectAttributes.addFlashAttribute("error", true);
                return new ModelAndView("redirect:/avaliar/" + avaliacao.getIdAluno());
            }

            redirectAttributes.addFlashAttribute("success", true);
            return new ModelAndView("/avaliacao/" + avaliacao.getIdAluno() + "?avaliacao=1");
        } catch (Exception ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("/avaliar/" + avaliacao.getIdAluno()).addObject("error", true);
        }                    
    }

    private ApiRetorno<List<Aluno>> getAlunos(String token, UUID id) {
        Gson gson = GsonHelper.getGson();
        String urlAluno = "Api/Aluno/";

        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(baseUrl + urlAluno + "?id=" + id)
                    .get()
                    .addHeader("Authorization", "Bearer" + token)
                    .build();

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
}
