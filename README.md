# Sistema de Gerenciamento de Biblioteca

## Contexto
Este projeto é um sistema simples de gerenciamento de livros e usuários para uma biblioteca. 
Ele foi desenvolvido em Java e utiliza conceitos básicos de Programação Orientada a Objetos (POO), 
como classes, objetos, atributos, métodos, encapsulamento e relacionamento entre classes.

O sistema permite cadastrar livros, cadastrar usuários, realizar empréstimos e devoluções de livros, 
além de exibir relatórios com os livros ou usuários cadastrados no sistema, os livros disponíveis 
para empréstimo e os livros que um usuário já pegou emprestado.

Algumas melhorias que podem ser implementada no futuro, dentre outras, são a possibilidade de cadastro e empréstimo de 
mais de um exemplar do mesmo livro, sistema de busca de livros por autor, título ou ISBN, relatórios de 
uso e interface gráfica.

---

## Tecnologias Utilizadas - 
**Linguagem**: Java

**Paradigma**: Programação Orientada a Objetos (POO) 

--- 

## Regras de Negócio
- Um livro só pode ser emprestado se estiver disponível.
- Um usuário pode pegar no máximo **3 livros emprestados** ao mesmo tempo.
- Ao devolver um livro, ele deve ser marcado como disponível novamente.

---

## Funcionalidades
O sistema oferece as seguintes funcionalidades por meio de um menu no console:
1. **Cadastrar livro**: Permite adicionar um novo livro à biblioteca.
2. **Cadastrar usuário**: Permite adicionar um novo usuário ao sistema.
3. **Realizar empréstimo**: Permite que um usuário pegue um livro emprestado.
4. **Realizar devolução**: Permite que um usuário devolva um livro emprestado.
5. **Listar livros da bibilioteca**: Lista todos os livros cadastrados.
6. **Listar livros disponíveis**: Lista todos os livros que estão disponíveis para empréstimo.
7. **Listar livros por usuário**: Lista todos os livros que um usuário já pegou emprestado.
8. **Listar usuários do sitema**: Lita todos os usuários cadastrados na biblioteca.
9. **Sair**: Encerra o programa.

---

## Como Executar 
1. Clone este repositório. 
2. Compile os arquivos `.java` utilizando o compilador Java. 
3. Execute o programa no console.
4. Siga as instruções do menu para interagir com o sistema. 

--- 



## Autor 
Este projeto foi desenvolvido como um exercício prático para aplicar conceitos de POO em Java.