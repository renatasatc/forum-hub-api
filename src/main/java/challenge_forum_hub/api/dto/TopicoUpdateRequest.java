package challenge_forum_hub.api.dto;

import jakarta.validation.constraints.NotBlank;

// DTO usado para atualizar os dados de um tópico existente
public class TopicoUpdateRequest {

    // Novo título do tópico — obrigatório
    @NotBlank
    private String titulo;

    // Nova mensagem do tópico — obrigatória
    @NotBlank
    private String mensagem;

    // Novo curso relacionado ao tópico — obrigatório
    @NotBlank
    private String curso;

    // Getter do título
    public String getTitulo() { return titulo; }

    // Getter da mensagem
    public String getMensagem() { return mensagem; }

    // Getter do curso
    public String getCurso() { return curso; }
}
