package controller;

import model.MenuOptionsEnum;
import model.Book;
import model.User;
import service.LibraryService;
import util.BookFileUtil;
import util.UserFileUtil;

import java.util.List;
import java.util.Scanner;

public class LibraryController {
    private LibraryService library;
    final int END = MenuOptionsEnum.values().length + 1;

    public LibraryController() {
        this.library = new LibraryService(BookFileUtil.loadBooksFromFile(), UserFileUtil.loadUsersFromFile());
    }

    public void librarySystem() {
        System.out.println("\n*** Bem vindo ao Sistema de Gerenciamento de Biblioteca ***\n");
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

                MenuOptionsEnum menuOption = MenuOptionsEnum.values()[option - 1];

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
                    case LIBRARY_BOOKS:
                        library.showLibraryBooks();
                        break;
                    case AVAILABLE_BOOKS:
                        library.showAvailableBooks();
                        break;
                    case BOOKS_USER:
                        showBooksUser(scanner);
                        break;
                    case LIBRARY_USERS:
                        library.showUsers();
                        break;
                    default:
                        break;
                }


            } while (option != END);

            BookFileUtil.saveBooksToFile(library.getBookList());
            UserFileUtil.saveUsersToFile(library.getUserList());
            System.out.println("\n----Programa encerrado----");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showBooksUser(Scanner scanner) {
        library.showUserListBook(readInt(scanner, "Id do usuário: "));
    }

    private void returnBook(Scanner scanner){
        System.out.println("\n-> Devolução de livro");
        library.returnBook(readString(scanner, "ISBN do livro: "), readInt(scanner, "Id do usuário: "));
    }

    private void leadBook(Scanner scanner) {
        System.out.println("\n-> Empréstimo de livro");
        library.lendBook(readString(scanner, "ISBN do livro: "), readInt(scanner, "Id do usuário: "));
    }

    private void userRegister(Scanner scanner) {
        System.out.println("\n-> Cadastro de usuário");
        User user = createUser(scanner);
        library.registerNewUser(user);
    }

    private User createUser(Scanner scanner) {
        return new User(readString(scanner, "Nome: "));
    }

    private void bookRegister(Scanner scanner) {
        System.out.println("\n-> Cadastro de livro");
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

        System.out.println("\nEscolha uma opção:");
        int index = 1;
        for (MenuOptionsEnum option : MenuOptionsEnum.values()) {
            System.out.println(index++ + ". " + option.getDescription());
        }
        System.out.println(END + ". Sair");
    }


}

