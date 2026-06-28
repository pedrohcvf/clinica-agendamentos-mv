# DECISOES.md — Clínica Agendamentos MV

---

## Quais foram as principais decisões técnicas?

Arquitetura modular por domínio (`paciente/`, `profissional/`, `agendamento/`), onde cada domínio contém suas próprias camadas. DTOs implementados como Records do Java por serem imutáveis e eliminarem boilerplate. Exceções customizadas genéricas e reutilizáveis (`ConflictException`, `ResourceNotFoundException`, `InvalidDateException`) em vez de uma exceção por cenário. Validação de data no passado feita no Service (não no DTO com `@Future`) para ter controle total sobre a mensagem de erro retornada. Credenciais do banco separadas em `application-local.properties` fora do versionamento.

---

## O que você priorizou e o que ficou de fora?

Priorizei a cobertura completa das regras de negócio, tratamento de erros padronizado, validações consistentes e qualidade do código. Entregui 3 testes automatizados (mínimo era 1) e documentação via Swagger. Ficou de fora o frontend — diferencial, não obrigatório. O Oracle também não foi implementado, mas a stack com Spring Data JPA é compatível por design: bastaria trocar o driver e a URL de conexão.

---

## Se utilizou IA, em quais partes e como validou o resultado?

Utilizei IA (Claude) como assistente técnico durante todo o projeto — funcionando como um par de programação. Auxiliou na estruturação da arquitetura, explicação de conceitos desconhecidos (ex: `@Query` JPQL, testes com Mockito), code review e documentação. A validação foi feita por testes manuais no Postman, testes automatizados e revisão do código antes de cada commit. Nenhum código foi aplicado sem entendimento prévio — quando não conhecia algo, pedi explicação antes de implementar.
