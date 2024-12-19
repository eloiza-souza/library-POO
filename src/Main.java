public class Main {
    public static void main(String[] args) {
        Book livro1 = new Book("Primeiro livro", "Fulano de Tal", "00001");

        Book livro2 = new Book("Primeiro livro modificado", "Fulano de Tal", "00001");
        Book livro3 = new Book("Outro livro", "Ciclano", "00002");
        Book livro4 = new Book("Outro livro novo", "Desconhecido", "00003");

        Book livro5 = new Book("Livro novo", "Autor nome", "00004");
        User user1 = new User("Jose", 1);

        Library library = new Library();
        library.registerNewBook(livro1);
        library.registerNewBook(livro2);
        library.registerNewBook(livro3);
        library.registerNewBook(livro4);
        library.registerNewBook(livro5);
        library.registerNewUser(user1);
        library.returnBook("00001", 1);
        library.lendBook("00005", 1);
        library.lendBook("00001", 2);
        library.lendBook("00001", 1);
        library.lendBook("00002", 1);
        library.lendBook("00003", 1);
        library.lendBook("00001", 1);
        library.returnBook("00002", 1);
        library.lendBook("00001", 1);
        library.lendBook("00002", 1);
        library.showAvailableBooks();

        library.returnBook("00001", 1);
        library.showAvailableBooks();
    }
}