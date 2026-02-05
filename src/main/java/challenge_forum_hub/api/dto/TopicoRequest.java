package challenge_forum_hub.api.dto;

import jakarta.validation.constraints.NotBlank;

// DTO usado para receber os dados na criação de um novo tópico
public class TopicoRequest {

    // Título do tópico — obrigatório
    @NotBlank
    private String titulo;

    // Mensagem/conteúdo do tópico — obrigatório
    @NotBlank
    private String mensagem;

    // Nome do autor do tópico — obrigatório
    @NotBlank
    private String autor;

    // Curso relacionado ao tópico — obrigatório
    @NotBlank
    private String curso;

    // Getter do título
    public String getTitulo() { return titulo; }

    // Getter da mensagem
    public String getMensagem() { return mensagem; }

    // Getter do autor
    public String getAutor() { return autor; }

    // Getter do curso
    public String getCurso() { return curso; }
}
