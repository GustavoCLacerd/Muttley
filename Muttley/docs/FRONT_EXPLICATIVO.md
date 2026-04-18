# Documentacao Didatica do Front - Projeto Muttley

## Objetivo deste documento

Este documento explica de forma didatica como o front foi implementado no projeto `Muttley`, para facilitar o alinhamento com o time de backend.

O foco aqui e:

- Entender onde esta cada parte do front.
- Entender como os campos da tela viram dados no backend.
- Entender as variaveis e objetos usados no HTML/Thymeleaf/JavaScript.
- Entender o fluxo completo de interacao entre front e backend.

---

## 1) Arquitetura usada

O projeto segue o modelo **Spring Boot MVC** (server-side rendering):

- **Backend Java** em `src/main/java`
- **Front estatico** em `src/main/resources/static`
- **Template renderizado pelo servidor** em `src/main/resources/templates`

### Front atual implementado

- Tela inicial: `src/main/resources/static/index.html`
- Tela novo cadastro: `src/main/resources/templates/cadastro_aluno.html`
- Estilos compartilhados: `src/main/resources/static/css/front/muttley-front.css`

---

## 2) Como cada tela funciona

## 2.1 Tela inicial (`index.html`)

A tela inicial e um arquivo estatico entregue automaticamente pelo Spring na rota `/`.

Elementos principais:

- Campo de `Nome` (visual, sem envio para backend no fluxo atual).
- Campo de `CPF` com `name="cpf"` (este sim e enviado no submit).
- Botao `Buscar` (submit do formulario).
- Botoes `Importar Lista`, `Novo Cadastro`, `Novo Evento` (navegacao/acoes visuais no momento).

### Formulario da tela inicial

```html
<form action="/alunos/buscar" method="get">
```

Isso significa:

- Ao clicar em **Buscar**, o browser faz `GET /alunos/buscar?cpf=...`
- O valor enviado vem do input com `name="cpf"`

## 2.2 Tela novo cadastro (`cadastro_aluno.html`)

Esta tela usa **Thymeleaf**, entao e renderizada pelo backend.

Ela e aberta pela rota:

- `GET /alunos/novo`

No controller, essa rota injeta no model:

- `model.addAttribute("aluno", new Aluno())`

Assim, o template recebe o objeto `aluno` para bind dos campos.

---

## 3) Variaveis e objetos usados no front

## 3.1 Objeto principal de formulario: `aluno`

No template:

```html
<form th:action="@{/alunos/salvar}" th:object="${aluno}" method="post">
```

Interpretacao:

- `th:object="${aluno}"` define que os campos estao ligados ao objeto `aluno`.
- `th:field="*{...}"` liga cada input a uma propriedade desse objeto.

Exemplos usados:

- `th:field="*{nome}"`
- `th:field="*{cpf}"`
- `th:field="*{curso}"`
- `th:field="*{email}"`
- `th:field="*{ra}"` (campo oculto)

## 3.2 Variavel de erro: `erroRa`

No template:

```html
<div th:if="${erroRa}" th:text="${erroRa}"></div>
```

Interpretacao:

- O backend envia `erroRa` quando a validacao de RA falha.
- Se existir valor, o alerta aparece para o usuario.

## 3.3 Variaveis JavaScript locais na tela de cadastro

No script da pagina:

- `cpfInput`: referencia ao campo CPF.
- `raInput`: referencia ao campo oculto RA.
- `syncRa()`: funcao que copia/deriva valor numerico do CPF para RA.

Resumo da regra:

- Remove caracteres nao numericos do CPF.
- Se tiver ao menos 4 digitos, usa isso no RA.
- Caso contrario, define RA como `"1234"` (fallback).

Objetivo:

- Garantir que o backend receba RA preenchido, sem expor o campo para o usuario nesta versao da tela.

---

## 4) Mapeamento de campos (Front -> Backend)

## Tela inicial

- Campo `CPF` (`name="cpf"`) -> parametro de query em `GET /alunos/buscar`

## Tela novo cadastro

Campos enviados no `POST /alunos/salvar`:

- `nome` -> `Aluno.nome`
- `cpf` -> `Aluno.cpf`
- `curso` -> `Aluno.curso`
- `email` -> `Aluno.email`
- `ra` (hidden) -> `Aluno.ra`

Isso acontece porque o Spring faz bind automatico via:

```java
public String salvarAluno(@ModelAttribute Aluno aluno, Model model)
```

---

## 5) Como o front interage com controller/service/repository

## Fluxo A - Abrir cadastro

1. Usuario clica em `Novo Cadastro` na tela inicial.
2. Browser faz `GET /alunos/novo`.
3. `AlunoController.exibirFormularioCadastro(...)` cria objeto `Aluno`.
4. Spring renderiza `cadastro_aluno.html` com `${aluno}`.

## Fluxo B - Salvar cadastro

1. Usuario preenche formulario e clica `Salvar`.
2. Browser envia `POST /alunos/salvar`.
3. `AlunoController.salvarAluno(...)` recebe `@ModelAttribute Aluno aluno`.
4. Backend valida RA com `aluno.validarRa()`.
5. Se invalido, retorna a mesma view com `erroRa`.
6. Se valido, salva no banco via `alunoRepository.save(aluno)`.
7. Redireciona para `/` (tela inicial).

## Fluxo C - Buscar por CPF

1. Usuario informa CPF na home e clica `Buscar`.
2. Browser envia `GET /alunos/buscar?cpf=...`.
3. `AlunoController.buscarPorCpf(...)` consulta `AlunoRepository.findByCpf(cpf)`.
4. Retorna view de detalhes (a implementar/ajustar no fluxo completo).

---

## 6) Estilo e UX aplicados

O CSS centralizado em `muttley-front.css` aplica:

- Layout em grid para alinhamento de label + input.
- Hierarquia visual de botoes:
  - Primario: `btn-muttley-primary` (acao principal da tela).
  - Secundario: `btn-muttley-secondary` (acoes auxiliares).
  - Perigo: `btn-muttley-danger` (acao destrutiva).
- Estados de foco visiveis para acessibilidade.
- Estado `disabled` com feedback visual.
- Responsividade basica para telas menores.

---

## 7) O que o backend precisa saber para evoluir junto

- A tela de cadastro espera um objeto `aluno` no model (`GET /alunos/novo`).
- O salvamento atual depende de `POST /alunos/salvar`.
- O campo `ra` e enviado oculto; se o backend exigir outra regra, o front pode ajustar facilmente.
- A busca atual usa `cpf` como parametro principal.

---

## 8) Sugestoes de evolucao (proximos passos)

- Implementar tela `detalhes_aluno` para completar o fluxo do botao Buscar.
- Definir regra final de RA (se deve ficar oculto, visivel, ou derivado de outro campo).
- Adicionar validacoes front-end complementares (mascara de CPF, tamanho maximo, feedback inline).
- Integrar os botoes `Importar Lista` e `Novo Evento` com rotas reais.

---

## 9) Resumo em uma frase

O front foi feito para trabalhar no padrao MVC do Spring: a home estatica chama rotas do backend, e o cadastro usa Thymeleaf com bind direto no objeto `Aluno`, enviando os dados por formulario para o controller persistir no banco.

