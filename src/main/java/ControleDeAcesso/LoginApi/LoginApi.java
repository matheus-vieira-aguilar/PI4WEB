package ControleDeAcesso.LoginApi;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoginApi {

    private static String baseUrl;

    private static String clientLogin;
    private static String clientPassword;

    @Value("${api.baseUrl}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Value("${api.login}")
    public void setClientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
    }

    @Value("${api.senha}")
    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public String logar() throws Exception {
        UserApi userApi = new UserApi();
        Gson gson = new Gson();

        String url = "oauth/token";

        String loginSenha = clientLogin + ":" + clientPassword;
        String tokenAuthorization = Base64.getEncoder().encodeToString(loginSenha.getBytes());

        try {   
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, userApi.toString());
            Request request = new Request.Builder()
              .url(baseUrl + url)
              .post(body)
              .addHeader("Authorization", "Basic " + tokenAuthorization)
              .build();
            System.out.println(baseUrl + url);
            Response response = client.newCall(request).execute();
            
            ResponseToken responseToken = gson.fromJson(response.body().string(), ResponseToken.class);

            if(responseToken.getErro() != null){
                return null;
            }

            return responseToken.getAcessToken();
        } catch (Exception ex) {
           throw new Exception(ex.getMessage());
        }
    }

    public class ResponseToken {

        private String access_token;

        private String error;

        public String getAcessToken(){
            return access_token;
        }

        public String getErro(){
            return error;
        }
    }

    public class UserApi {

        public UserApi() {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            grantType = "password";
            password = userDetails.getPassword();
            username = userDetails.getUsername();
        }

        private final String grantType;
        private final String password;
        private final String username;

        public String getGrantType() {
            return grantType;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        @Override
        public String toString() {
            return String.format("grant_type=%s&username=%s&password=%s", grantType, username, password);
        }
    }
}
