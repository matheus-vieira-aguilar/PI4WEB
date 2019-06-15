package GerenciadorDeAcademiWeb.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ControleDeAcesso.LoginApi.LoginApi;
import GerenciadorDeAcademiWeb.Models.Aluno;
import GerenciadorDeAcademiWeb.Models.Avaliacao;
import GerenciadorDeAcademiWeb.Models.ResponseModels.ApiRetorno;
import Helper.GsonHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.squareup.okhttp.RequestBody;
import java.time.OffsetDateTime;
import okio.ByteString;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AvaliacaoController {
    
    @Value("${api.baseUrl}")
    private String baseUrl;

    @RequestMapping(value = {"/avaliacao/{idAluno}"})
    public ModelAndView avaliacao(@Valid @PathVariable("idAluno") UUID idAluno,  RedirectAttributes redirectAttributes) {
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
            if (responseApi.isSuccessful()) {
                System.out.println(responseApi.code());
            }

            ApiRetorno<List<Aluno>> alunosApi = getAlunos(token, idAluno);
            System.out.println(alunosApi.getData().get(0));
            
            modelAndView.addObject("avaliacoes", retornoJson);
            modelAndView.addObject("aluno", alunosApi.getData().get(0));

            return modelAndView;


        } catch (Exception ex) {

            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("Aluno/ListAluno").addObject("error", true);
        }
    }    
    
    @RequestMapping(value = {"/avaliar/{id}"})
    public ModelAndView avaliar(@Valid @PathVariable("idAluno") UUID idAluno){                        
        ModelAndView modelAndView = new ModelAndView("Avaliacao/Avaliacao");
        modelAndView.addObject("idAluno", idAluno);
        return modelAndView;             
    }
    
    private List<Avaliacao> GetAvaliacoes(UUID idAluno){
        return new ArrayList<Avaliacao>();
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
