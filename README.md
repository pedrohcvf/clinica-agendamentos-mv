# 🏥 Clínica Agendamentos MV — Backend

> API RESTful para gerenciamento de agendamentos de consultas médicas — Teste Técnico MV.

---

## 📌 Sobre o Projeto

A **Clínica Agendamentos MV** é uma API REST desenvolvida com Java e Spring Boot para controlar agendamentos de consultas em clínicas médicas. O sistema permite o cadastro de pacientes e profissionais, criação e cancelamento de agendamentos, com validação de regras de negócio e filtros de listagem.

---

## ✨ Funcionalidades

### 👤 Pacientes
- ✅ Cadastro de pacientes com validação de CPF
- ✅ Listagem de pacientes

### 🩺 Profissionais
- ✅ Cadastro de profissionais com validação de CRM único
- ✅ Listagem de profissionais

### 📅 Agendamentos
- ✅ Criação de agendamentos com validação de data/hora
- ✅ Listagem de agendamentos com filtros opcionais
- ✅ Cancelamento de agendamento com registro de motivo
- ✅ Filtro por paciente, profissional ou status

### 🔒 Regras de Negócio
- ✅ Profissional não pode ter dois agendamentos no mesmo horário
- ✅ Não é permitido criar agendamento com data/hora no passado
- ✅ Cancelamento registra motivo e mantém o histórico
- ✅ CPF de paciente único no sistema
- ✅ CRM de profissional único no sistema

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Descrição |
|---|---|
| Java 21 | Linguagem principal |
| Spring Boot 3.5 | Framework para criação da API REST |
| Spring Data JPA | Abstração de acesso a dados |
| Bean Validation | Validação de dados de entrada |
| PostgreSQL | Banco de dados relacional |
| Maven | Gerenciador de dependências |
| Springdoc OpenAPI | Documentação automática via Swagger |
| JUnit 5 + Mockito | Testes automatizados |

---

## 📁 Estrutura do Projeto

```
src/main/java/com/pedro/clinica/
├── paciente/
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── mapper/
│   ├── repository/
│   └── service/
├── profissional/
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── mapper/
│   ├── repository/
│   └── service/
├── agendamento/
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── enums/
│   ├── mapper/
│   ├── repository/
│   └── service/
├── exception/
│   ├── custom/
│   └── dto/
├── config/
└── ClinicaAgendamentosMvApplication.java
```

---

## 📡 Endpoints

### 👤 Pacientes

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/pacientes` | Lista todos os pacientes |
| `POST` | `/pacientes` | Cadastra um novo paciente |

### 🩺 Profissionais

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/profissionais` | Lista todos os profissionais |
| `POST` | `/profissionais` | Cadastra um novo profissional |

### 📅 Agendamentos

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/agendamentos` | Lista agendamentos (com filtros opcionais) |
| `POST` | `/agendamentos` | Cria um novo agendamento |
| `PATCH` | `/agendamentos/{id}/cancelar` | Cancela um agendamento |

**Filtros disponíveis no GET /agendamentos:**
```
/agendamentos?pacienteId={uuid}
/agendamentos?profissionalId={uuid}
/agendamentos?status=AGENDADO
/agendamentos?status=CANCELADO
```

---

## ⚙️ Configuração

O projeto usa dois arquivos de properties para separar configuração de credenciais:

**`application.properties`** — configurações gerais (versionado):
```properties
spring.application.name=clinica-agendamentos-mv
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.profiles.active=local
```

**`application-local.properties`** — credenciais locais (não versionado):
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/clinica_agendamentos
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos
- Java 21 ou superior
- PostgreSQL instalado e rodando
- Maven instalado (ou usar o Maven Wrapper incluso)

### Passo a passo

**1️⃣ Clone o repositório**
```bash
git clone https://github.com/pedrohcvf/clinica-agendamentos-mv.git
cd clinica-agendamentos-mv
```

**2️⃣ Crie o banco de dados**
```sql
CREATE DATABASE clinica_agendamentos;
```

**3️⃣ Configure suas credenciais**

Crie o arquivo `src/main/resources/application-local.properties` com seu usuário e senha do PostgreSQL:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/clinica_agendamentos
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

**4️⃣ Execute a aplicação**

Com Maven Wrapper:
```bash
./mvnw spring-boot:run
```

Ou com Maven instalado:
```bash
mvn spring-boot:run
```

**5️⃣ Acesse a API**
```
http://localhost:8080
```

**6️⃣ Acesse a documentação Swagger**
```
http://localhost:8080/swagger-ui/index.html
```

---

## 📦 Exemplos de Requisição

**POST** `/pacientes` — Cadastrar um paciente:
```json
{
  "name": "João Silva",
  "birthDate": "1990-05-15",
  "cpf": "12345678909",
  "phoneNumber": "81999999999"
}
```

**Resposta** `201 Created`:
```json
{
  "id": "abd7a450-9f5d-44bf-b6a7-bf58c5cdac19",
  "name": "João Silva",
  "birthDate": "1990-05-15",
  "cpf": "12345678909",
  "phoneNumber": "81999999999"
}
```

---

**POST** `/agendamentos` — Criar um agendamento:
```json
{
  "pacienteId": "abd7a450-9f5d-44bf-b6a7-bf58c5cdac19",
  "profissionalId": "9bb452a6-a54f-4dcd-9f4d-9bcd4015d5e8",
  "dateTime": "2026-07-15T09:00:00",
  "appointmentType": "Consulta"
}
```

**Resposta** `201 Created`:
```json
{
  "id": "584032f4-9063-4dab-a216-52d3c8e7c901",
  "patientName": "João Silva",
  "profissionalName": "Dr. Ricardo Alves",
  "dateTime": "2026-07-15T09:00:00",
  "appointmentType": "Consulta",
  "status": "AGENDADO",
  "cancellationReason": null
}
```

---

**PATCH** `/agendamentos/{id}/cancelar` — Cancelar um agendamento:
```json
{
  "cancellationReason": "Paciente não pode comparecer"
}
```

**Resposta** `200 OK`:
```json
{
  "id": "584032f4-9063-4dab-a216-52d3c8e7c901",
  "patientName": "João Silva",
  "profissionalName": "Dr. Ricardo Alves",
  "dateTime": "2026-07-15T09:00:00",
  "appointmentType": "Consulta",
  "status": "CANCELADO",
  "cancellationReason": "Paciente não pode comparecer"
}
```

---

## 🧪 Testes

O projeto conta com testes automatizados cobrindo as principais regras de negócio do domínio de agendamentos:

- ✅ Lança exceção ao criar agendamento com data no passado
- ✅ Lança exceção ao criar agendamento com conflito de horário do profissional
- ✅ Lança exceção ao cancelar agendamento já cancelado

Para rodar os testes:
```bash
./mvnw test
```

---

## 👤 Autor

**Pedro Carvalho**

[![GitHub](https://img.shields.io/badge/GitHub-pedrohcvf-181717?style=flat&logo=github)](https://github.com/pedrohcvf/clinica-agendamentos-mv)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-pcarvalhof-0A66C2?style=flat&logo=linkedin)](https://linkedin.com/in/pcarvalhof)
