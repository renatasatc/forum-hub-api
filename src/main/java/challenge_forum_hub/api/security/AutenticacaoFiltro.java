package challenge_forum_hub.api.security;

import challenge_forum_hub.api.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Filtro que executa uma vez por requisição
// Responsável por interceptar as requisições e validar o token JWT
public class AutenticacaoFiltro extends OncePerRequestFilter {

    // Serviço responsável por gerar e validar o token
    private final TokenService tokenService;

    // Repositório para buscar o usuário no banco
    private final UsuarioRepository usuarioRepository;

    // Construtor com injeção das dependências
    public AutenticacaoFiltro(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Recupera o token do header Authorization
        String token = recuperarToken(request);

        // Se existir token na requisição
        if (token != null) {

            // Valida o token e extrai o login do usuário
            String login = tokenService.validarToken(token);

            // Busca o usuário no banco com base no login extraído do token
            usuarioRepository.findByLogin(login).ifPresent(usuario -> {

                // Cria um objeto de autenticação do Spring Security
                var auth = new UsernamePasswordAuthenticationToken(
                        usuario, // principal (usuário autenticado)
                        null,    // credenciais (não usamos aqui)
                        null     // authorities/permissões (não implementadas)
                );

                // Define o usuário como autenticado no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(auth);
            });
        }

        // Continua a cadeia de filtros
        filterChain.doFilter(request, response);
    }

    // Método auxiliar para extrair o token do header
    private String recuperarToken(HttpServletRequest request) {

        // Lê o header Authorization
        String header = request.getHeader("Authorization");

        // Se não existir header, não há token
        if (header == null) return null;

        // Remove o prefixo "Bearer " e retorna só o token
        return header.replace("Bearer ", "");
    }
}
