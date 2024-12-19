package enums;

public enum MenuOptions {
    BOOK_REGISTER("Cadastrar livro"),
    USER_REGISTER("Cadastrar usuário"),
    LEAD_BOOK ("Realizar empréstimo"),
    RETURN_BOOK ("Realizar devolução"),
    LIBRARY_BOOKS("Exibir todos os livros da biblioteca"),
    AVAILABLE_BOOKS("Listar livros disponíveis"),
    BOOKS_USER ("Listar livros por usuário");


    private final String description;

    MenuOptions(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

}
