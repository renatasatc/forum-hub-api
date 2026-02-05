package challenge_forum_hub.api.repository;

import challenge_forum_hub.api.model.EstadoTopico;
import challenge_forum_hub.api.model.Topico;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Interface de repositório para a entidade Topico
// JpaRepository já fornece métodos prontos como save, findById, findAll, delete etc.
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Busca tópicos filtrando pelo estado (ATIVO ou INATIVO)
    // O Spring Data JPA cria automaticamente a query com base no nome do método
    List<Topico> findByEstado(EstadoTopico estado);

    // Busca tópicos pelo estado permitindo informar a ordenação
    // Exemplo: ordenar por id, dataCriacao etc.
    List<Topico> findByEstado(EstadoTopico estado, Sort sort);
}
