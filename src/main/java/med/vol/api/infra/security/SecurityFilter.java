package med.vol.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// @component é utilizado para que o spring carregue uma classe/componente genérico
@Component
public class SecurityFilter extends OncePerRequestFilter {

//filter chain representa a cadeia de filtros na aplicação, doFilter chama os próximos filtros da aplicação
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        System.out.println(tokenJWT);

        filterChain.doFilter(request,response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) throw new RuntimeException("Token JWT não enviado no cabeçalho Authorization.");

        return authHeader.replace("Bearer", "");
    }
}
