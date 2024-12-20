package model;

public enum MenuOptionsEnum {
    BOOK_REGISTER("Cadastrar livro"),
    USER_REGISTER("Cadastrar usuário"),
    LEAD_BOOK("Realizar empréstimo"),
    RETURN_BOOK("Realizar devolução"),
    LIBRARY_BOOKS("Listar livros da biblioteca"),
    AVAILABLE_BOOKS("Listar livros disponíveis"),
    BOOKS_USER("Listar livros por usuário"),
    LIBRARY_USERS("Listar usuários do sistema");

    private final String description;

    MenuOptionsEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
