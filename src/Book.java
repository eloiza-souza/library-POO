public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void showDetails(){
        System.out.println("Título: " + getTitle());
        System.out.println("Autor: " + getAuthor());
        System.out.println("ISBN: " + getIsbn());
    }

    public void showStatus(){
        System.out.println("Status: " + (isAvailable?"Disponível":"Emprestado"));
    }

    public void lend(){
        this.isAvailable = false;
    }

    public void returnBook(){
        this.isAvailable = true;
    }
}
