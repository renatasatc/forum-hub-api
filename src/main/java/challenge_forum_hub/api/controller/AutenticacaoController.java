package challenge_forum_hub.api.controller;

import challenge_forum_hub.api.dto.DadosAutenticacao;
import challenge_forum_hub.api.model.Usuario;
import challenge_forum_hub.api.repository.UsuarioRepository;
import challenge_forum_hub.api.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

// Indica que essa classe é um controller REST
@RestController
// Define o caminho base para os endpoints desse controller
@RequestMapping("/auth")
public class AutenticacaoController {

    // Repositório para acessar os dados do usuário no banco
    private final UsuarioRepository repository;

    // Serviço responsável por gerar o token JWT
    private final TokenService tokenService;

    // Codificador de senha usando BCrypt
    private final BCryptPasswordEncoder encoder;

    // Injeção de dependências via construtor
    public AutenticacaoController(
            UsuarioRepository repository,
            TokenService tokenService,
            BCryptPasswordEncoder encoder
    ) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.encoder = encoder;
    }

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid DadosAutenticacao dados) {

        // Busca o usuário pelo login informado
        Usuario usuario = repository.findByLogin(dados.login())
                // Caso não exista, lança erro em tempo de execução
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verifica se a senha enviada bate com a senha criptografada no banco
        if (!encoder.matches(dados.senha(), usuario.getSenha())) {
            // Retorna 403 (Forbidden) se a senha estiver incorreta
            return ResponseEntity.status(403).build();
        }

        // Gera o token JWT para o usuário autenticado
        String token = tokenService.gerarToken(usuario);

        // Retorna o token no corpo da resposta
        return ResponseEntity.ok(token);
    }
}
