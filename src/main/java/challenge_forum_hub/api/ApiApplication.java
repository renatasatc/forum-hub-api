package challenge_forum_hub.api;

import challenge_forum_hub.api.model.Usuario;
import challenge_forum_hub.api.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Anotação principal do Spring Boot
// Marca como aplicação Spring Boot e habilita auto-configuração e scan de componentes
@SpringBootApplication
public class ApiApplication {

    // Método principal que inicia a aplicação
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    // Bean executado automaticamente após a aplicação iniciar
    // Usado aqui para inserir um usuário padrão no banco
    @Bean
    CommandLineRunner init(UsuarioRepository repo) {
        return args -> {

            // Verifica se já existe um usuário com login "admin"
            if (repo.findByLogin("admin").isEmpty()) {

                // Cria um encoder para criptografar a senha
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                // Salva um usuário padrão no banco
                repo.save(new Usuario(
                        "admin",
                        // Senha criptografada antes de salvar
                        encoder.encode("123456")
                ));
            }
        };
    }
}
