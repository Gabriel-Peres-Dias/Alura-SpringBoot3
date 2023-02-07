package med.vol.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.vol.api.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario) {
        try {
            //algoritimo para gerar a assinatura digital do token
            var algoritmo = Algorithm.HMAC256("12345678");
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

    private Instant dataExpiracao() {
        //horário atual + duas horas, converter para instant com o fuso horário do Brasil
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
