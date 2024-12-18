import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int id;
    List<Book> lendedBooks;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.lendedBooks = new ArrayList<>();
    }

    public List<Book> getLendedBooks() {
        return lendedBooks;
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
        showLendedBooks();
    }

    public void addBook(Book book){
        this.lendedBooks.add(book);
    }

    private void showLendedBooks(){
        for(Book book: this.getLendedBooks()){
            book.showDetails();
            System.out.println(" ");
        }
    }
}
