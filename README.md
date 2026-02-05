# Forum Hub API 💬

[![Java](https://img.shields.io/badge/Java-17-blue?logo=openjdk)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql)](https://www.postgresql.org/)
[![JWT](https://img.shields.io/badge/Auth-JWT-orange)](https://jwt.io/)







O Forum **Hub API** é uma API REST desenvolvida em Java + Spring Boot que permite o gerenciamento de tópicos de um fórum com autenticação via JWT.

A API possibilita criar, listar, atualizar, ativar/inativar e remover tópicos, além de possuir um sistema de login com geração de token para proteger os endpoints.

---

## 🚀 Funcionalidades

🔐 Autenticação de usuários com JWT  
👤 Cadastro e busca de usuários  
💬 Criação, listagem e gerenciamento de tópicos  
📌 Filtro de tópicos por estado (ex: ATIVO, INATIVO)  
🗄 Persistência em banco PostgreSQL  
🛡 Proteção de rotas com Spring Security  

---
## 🧠 Tecnologias Utilizadas

- **Java 17**

- **Spring Boot**

- **Spring Security**

- **Spring Data JPA**

- **JWT (JSON Web Token)**

- **PostgreSQL**

- **Maven**

---

## ⚙️ Configuração do Banco de Dados

1. Crie um banco no PostgreSQL, por exemplo `forumhub`:

```sql
CREATE DATABASE forumhub;


2. Configure o application.properties (ou application.yml):

spring.datasource.url=jdbc:postgresql://localhost:5432/forumhub
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

jwt.secret=SUA_CHAVE_SECRETA_JWT
```
---
## 🔒 Importante:
Nunca compartilhe senhas ou chaves secretas reais em repositórios públicos.

---
## ▶️ Como Executar o Projeto
```sql
git clone https://github.com/seu-usuario/forum-hub-api.git
cd forum-hub-api
mvn spring-boot:run

A API iniciará em:

http://localhost:8080
```
---
## 🔐 Autenticação

A API utiliza JWT. Para acessar rotas protegidas:

1️⃣ Faça login

2️⃣ Receba o token JWT

3️⃣ Envie no header das requisições:

4️⃣ Authorization: Bearer SEU_TOKEN_AQUI

---

## 📡 Endpoints da API
```sql
🔑 Autenticação
Método	Endpoint	Descrição
POST	/login	    Autentica usuário e retorna token JWT
```

```sql
👤 Usuários
Método	Endpoint	Descrição
GET	/usuarios	Lista usuários cadastrados
GET	/usuarios/{id}	Busca usuário por ID
```
```sql
💬 Tópicos
Método	Endpoint	Descrição
GET	/topicos	Lista todos os tópicos
GET	/topicos/{id}	Detalha um tópico
POST	/topicos	Cria novo tópico
PUT	/topicos/{id}	Atualiza um tópico
DELETE	/topicos/{id}	Remove um tópico
```
```sql
📌 Filtro por Estado do Tópico
Método	Endpoint	Descrição
GET	/topicos/estado/{estado}	Lista tópicos por estado

Exemplo de estados possíveis:

- **INATIVO**
- **ATIVO**
```
```sql
🗂 Estrutura do Projeto
src
└── main
└── java
└── challenge_forum_hub.api
├── config
│   ├── OpenApiConfig.java        → Configuração da documentação da API
│   └── SecurityConfig.java       → Configurações do Spring Security
│
├── controller
│   ├── AutenticacaoController.java → Endpoint de login e geração do JWT
│   └── TopicoController.java        → Endpoints de gerenciamento de tópicos
│
├── dto
│   ├── DadosAutenticacao.java     → Dados enviados no login
│   ├── TopicoRequest.java         → Dados para criação de tópico
│   ├── TopicoResponse.java        → Dados retornados ao cliente
│   └── TopicoUpdateRequest.java   → Dados para atualização de tópico
│
├── model
│   ├── EstadoTopico.java          → Enum com os estados do tópico
│   ├── Topico.java                → Entidade JPA de tópicos
│   └── Usuario.java               → Entidade JPA de usuários
│
├── repository
│   ├── TopicoRepository.java      → Acesso ao banco para tópicos
│   └── UsuarioRepository.java     → Acesso ao banco para usuários
│
├── security
│   ├── AutenticacaoFiltro.java    → Filtro que valida o token JWT
│   └── TokenService.java          → Geração e validação de tokens
│
└── ApiApplication.java            → Classe principal da aplicação
```

```sql

📘 Documentação da API (Swagger)

A API possui documentação interativa gerada automaticamente com Springdoc OpenAPI (Swagger).

Com ela você pode:

✔ Visualizar todos os endpoints
✔ Ver os modelos de requisição e resposta
✔ Testar as requisições direto pelo navegador
✔ Informar o token JWT para acessar rotas protegidas


🔗 Acessar a documentação

Com a aplicação rodando:

http://localhost:8080/swagger-ui.html

ou

http://localhost:8080/swagger-ui/index.html

🔐 Como usar o token no Swagger

- **Faça login pelo endpoint de autenticação**
- **Copie o token JWT retornado**
- **Clique no botão Authorize 🔒 no topo do Swagger**
- **Insira: Bearer SEU_TOKEN_AQUI**

✔ Agora você pode testar os endpoints protegidos 🎉
```

## 🔮 Melhorias Futuras

✨ Paginação e ordenação de tópicos  
✨ Implementar filtros por autor ou curso

---


## 👩‍💻 Autora

Projeto desenvolvido por Renata Saturnino Costa 💙  
Curso: One(ORACLE) + Alura  
Para estudos de Java, Spring Boot, APIs REST e Segurança com JWT.