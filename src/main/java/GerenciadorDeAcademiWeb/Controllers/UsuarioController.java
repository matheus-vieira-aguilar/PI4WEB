package GerenciadorDeAcademiWeb.Controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ControleDeAcesso.LoginApi.LoginApi;
import GerenciadorDeAcademiWeb.Models.ResponseModels.ApiRetorno;
import Helper.GsonHelper;
import groovyjarjarantlr.collections.List;

@Controller
public class UsuarioController {

    @Value("${api.baseUrl}")
    private String baseUrl;

    @RequestMapping(value = { "/Usuario" })
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("Usuario/Usuario");
        LoginApi loginApi = new LoginApi();

        try {
            String token = loginApi.logar();

            if (token == null) {
                return new ModelAndView("redirect:/logout");
            }

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();

            modelAndView.addObject("username", username);
            return modelAndView;
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("redirect:/logout");
        }

    }

    @RequestMapping(value = {"alterar-senha"}, method = RequestMethod.GET)
    public ModelAndView ativarAluno(@RequestParam("senha") String novaSenha, RedirectAttributes redirectAttributes) {
        Gson gson = GsonHelper.getGson();
        ModelAndView modelAndView = new ModelAndView("redirect:/logout");
        LoginApi loginApi = new LoginApi();
        String urlUsuario = "Api/Usuario";

        try {
            String token = loginApi.logar();

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();

            if (token == null) {
                return new ModelAndView("redirect:/logout");
            }

            MediaType media = MediaType.parse("application/json");

            RequestBody body = RequestBody.create(media, gson.toJson(""));
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(baseUrl + urlUsuario)
                    .patch(body)
                    .addHeader("Authorization", "Bearer" + token)
                    .addHeader("novaSenha", novaSenha)
                    .addHeader("usuario", username)
                    .build();

            Response responseApi = client.newCall(request).execute();

            return modelAndView.addObject("mensagem", "Senha atualizada com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView("redirect:/Usuario");
        }
    }
}