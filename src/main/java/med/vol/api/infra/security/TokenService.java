package med.vol.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.vol.api.domain.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    /* o value tem que ser do sprint, e dentro dos parênteses eu passo o nome da variavel que criamos no
    application.properties  -- usamos o ${} para dizer para o spring ler uma variavel de ambiente e usamos
    o : para ser o default caso não encontre a variavel de ambiente */
    @Value("api.securitu.token.secret")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            //algoritimo para gerar a assinatura digital do token
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    //api responsavel pela geração do token - dona
                    .withIssuer("API Voll.med")
                    //dono do token
                    .withSubject(usuario.getLogin())
                    //data de validade do token
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        }

    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }


    private Instant dataExpiracao() {
        //horário atual + duas horas, converter para instant com o fuso horário do Brasil
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
