package challenge_forum_hub.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Indica que essa classe é uma entidade JPA (tabela no banco)
@Entity
// Define o nome da tabela no banco de dados
@Table(name = "topicos")
public class Topico {

    // Chave primária da tabela
    @Id
    // Valor gerado automaticamente pelo banco (auto incremento)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Título do tópico — não pode ser nulo e deve ser único
    @Column(nullable = false, unique = true)
    private String titulo;

    // Mensagem/conteúdo do tópico — obrigatório
    @Column(nullable = false)
    private String mensagem;

    // Autor do tópico — obrigatório
    @Column(nullable = false)
    private String autor;

    // Curso relacionado ao tópico — obrigatório
    @Column(nullable = false)
    private String curso;

    // Estado do tópico (ATIVO ou INATIVO), salvo como texto no banco
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoTopico estado;

    // Data e hora em que o tópico foi criado
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    // Método executado automaticamente antes de persistir no banco
    @PrePersist
    public void prePersist() {
        // Define a data de criação no momento do salvamento
        this.dataCriacao = LocalDateTime.now();

        // Define o estado padrão como ATIVO ao criar um novo tópico
        this.estado = EstadoTopico.ATIVO;
    }

    // Getters e setters

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }

    public EstadoTopico getEstado() { return estado; }
    public void setEstado(EstadoTopico estado) { this.estado = estado; }
}
