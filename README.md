# API REST CRUD - Sistema de Gestão de Produtos, Categorias e Fornecedores

**Instituição:** IBMEC

**Disciplina:** Técnicas Avançadas de Programação (IBM3120)

**Semestre:** 1º Semestre de 2026

**Professor:** Thiago Souza

**Aluna:** Sarah Ferrari Rodrigues

---

# Sobre o Projeto

Este projeto consiste no desenvolvimento de uma API REST CRUD utilizando Java e Spring Boot para gerenciamento de produtos, categorias e fornecedores.

A aplicação foi desenvolvida seguindo os requisitos da disciplina de Técnicas Avançadas de Programação, incluindo:

* API REST CRUD
* Banco de dados relacional
* Spring Boot
* Swagger/OpenAPI
* JDBC para acesso ao banco
* Aplicação de Design Patterns
* Relacionamentos 1:N e N:M

---

# Tecnologias Utilizadas

* Java 17+
* Spring Boot 3+
* JDBC
* MySQL
* Swagger / OpenAPI
* IntelliJ IDEA
* Maven
* Clever Cloud (Banco de Dados em Nuvem)

---

# Modelo de Domínio

O sistema é composto por três entidades principais:

## Categoria

Representa a categoria de um produto.

Exemplos:

* Informática
* Periféricos
* Armazenamento

### Atributos

* id
* nome
* descricao

---

## Produto

Representa um produto comercializado.

Exemplos:

* Notebook Dell Inspiron
* Mouse Logitech G203
* SSD Samsung 1TB

### Atributos

* id
* nome
* preco
* categoriaId

---

## Fornecedor

Representa uma empresa fornecedora de produtos.

Exemplos:

* Dell Brasil
* Kabum
* Samsung

### Atributos

* id
* nome
* email

---

# Relacionamentos

## Relacionamento 1:N

### Categoria → Produto

Uma categoria pode possuir vários produtos.

Exemplo:

Categoria Informática

* Notebook Dell
* Computador Gamer
* Teclado Mecânico

Já um produto pertence a apenas uma categoria.

---

## Relacionamento N:M

### Produto ↔ Fornecedor

Um produto pode possuir vários fornecedores.

Um fornecedor pode fornecer vários produtos.

Esse relacionamento foi implementado através da tabela associativa:

```text
produto_fornecedor
```

Campos:

```text
produto_id
fornecedor_id
```

Exemplo:

Notebook Dell

* Dell Brasil
* Kabum

Monitor Dell

* Dell Brasil
* Kabum

````

---

# Estrutura do Banco de Dados

Tabelas criadas:

```text
categorias
produtos
fornecedores
produto_fornecedor
````

Relacionamentos:

```text
categorias (1) -------- (N) produtos

produtos (N) -------- (N) fornecedores
               |
               |
       produto_fornecedor
```

O banco de dados está hospedado na plataforma Clever Cloud.

---

# Arquitetura da Aplicação

A aplicação foi organizada em camadas:

```text
controller
dao
model
factory
config
```

## Controller

Responsável pelos endpoints REST.

Exemplos:

* CategoriaController
* ProdutoController
* FornecedorController
* ProdutoFornecedorController

---

## DAO

Responsável pelo acesso ao banco de dados através de JDBC.

Exemplos:

* CategoriaDao
* ProdutoDao
* FornecedorDao
* ProdutoFornecedorDao

---

## Model

Representação das entidades de domínio.

Exemplos:

* Categoria
* Produto
* Fornecedor
* ProdutoFornecedor

---

# Design Patterns Aplicados

## 1. Singleton

Implementado na classe:

```text
Conexao
```

Objetivo:

Garantir uma única instância de conexão com o banco de dados durante a execução da aplicação.

Benefícios:

* Redução de abertura de conexões
* Centralização do acesso ao banco
* Menor consumo de recursos

---

## 2. Factory Method

Implementado na classe:

```text
DaoFactory
```

Objetivo:

Centralizar a criação dos objetos DAO.

Exemplo:

```java
DaoFactory.criarDao(CategoriaDao.class)
```

Benefícios:

* Baixo acoplamento
* Maior flexibilidade
* Facilidade de manutenção

---

# Princípios de Engenharia de Software

Durante o desenvolvimento foram aplicados conceitos de:

## Clean Code

* Nomes descritivos
* Métodos pequenos
* Separação de responsabilidades
* Código organizado em camadas

## SOLID

### Single Responsibility Principle (SRP)

Cada classe possui apenas uma responsabilidade.

Exemplo:

* Controller → Requisições HTTP
* DAO → Banco de Dados
* Model → Dados

### Open/Closed Principle (OCP)

A utilização da Factory permite adicionar novos DAOs sem modificar a estrutura principal da aplicação.

---

# Documentação Swagger

A API possui documentação automática utilizando Swagger/OpenAPI.

Após iniciar a aplicação:

```text
http://localhost:8080/swagger-ui/index.html
```

É possível:

* Visualizar endpoints
* Testar requisições
* Enviar parâmetros
* Consultar respostas

---

# Endpoints Disponíveis

## Categorias

```text
GET    /api/categorias
GET    /api/categorias/{id}
POST   /api/categorias
PUT    /api/categorias/{id}
DELETE /api/categorias/{id}
```

---

## Produtos

```text
GET    /api/produtos
GET    /api/produtos/{id}
POST   /api/produtos
PUT    /api/produtos/{id}
DELETE /api/produtos/{id}
```

---

## Fornecedores

```text
GET    /api/fornecedores
GET    /api/fornecedores/{id}
POST   /api/fornecedores
PUT    /api/fornecedores/{id}
DELETE /api/fornecedores/{id}
```

---

## ProdutoFornecedor

```text
GET    /api/produto-fornecedor
GET    /api/produto-fornecedor/produto/{produtoId}
POST   /api/produto-fornecedor
DELETE /api/produto-fornecedor/{produtoId}/{fornecedorId}
```

---

# Como Executar

1. Clonar o repositório

2. Configurar as credenciais do banco na classe de conexão

3. Executar a aplicação Spring Boot

```text
TapAp2Application.java
```

4. Acessar o Swagger

```text
http://localhost:8080/swagger-ui/index.html
```

---