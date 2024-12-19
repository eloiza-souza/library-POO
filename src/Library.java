import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Library {
    private List<Book> bookList;
    private List<User> userList;

    public Library() {
        this.bookList = new ArrayList<>();
        this.userList = new ArrayList<>();
    }

    public void registerNewBook(Book book) {
        this.bookList.add(book);
    }

    public void registerNewUser(User user) {
        this.userList.add(user);
    }

    public void lendBook(String isbn, int idUser) {
        Optional<Book> book = searchBookByIsbn(isbn);
        if (book.isEmpty()) {
            showNotLendBook("Livro não cadastrado");
            return;
        }
        if (!book.get().isAvailable()) {
            showNotLendBook("livro indisponível");
            return;
        }
        Optional<User> user = searchUserById(idUser);
        if (user.isEmpty()) {
            showNotLendBook("Usuário não cadastrado.");
            return;
        }
        if (!isUserAvailable(user.get())) {
            showNotLendBook("Usuário com número máximo de livros permitido." +
                    "\nDevolva um livro para realizar novo empréstimo");
            return;
        }
        book.get().lend();
        user.get().addBook(book.get());
        System.out.println("Empréstimo realizado com sucesso!");
    }



    private Optional<Book> searchBookByIsbn(String isbn) {
        return this.bookList.stream()
                .filter(Book::isAvailable)
                .findFirst();
    }

    private Optional<User> searchUserById(int id) {
        return this.userList.stream()
                .filter(user -> (user.getId() == id))
                .findFirst();
    }

    private void showNotLendBook(String message) {
        System.out.println("Não foi possível realizar o empréstimo - " + message);
    }

    private void showNotReturnBook(String message) {
        System.out.println("Não foi possível devolver o livro - " + message);
    }

    private boolean isUserAvailable(User user){
        int count =0;
        for (Book book: user.lendedBooks){
           if (!book.isAvailable())
               count++;
        }
        return count < 3;
    }
}
