package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private static int idGenerator;
    private String name;
    private int id;
    public List<Book> lendedBooks;

    public User(String name) {
        this.name = name;
        this.id = ++idGenerator;
        this.lendedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void showDetails(){

        System.out.println("ID " + getId() + " - Nome: " + getName());
        this.showLendedBooks();
    }

    public void addBook(Book book){
        this.lendedBooks.add(book);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    private void showLendedBooks(){
        for(Book book: this.lendedBooks){
            book.showDetails();
            System.out.println(" ");
        }
    }
}
