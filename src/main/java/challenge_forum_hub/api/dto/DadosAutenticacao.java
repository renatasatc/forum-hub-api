package challenge_forum_hub.api.dto;

import jakarta.validation.constraints.NotBlank;

// Record usado para receber os dados de login na autenticação
public record DadosAutenticacao(

        // Login do usuário — não pode ser nulo nem vazio
        @NotBlank String login,

        // Senha do usuário — não pode ser nula nem vazia
        @NotBlank String senha
) {}
