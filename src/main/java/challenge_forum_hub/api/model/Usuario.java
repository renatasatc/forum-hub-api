package challenge_forum_hub.api.model;

import jakarta.persistence.*;

// Indica que a classe é uma entidade do banco
@Entity
// Define o nome da tabela
@Table(name = "usuarios")
public class Usuario {

    // Chave primária
    @Id
    // ID gerado automaticamente pelo banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Login do usuário — deve ser único e não pode ser nulo
    @Column(unique = true, nullable = false)
    private String login;

    // Senha do usuário (armazenada criptografada) — obrigatória
    @Column(nullable = false)
    private String senha;

    // Construtor vazio exigido pelo JPA
    public Usuario() {}

    // Construtor para facilitar a criação de novos usuários
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    // Getters

    public Long getId() { return id; }

    public String getLogin() { return login; }

    public String getSenha() { return senha; }
}
