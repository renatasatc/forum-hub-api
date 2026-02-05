package challenge_forum_hub.api.security;

import challenge_forum_hub.api.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

// Indica que é um serviço gerenciado pelo Spring
@Service
public class TokenService {

    // Lê a chave secreta do application.properties ou application.yml
    @Value("${jwt.secret}")
    private String segredo;

    // Gera um token JWT para o usuário autenticado
    public String gerarToken(Usuario usuario) {
        return JWT.create()
                // Identificador de quem emitiu o token
                .withIssuer("ForumHub API")

                // Define o "dono" do token (login do usuário)
                .withSubject(usuario.getLogin())

                // Define a data de expiração
                .withExpiresAt(dataExpiracao())

                // Assina o token usando HMAC256 e a chave secreta
                .sign(Algorithm.HMAC256(segredo));
    }

    // Valida o token recebido e retorna o login (subject)
    public String validarToken(String token) {
        return JWT.require(Algorithm.HMAC256(segredo))
                // Garante que o emissor seja o mesmo usado na geração
                .withIssuer("ForumHub API")
                .build()
                // Verifica assinatura e validade
                .verify(token)
                // Retorna o subject (login do usuário)
                .getSubject();
    }

    // Define a data de expiração do token
    private Instant dataExpiracao() {
        return LocalDateTime.now()
                // Token válido por 2 horas
                .plusHours(2)
                // Converte para Instant considerando o fuso horário -03:00
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
