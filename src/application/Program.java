package application;

import enums.MenuOptions;
import model.Book;
import model.User;
import service.Library;

import java.util.Scanner;

import static enums.MenuOptions.BOOK_REGISTER;

public class Program {
    private Library library;
    final int END = MenuOptions.values().length + 1;

    public Program() {
        this.library = new Library();
    }

    public static void main(String[] args) {
        Program program = new Program();
        program.librarySystem();
    }

    public void librarySystem() {
        System.out.println("Bem vindo ao Sistema de Gerenciamento de Biblioteca");
        int option = 0;
        try (Scanner scanner = new Scanner(System.in)) {

            do {
                showPrincipalMenu();
                option = readInt(scanner, "Opção: ");

                if (option == END) {
                    continue;
                }
                if (option < 0 || option > END) {
                    System.out.println("Opção fora do intervalo. Tente novamente.");
                    continue;
                }

                MenuOptions menuOption = MenuOptions.values()[option - 1];

                switch (menuOption) {
                    case BOOK_REGISTER:
                        bookRegister(scanner);
                        break;
                    case USER_REGISTER:
                        userRegister(scanner);
                        break;
                    case LEAD_BOOK:
                        leadBook(scanner);
                        break;
                    case RETURN_BOOK:
                        returnBook(scanner);
                        break;
                }


            } while (option != END);

            System.out.println("----Programa encerrado----");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void returnBook(Scanner scanner){
        System.out.println("-> Devolução de livro");
        library.returnBook(readString(scanner, "ISBN do livro: "), readInt(scanner, "Id do usuário: "));
    }

    private void leadBook(Scanner scanner) {
        System.out.println("-> Empréstimo de livro");
        library.lendBook(readString(scanner, "ISBN do livro: "), readInt(scanner, "Id do usuário: "));
    }

    private void userRegister(Scanner scanner) {
        System.out.println("-> Cadastro de usuário");
        User user = createUser(scanner);
        library.registerNewUser(user);
    }

    private User createUser(Scanner scanner) {
        return new User(readString(scanner, "Nome: "));
    }

    private void bookRegister(Scanner scanner) {
        System.out.println("-> Cadastro de livro");
        Book book = createBook(scanner);
        library.registerNewBook(book);
    }

    private Book createBook(Scanner scanner) {
        return new Book(readString(scanner, "Título: "), readString(scanner, "Autor: "),
                readString(scanner, "ISBN: "));
    }

    private String readString(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private int readInt(Scanner scanner, String message) {
        int option;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();
                return option;
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
            }
        }
    }

    private void showPrincipalMenu() {

        System.out.println("Escolha uma opção:");
        int index = 1;
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println(index++ + ". " + option.getDescription());
        }
        System.out.println(END + ". Sair");
    }


}

