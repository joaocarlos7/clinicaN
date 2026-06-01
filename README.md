# CRUD de Uma Clínica

Crud feito em Spring Boot com PostgreSQL. 

---

## Pré-requisitos

- [Git](https://git-scm.com/)
- [Java 26+](https://www.azul.com/downloads/) (projeto usa Azul JDK)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou outro IDE Java
- [DBeaver](https://dbeaver.io/) (ou outro para gerenciar o banco)


---

## 1. Clonar o repositório

```bash
git clone https://github.com/joaocarlos7/clinicaN.git
cd clinicaN
```

---

## 2. Instalar o Docker Desktop

1. Acesse [docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop/)
2. Baixe a versão para o seu sistema operacional (Windows, Linux, Mac)
3. Instale e abra o Docker Desktop
4. Aguarde o ícone da baleia aparecer na barra de status — significa que o Docker está rodando

---

## 3. Subir o container do PostgreSQL

Na raiz do projeto (onde está o `docker-compose.yml`), execute:

```bash
docker compose up -d
```

Verifique se o container subiu:

```bash
docker ps
```

Deve aparecer um container chamado `clinicaN` com status `Up`.

**Credenciais do banco:**

| Campo   | Valor        |
|---------|--------------|
| Host    | localhost    |
| Porta   | 5432         |
| Banco   | clinicaN     |
| Usuário | clinica123   |
| Senha   | clinica123   |

---

## 4. Criar as tabelas no banco

### No DBeaver
1. Abra o DBeaver e crie uma nova conexão PostgreSQL com as credenciais acima
2. Para criar as tabelas e importar os dados, rode os scripts em ordem da pasta resources no Script SQL do Dbeaver.

---


## 5. Executar a aplicação

### Via IntelliJ IDEA

1. Abra o projeto no IntelliJ
2. Aguarde o Maven baixar as dependências
4. Localize a classe `ClinicaNApplication.java`
5. Clique no botão **Run** (▶) ou use `Shift + F10`

### Via terminal

```bash
./mvnw spring-boot:run
```

Aguarde a mensagem:
```
Started ClinicaNApplication in X.XXX seconds
```

---

## 6. Acessar a API

A aplicação estará disponível em: **http://localhost:8080**

---

## Prints de Execução

(docs/images/)

---

## Autoria

```bash

Desenvolvido por João Carlos Vieira Machado.

LinkedIn: https://www.linkedin.com/in/jo%C3%A3o-carlos-machado-3ab280100/
