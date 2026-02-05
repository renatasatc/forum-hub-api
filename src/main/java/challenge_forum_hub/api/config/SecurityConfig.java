package challenge_forum_hub.api.config;

import challenge_forum_hub.api.repository.UsuarioRepository;
import challenge_forum_hub.api.security.AutenticacaoFiltro;
import challenge_forum_hub.api.security.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Classe de configuração de segurança do Spring
@Configuration
public class SecurityConfig {

    // Define a cadeia de filtros de segurança da aplicação
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            TokenService tokenService,
            UsuarioRepository usuarioRepository
    ) throws Exception {

        return http
                // Desabilita proteção CSRF (comum em APIs stateless)
                .csrf(csrf -> csrf.disable())

                // Desabilita gerenciamento de sessão (API usa JWT, não sessão)
                .sessionManagement(sm -> sm.disable())

                // Define regras de autorização das requisições
                .authorizeHttpRequests(auth -> auth
                        // Endpoint de login é público
                        .requestMatchers("/auth/login").permitAll()

                        // Endpoints da documentação Swagger são públicos
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**"
                        ).permitAll()

                        // Qualquer outra requisição exige autenticação
                        .anyRequest().authenticated()
                )

                // Adiciona um filtro customizado antes do filtro padrão de autenticação
                .addFilterBefore(
                        // Filtro responsável por validar o token JWT em cada requisição
                        new AutenticacaoFiltro(tokenService, usuarioRepository),
                        // Filtro padrão do Spring que processa login com usuário e senha
                        UsernamePasswordAuthenticationFilter.class
                )

                // Constrói a configuração de segurança
                .build();
    }

    // Expõe o AuthenticationManager como Bean para ser usado no processo de autenticação
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    // Bean responsável por criptografar senhas usando o algoritmo BCrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
