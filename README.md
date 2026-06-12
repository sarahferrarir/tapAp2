# API REST CRUD - Sistema de Gestão da Copa do Mundo de Futebol

**Instituição:** IBMEC

**Disciplina:** Técnicas Avançadas de Programação (IBM3120)

**Semestre:** 1º Semestre de 2026

**Professor:** Thiago Souza

**Aluna:** Sarah Ferrari Rodrigues

---

# Sobre o Projeto

Este projeto consiste no desenvolvimento de uma API REST CRUD utilizando Java e Spring Boot para gerenciamento de informações relacionadas à Copa do Mundo de Futebol.

A aplicação foi desenvolvida com base no minimundo proposto na avaliação final da disciplina, permitindo o gerenciamento de seleções, jogadores e partidas da competição.

O sistema contempla:

* API REST CRUD
* Banco de dados relacional
* Spring Boot
* Swagger/OpenAPI
* JDBC para acesso aos dados
* Relacionamentos 1:N e N:N
* Aplicação de Design Patterns
* Aplicação de princípios SOLID
* Aplicação de boas práticas de Clean Code
* Deploy em nuvem

---

# Tecnologias Utilizadas

* Java 17+
* Spring Boot 3+
* JDBC
* MySQL
* Maven
* Swagger / OpenAPI
* IntelliJ IDEA
* Clever Cloud (Banco de Dados em Nuvem)
* Render (Hospedagem da API)

---

# Modelo de Domínio

O sistema é composto por três entidades principais.

## Seleção

Representa uma seleção participante da Copa do Mundo.

### Exemplos

* Brasil
* Argentina
* França

### Atributos

* id
* nomePais
* tecnico
* rankingFifa

---

## Jogador

Representa um atleta pertencente a uma seleção.

### Exemplos

* Vinicius Junior
* Lionel Messi
* Kylian Mbappé

### Atributos

* id
* nome
* numeroCamisa
* posicao
* idade
* selecaoId

---

## Partida

Representa uma partida disputada durante a competição.

### Exemplos

* Brasil x Argentina
* França x Alemanha

### Atributos

* id
* dataPartida
* estadio
* faseCompeticao
* placar

---

# Relacionamentos

## Relacionamento 1:N

### Seleção → Jogador

Uma seleção pode possuir vários jogadores.

Um jogador pertence a apenas uma seleção.

### Exemplo

Brasil

* Vinicius Junior
* Bruno Guimarães
* Marquinhos

Argentina

* Messi
* Julián Álvarez
* Enzo Fernández

---

## Relacionamento N:N

### Partida ↔ Seleção

Uma partida envolve duas seleções.

Uma seleção pode participar de várias partidas ao longo da competição.

Esse relacionamento foi implementado através da tabela associativa:

```text
partida_selecao
```

Campos:

```text
partida_id
selecao_id
```

### Exemplo

Partida Brasil x Argentina

* Brasil
* Argentina

Partida Brasil x França

* Brasil
* França

---

# Estrutura do Banco de Dados

Tabelas criadas:

```text
selecoes
jogadores
partidas
partida_selecao
```

Relacionamentos:

```text
selecoes (1) -------- (N) jogadores

partidas (N) -------- (N) selecoes
               |
               |
         partida_selecao
```

O banco de dados MySQL está hospedado na plataforma Clever Cloud e é acessado pela aplicação através de JDBC.

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

* SelecaoController
* JogadorController
* PartidaController
* PartidaSelecaoController

---

## DAO

Responsável pelo acesso ao banco de dados utilizando JDBC.

Exemplos:

* SelecaoDao
* JogadorDao
* PartidaDao
* PartidaSelecaoDao

---

## Model

Representação das entidades de domínio.

Exemplos:

* Selecao
* Jogador
* Partida
* PartidaSelecao

---

## Config

Responsável pelas configurações da aplicação.

Exemplo:

* Conexao

---

## Factory

Responsável pela criação centralizada dos DAOs.

Exemplo:

* DaoFactory

---

# Design Patterns Aplicados

## 1. Singleton

Implementado na classe:

```text
Conexao
```

Objetivo:

Garantir uma única instância de conexão com o banco de dados durante toda a execução da aplicação.

Benefícios:

* Redução da criação de conexões
* Centralização do acesso ao banco
* Menor consumo de recursos

---

## 2. Factory Method

Implementado na classe:

```text
DaoFactory
```

Objetivo:

Centralizar a criação dos objetos DAO da aplicação.

Exemplo:

```java
DaoFactory.criarDao(SelecaoDao.class)
```

Benefícios:

* Baixo acoplamento
* Facilidade de manutenção
* Flexibilidade para expansão futura

---

# Princípios de Engenharia de Software

Durante o desenvolvimento foram aplicados conceitos de:

## Clean Code

* Nomes descritivos
* Métodos pequenos e objetivos
* Baixa duplicação de código
* Organização por responsabilidade
* Separação em camadas

---

## SOLID

### Single Responsibility Principle (SRP)

Cada classe possui apenas uma responsabilidade.

Exemplos:

* Controller → Requisições HTTP
* DAO → Persistência de dados
* Model → Representação das entidades

---

### Open/Closed Principle (OCP)

A utilização da Factory permite adicionar novos DAOs sem modificar a estrutura principal da aplicação.

---

# Documentação Swagger

A API possui documentação automática utilizando Swagger/OpenAPI.

Ambiente local:

```text
http://localhost:8080/swagger-ui/index.html
```

Através do Swagger é possível:

* Visualizar endpoints
* Testar requisições
* Consultar parâmetros
* Validar respostas
* Realizar testes diretamente pelo navegador

---

# Deploy em Nuvem

Como requisito bônus da atividade, a API pode ser disponibilizada em ambiente de nuvem utilizando a plataforma Render.

Infraestrutura utilizada:

* Aplicação Spring Boot hospedada no Render
* Banco MySQL hospedado no Clever Cloud
* Comunicação realizada via JDBC

---

# Endpoints Disponíveis

## Seleções

```text
GET    /api/selecoes
GET    /api/selecoes/{id}
POST   /api/selecoes
PUT    /api/selecoes/{id}
DELETE /api/selecoes/{id}
```

---

## Jogadores

```text
GET    /api/jogadores
GET    /api/jogadores/{id}
POST   /api/jogadores
PUT    /api/jogadores/{id}
DELETE /api/jogadores/{id}
```

---

## Partidas

```text
GET    /api/partidas
GET    /api/partidas/{id}
POST   /api/partidas
PUT    /api/partidas/{id}
DELETE /api/partidas/{id}
```

---

## PartidaSelecao

```text
GET    /api/partida-selecao
GET    /api/partida-selecao/partida/{partidaId}
POST   /api/partida-selecao
DELETE /api/partida-selecao/{partidaId}/{selecaoId}
```

---

# Como Executar

### 1. Clonar o repositório

```bash
git clone <url-do-repositorio>
```

### 2. Configurar as credenciais do banco

Atualizar as informações de conexão na classe:

```text
Conexao.java
```

### 3. Executar a aplicação

Executar a classe:

```text
TapAp2Application.java
```

### 4. Acessar o Swagger

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Requisitos do Trabalho Atendidos

✅ API REST CRUD

✅ Três entidades de domínio

✅ Relacionamento 1:N

✅ Relacionamento N:N

✅ Banco de dados relacional

✅ Spring Boot

✅ Swagger/OpenAPI

✅ Design Patterns (Singleton e Factory Method)

✅ Aplicação de princípios de Clean Code

✅ Aplicação de princípios SOLID

✅ Deploy em nuvem (Render)

✅ Banco de dados em nuvem (Clever Cloud)
