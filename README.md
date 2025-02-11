
# Documentação da API - Aplicativo de Anotações

## Visão Geral
Esta API fornece os endpoints necessários para gerenciar anotações em um aplicativo de notas. As operações incluem a criação, leitura, atualização e exclusão de anotações.

---

## Tópicos abordados nesse projeto
- Setup inicial de projeto com o Spring Boot Initialzr;
- Criação de modelo de dados para o mapeamento de entidades em bancos de dados;
- Desenvolvimento de operações de gerenciamento das anotações CRUD (Cadastro, leitura, atualização e remoção de anotações do sistema).
- API desenvolvida orientada ao padrão arquitetural REST.
- Implantação do sistema na nuvem através do [Railway][railway].

---

## URL do projeto e documentação Swagger na nuvem
```
https://api-anotacoes.up.railway.app
```

```
https://api-anotacoes.up.railway.app/swagger-ui/index.html
```

## Endpoints

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
## Pré-requisitos para executar esse projeto
- Java 17 ou versões superiores.
- Sua IDE favorita, utilizei o Intellj IDEA Community Edition.
- Controle de versão GIT.
- Conta no [GitHub][github] para o versionamento do seu projeto online.
- Conta no [Railway][railway] para o deploy do projeto na nuvem.


[railway]: https://railway.com/
[github]: https://github.com/