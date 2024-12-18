import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int id;
    List<Book> lendBooks;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.lendBooks = new ArrayList<>();
    }

    public List<Book> getLendBooks() {
        return lendBooks;
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
    }

    public void addBook(Book book){
        this.lendBooks.add(book);
    }

    public void removeBook(Book book){
        this.lendBooks.remove(book);
    }
}
