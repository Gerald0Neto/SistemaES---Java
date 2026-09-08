# 🎓 Sistema de Gestão de Alunos e Cursos

Sistema web desenvolvido com **JSP (JavaServer Pages)** para gerenciamento básico de **alunos e cursos**.

O projeto tem como objetivo praticar o desenvolvimento de aplicações web utilizando Java, trabalhando com cadastro, consulta e gerenciamento de informações acadêmicas.

> 🚧 **Projeto em desenvolvimento:** uma próxima atualização irá adicionar o módulo de **matrículas**, permitindo relacionar alunos aos cursos cadastrados.

---

## 📌 Sobre o projeto

O sistema permite realizar o cadastro e gerenciamento de alunos e cursos.

Atualmente, o projeto possui duas entidades principais:

* 👨‍🎓 **Aluno**
* 📚 **Curso**

A proposta é evoluir o sistema para que seja possível realizar a matrícula de um aluno em um determinado curso.

### Fluxo planejado

```text
Aluno
  │
  └──► Matrícula ◄── Curso
```

Dessa forma, um aluno poderá estar matriculado em um ou mais cursos, enquanto um curso poderá possuir vários alunos.

---

## 🚀 Funcionalidades atuais

### 👨‍🎓 Cadastro de Alunos

O sistema permite cadastrar informações dos alunos e armazená-las para posterior consulta e gerenciamento.

### 📚 Cadastro de Cursos

É possível cadastrar cursos que estarão disponíveis para futuras matrículas.

### 🔎 Consulta

O sistema permite visualizar os registros cadastrados de alunos e cursos.

---

## 🔜 Próxima atualização

A próxima versão do projeto terá como foco o **módulo de matrícula**.

Será possível:

* Realizar matrícula de um aluno em um curso;
* Selecionar um aluno cadastrado;
* Selecionar um curso cadastrado;
* Registrar a data da matrícula;
* Consultar as matrículas realizadas;
* Visualizar quais cursos um aluno está matriculado;
* Visualizar quais alunos estão matriculados em determinado curso.

### Exemplo

```text
Aluno: João da Silva

Curso: Java Web

Matrícula:
João da Silva → Java Web
```

---

## 🛠️ Tecnologias utilizadas

* ☕ **Java**
* 🌐 **JSP (JavaServer Pages)**
* 🗄️ **MySQL**
* 🔌 **JDBC**
* 🖥️ **Apache Tomcat**
* 🎨 **HTML5**
* 🎨 **CSS3**
* 🐙 **Git / GitHub**


## 🎯 Objetivo do projeto

Este projeto foi desenvolvido com o objetivo de colocar em prática conceitos de desenvolvimento web utilizando **Java e JSP**, trabalhando desde o cadastro de dados até o relacionamento entre diferentes entidades.

A evolução planejada permitirá aplicar conceitos importantes de:

* CRUD;
* JDBC;
* Relacionamentos entre tabelas;
* Chaves estrangeiras;
* Formulários JSP;
* Validação de dados;
* Organização de aplicações Java Web.

---

## 📈 Roadmap

* [x] Cadastro de alunos
* [x] Cadastro de cursos
* [x] Consulta de alunos
* [x] Consulta de cursos
* [ ] Cadastro de matrículas
* [ ] Relacionamento aluno × curso
* [ ] Consulta de matrículas
* [ ] Edição de matrículas
* [ ] Cancelamento de matrícula
* [ ] Melhorias na interface
* [ ] Validação dos formulários
* [ ] Melhorias na arquitetura do projeto

---

## 👨‍💻 Autor

**Geraldo Neto**

Projeto desenvolvido para estudos e prática de desenvolvimento de aplicações Web com Java, JSP, JDBC e MySQL.
