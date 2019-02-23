package ControleDeAcesso;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {        
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login as username,senha as password, true as enable from usuario where login=?")
                .passwordEncoder(new ShaPasswordEncoder(256))
                .authoritiesByUsernameQuery("select login as username, \"admin\" as user_roles from usuario where login=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(customizeAuthenticationSuccessHandler)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.exceptionHandling().accessDeniedPage("/403");
    }
}
