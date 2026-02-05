package challenge_forum_hub.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Indica que esta classe é uma classe de configuração do Spring
@Configuration
public class OpenApiConfig {

    // Define um Bean que será gerenciado pelo Spring
    @Bean
    public OpenAPI customOpenAPI() {

        // Nome do esquema de segurança que será usado na documentação
        final String securitySchemeName = "bearerAuth";

        // Cria a configuração personalizada do OpenAPI (Swagger)
        return new OpenAPI()
                // Adiciona um requisito de segurança global à API
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // Define os componentes reutilizáveis da documentação
                .components(
                        new Components()
                                // Adiciona o esquema de segurança chamado "bearerAuth"
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                // Nome do esquema de segurança
                                                .name(securitySchemeName)
                                                // Tipo HTTP (usado para autenticação via header)
                                                .type(SecurityScheme.Type.HTTP)
                                                // Define que o esquema é do tipo Bearer
                                                .scheme("bearer")
                                                // Especifica que o token utilizado é no formato JWT
                                                .bearerFormat("JWT")
                                )
                );
    }
}
