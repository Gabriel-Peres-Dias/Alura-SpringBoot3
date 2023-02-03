package med.vol.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//Classe de configuração do spring security
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    //csrf = cross-site request forgery = como vamos usar tokens não precisamos dele pq ele o token já nos protege de ataques
    // SessionCreationPolicy.STATELESS = desabilitamos o processo de autenticação do spring do formulario, e como é api rest e rest é stateless
    //@bean = devolve um objeto para o spring ou injetar em algum lugar
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();
    }

}
