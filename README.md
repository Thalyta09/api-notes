
# 📄 Documentação da API - Aplicativo de Anotações

## ✅ Visão Geral
Esta API fornece os endpoints necessários para gerenciar anotações em um aplicativo de notas. As operações incluem a criação, leitura, atualização e exclusão de anotações.

---

## 📌 Tópicos abordados nesse projeto
- Setup inicial de projeto com o Spring Boot Initialzr;
- Criação de modelo de dados para o mapeamento de entidades em bancos de dados;
- Desenvolvimento de operações de gerenciamento das anotações CRUD (Cadastro, leitura, atualização e remoção de anotações do sistema).
- API desenvolvida orientada ao padrão arquitetural REST.
- Autenticação com Spring Security + JWT.
- Documentação do projeto com a biblioteca Swagger UI.

## 🌐 Endpoints

### 1. **Listar Todas as Anotações**
**Descrição:** Retorna uma lista com todas as anotações criadas.  
**Método:** `GET`  
**URL:** `/api/notes`  

**Resposta:**
```json
[
  {
    "id": 1,
    "titulo": "Minha Primeira Nota",
    "conteudo": "Este é o conteúdo da nota.",
    "dataCriacao": "2025-01-01T12:00:00Z",
    "dataAtualizacao": "2025-01-02T14:00:00Z"
  },
  {
    "id": 2,
    "titulo": "Lista de Compras",
    "conteudo": "Leite, pão, ovos, café.",
    "dataCriacao": "2025-01-05T08:30:00Z",
    "dataAtualizacao": null
  }
]
```

---

### 2. **Criar Nova Nota**
**Descrição:** Cria uma nova nota no sistema.  
**Método:** `POST`  
**URL:** `/api/notes`  

**Corpo da Requisição:**
```json
{
  "titulo": "Título da Nota",
  "conteudo": "Conteúdo da Nota"
}
```

**Resposta:**
```json
{
  "id": 3,
  "titulo": "Título da Nota",
  "conteudo": "Conteúdo da Nota",
  "dataCriacao": "2025-01-25T10:00:00Z",
  "dataAtualizacao": null
}
```

---

### 3. **Visualizar Detalhes de uma Nota**
**Descrição:** Retorna os detalhes de uma nota específica.  
**Método:** `GET`  
**URL:** `/api/notes/{id}`  

**Resposta:**
```json
{
  "id": 1,
  "titulo": "Minha Primeira Nota",
  "conteudo": "Este é o conteúdo da nota.",
  "dataCriacao": "2025-01-01T12:00:00Z",
  "dataAtualizacao": "2025-01-02T14:00:00Z"
}
```

---

### 4. **Atualizar uma Nota**
**Descrição:** Atualiza os dados de uma nota específica.  
**Método:** `PUT`  
**URL:** `/api/notes/{id}`  

**Corpo da Requisição:**
```json
{
  "titulo": "Novo Título",
  "conteudo": "Novo Conteúdo"
}
```

**Resposta:**
```json
{
  "id": 1,
  "titulo": "Novo Título",
  "conteudo": "Novo Conteúdo",
  "dataCriacao": "2025-01-01T12:00:00Z",
  "dataAtualizacao": "2025-01-25T10:30:00Z"
}
```

---

### 5. **Excluir uma Nota**
**Descrição:** Remove uma nota do sistema.  
**Método:** `DELETE`  
**URL:** `/api/notes/{id}`  

**Resposta:**
- **Código 204 - Sem Conteúdo**
```

```
## ✏️ Pré-requisitos para executar esse projeto
- Java 17 ou versões superiores.
- Sua IDE favorita, utilizei o Intellj IDEA Community Edition.
- Controle de versão GIT.
- Conta no [GitHub][github] para o versionamento do seu projeto online.
- Conta no [Railway][railway] para o deploy do projeto na nuvem.


[railway]: https://railway.com/
[github]: https://github.com/

## 🔐 Segurança (Spring Security)

A API implementa uma camada de segurança robusta utilizando **Spring Security** para proteger os endpoints. A autenticação é do tipo **stateless**, utilizando **JSON Web Tokens (JWT)**.

As seguintes funcionalidades de segurança foram configuradas:

* **Autenticação JWT:** Acesso aos endpoints protegidos requer um `Bearer Token` válido.
* **Endpoint de Login:** A rota pública `POST /login` é usada para autenticar um usuário (via banco de dados) e gerar seu token JWT.
* **Autorização Baseada em Papéis (RBAC):** Os usuários são divididos em papéis (`ROLE_USER` e `ROLE_ADMIN`) que determinam suas permissões.
 * `ROLE_ADMIN` pode executar qualquer ação do CRUD.
 * `ROLE_USER` pode criar e consultar, mas não pode deletar notas.
* **Criptografia de Senhas:** Senhas são armazenadas de forma segura no banco de dados usando **BCrypt**.
* **Tratamento de Exceções:** A API retorna respostas de erro personalizadas para falhas de autenticação (`401`) e autorização (`403`).

## 📚 Documentação da API (Swagger)

Este projeto utiliza **SpringDoc (OpenAPI 3)** para gerar a documentação interativa da API. Após iniciar a aplicação, você pode acessar e testar todos os endpoints diretamente pelo seu navegador:

* **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
* **Definição OpenAPI (JSON):** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Na interface do Swagger, você pode usar o botão "Authorize" (no canto superior direito) para inserir seu token JWT e testar as rotas protegidas.

## ⚙️ Configuração (Variáveis de Ambiente)

Para executar esta aplicação, você precisará configurar as seguintes variáveis de ambiente. Você pode configurá-las na sua "Run Configuration" da IDE (como o IntelliJ) ou no seu sistema operacional.

| Variável | Descrição | Exemplo |
| :--- | :--- | :--- |
| `DATABASE_URL` | A string de conexão JDBC completa para seu banco de dados PostgreSQL. | `jdbc:postgresql://localhost:5432/api_notes_db` |
| `dbuser` | O nome de usuário para acessar o banco de dados. | `postgres` |
| `dbpass` | A senha para acessar o banco de dados. | `suaSenhaSecreta` |
| `JWT_SECRET` | A chave secreta usada para assinar e validar os tokens JWT. (Use uma string longa e aleatória). | `minha-chave-secreta-super-longa-e-dificil-de-adivinhar-123456` |