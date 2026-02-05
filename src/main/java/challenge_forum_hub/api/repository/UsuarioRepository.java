package challenge_forum_hub.api.repository;

import challenge_forum_hub.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repositório da entidade Usuario
// Herdando JpaRepository, já temos CRUD completo
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca um usuário pelo login
    // Retorna Optional para tratar o caso de não existir usuário com esse login
    Optional<Usuario> findByLogin(String login);
}
