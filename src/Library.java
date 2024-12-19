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

    public boolean lendBook(String isbn, int idUser) {
        Optional<Book> bookOptional = searchBookByIsbn(isbn);
        if (bookOptional.isEmpty()) {
            showNotLendBook("Livro não cadastrado");
            return false;
        }
        Book book = bookOptional.get();

        if (!book.isAvailable()) {
            showNotLendBook("livro indisponível");
            return false;
        }
        Optional<User> userOptional = searchUserById(idUser);
        if (userOptional.isEmpty()) {
            showNotLendBook("Usuário não cadastrado.");
            return false;
        }
        User user = userOptional.get();

        if (!isUserAvailable(user)) {
            showNotLendBook("Usuário com número máximo de livros permitido." +
                    "\nDevolva um livro para realizar novo empréstimo");
            return false;
        }
        book.lend();
        user.addBook(book);
        System.out.println("Empréstimo realizado com sucesso!");
        return true;
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
