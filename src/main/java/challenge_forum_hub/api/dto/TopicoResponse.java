package challenge_forum_hub.api.dto;

import challenge_forum_hub.api.model.EstadoTopico;
import challenge_forum_hub.api.model.Topico;

import java.time.LocalDateTime;

/**
 * DTO de saída (usado nos GETs)
 * Representa os dados que serão devolvidos para o cliente
 */
public record TopicoResponse(

        // Identificador único do tópico
        Long id,

        // Título do tópico
        String titulo,

        // Conteúdo da mensagem do tópico
        String mensagem,

        // Autor do tópico
        String autor,

        // Curso relacionado ao tópico
        String curso,

        // Estado atual do tópico (ATIVO ou INATIVO)
        EstadoTopico estado,

        // Data e hora de criação do tópico
        LocalDateTime dataCriacao
) {

    // Construtor que converte uma entidade Topico em DTO
    public TopicoResponse(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getEstado(),
                topico.getDataCriacao()
        );
    }
}
