public class Main {
    public static void main(String[] args) {
        Book livro1 = new Book("Primeiro livro", "Fulano de Tal", "00001");
        livro1.showDetails();
        livro1.showStatus();
        livro1.lend();
        livro1.showStatus();
        livro1.setAuthor("Fulano de Tal Alterado");
        livro1.setTitle("Primeiro livro alterado");
        livro1.returnBook();
        livro1.showDetails();
        livro1.showStatus();
    }
}