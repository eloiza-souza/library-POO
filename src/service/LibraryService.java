package service;

import model.Book;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryService {
    private final List<Book> bookList;
    private final List<User> userList;

    public LibraryService() {
        this.bookList = new ArrayList<>();
        this.userList = new ArrayList<>();
    }

    public LibraryService(List<Book> bookList, List<User> userList) {
        this.bookList = bookList;
        this.userList = userList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public boolean registerNewBook(Book book) {
        if (bookList.contains(book)) {
            System.out.println("Livro já cadastrado");
            return false;
        }
        this.bookList.add(book);
        System.out.println("Livro cadastrado com sucesso!");
        return true;
    }

    public boolean registerNewUser(User user) {
        if (userList.contains(user)) {
            System.out.println("Usuário já cadastrado");
            return false;
        }
        this.userList.add(user);
        System.out.println("Usuário cadastrado com sucesso!");
        return true;
    }

    public boolean lendBook(String isbn, int idUser) {
        Optional<Book> bookOptional = searchBookByIsbn(isbn);
        if (bookOptional.isEmpty()) {
            showNotLendBookMessage("Livro não cadastrado");
            return false;
        }
        Book book = bookOptional.get();

        if (!book.isAvailable()) {
            showNotLendBookMessage("Livro indisponível");
            return false;
        }
        Optional<User> userOptional = searchUserById(idUser);
        if (userOptional.isEmpty()) {
            showNotLendBookMessage("Usuário não cadastrado.");
            return false;
        }
        User user = userOptional.get();

        if (!isUserAvailable(user)) {
            showNotLendBookMessage("Usuário com número máximo de livros permitido." +
                    "\nDevolva um livro para realizar novo empréstimo");
            return false;
        }
        book.lend();
        user.addBook(book);
        System.out.println("Empréstimo realizado com sucesso!");
        return true;
    }

    public boolean returnBook(String isbn, int idUser) {
        Optional<User> userOptional = searchUserById(idUser);
        if (userOptional.isEmpty()) {
            showNotReturnBookMessage("Usuário não cadastrado.");
            return false;
        }
        User user = userOptional.get();
        if (user.lendedBooks.isEmpty()) {
            showNotReturnBookMessage("Usuário não tem nenhum livro emprestado.");
            return false;
        }

        Optional<Book> bookOptional = user.lendedBooks.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();

        if (bookOptional.isEmpty()) {
            showNotReturnBookMessage("Livro não emprestado para o usuário.");
            return false;
        }

        Book book = bookOptional.get();

        if (book.isAvailable()) {
            showNotReturnBookMessage("Livro já foi devolvido antes.");
            return false;
        }

        book.returnBook();
        System.out.println("Livro devolvido.");
        return true;
    }

    public void showAvailableBooks() {
        System.out.println("----- Livros disponíveis para empréstimo -----");
        for (Book book : bookList) {
            if (book.isAvailable()) {
                book.showDetails();
                System.out.println(" ");
            }
        }
    }

    public void showLibraryBooks() {
        System.out.println("\n----- Acervo da Biblioteca -----\n");
        for (Book book : bookList) {
            book.showDetails();
            System.out.println(" ");
        }
    }

    public void showUserListBook(int userId) {
        Optional<User> userOptional = searchUserById(userId);
        if (userOptional.isEmpty()) {
            System.out.println("Usuário não cadastrado.");
            return;
        }
        System.out.println("\n----- Livros já emprestados para o usuário -----\n");
        userOptional.get().showDetails();
    }

    public void showUsers(){
        System.out.println("\n----- Usuários da Biblioteca -----\n");
        for (User user : userList)
            user.showUser();
    }

    private Optional<Book> searchBookByIsbn(String isbn) {
        return this.bookList.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    private Optional<User> searchUserById(int id) {
        return this.userList.stream()
                .filter(user -> (user.getId() == id))
                .findFirst();
    }

    private void showNotLendBookMessage(String message) {
        System.out.println("Não foi possível realizar o empréstimo - " + message);
    }

    private void showNotReturnBookMessage(String message) {
        System.out.println("Não foi possível devolver o livro - " + message);
    }

    private boolean isUserAvailable(User user) {
        int count = 0;
        for (Book book : user.lendedBooks) {
            if (!book.isAvailable())
                count++;
        }
        return count < 3;
    }
}
