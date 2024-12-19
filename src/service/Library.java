package service;

import model.Book;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {
    private final List<Book> bookList;
    private final List<User> userList;

    public Library() {
        this.bookList = new ArrayList<>();
        this.userList = new ArrayList<>();
    }

    public boolean registerNewBook(Book book) {
       if(bookList.contains(book)){
           System.out.println("livro já cadastrado");
           return false;
       }
        this.bookList.add(book);
        System.out.println("livro cadastrado com sucesso!");
        return true;
    }

    public boolean registerNewUser(User user) {
        if(userList.contains(user)){
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

    public boolean returnBook(String isbn, int idUser) {
        Optional<User> userOptional = searchUserById(idUser);
        if (userOptional.isEmpty()) {
            showNotReturnBook("Usuário não cadastrado.");
            return false;
        }
        User user = userOptional.get();
        if (user.lendedBooks.isEmpty()) {
            showNotReturnBook("Usuário não tem nenhum livro emprestado.");
            return false;
        }

        Optional<Book> bookOptional = user.lendedBooks.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();

        if (bookOptional.isEmpty()) {
            showNotReturnBook("Livro não emprestado para o usuário.");
            return false;
        }

        Book book = bookOptional.get();

        if (book.isAvailable()) {
            showNotReturnBook("Livro já foi devolvido antes.");
            return false;
        }

        book.returnBook();
        System.out.println("Livro devolvido.");
        return true;
    }

    public void showAvailableBooks(){
        System.out.println("----- Livros disponíveis para empréstimo -----");
        for(Book book: bookList){
            if(book.isAvailable()){
                book.showDetails();
                System.out.println(" ");
            }
        }
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
