package controller;

import model.Book;
import model.MenuOptionsEnum;
import model.User;
import service.LibraryService;
import util.BookFileUtil;
import util.ScannerUtil;
import util.UserFileUtil;

import java.util.Scanner;

public class LibraryController {
    private LibraryService library;

    private BookFileUtil bookFileUtil;
    private UserFileUtil userFileUtil;

    final int END = MenuOptionsEnum.values().length + 1;
    final static String FILE_NAME_BOOK = "booksFile";
    final static  String FILE_NAME_USER = "usersFile";


    public LibraryController() {
        this.userFileUtil = new UserFileUtil();
        this.bookFileUtil = new BookFileUtil();

        this.library = new LibraryService(bookFileUtil.loadFromFile(FILE_NAME_BOOK),
                                          userFileUtil.loadFromFile(FILE_NAME_USER));
    }

    public void librarySystem() {
        System.out.println("\n*** Bem vindo ao Sistema de Gerenciamento de Biblioteca ***\n");
        int option = 0;
        try (Scanner scanner = new Scanner(System.in)) {

            do {
                showPrincipalMenu();
                option = ScannerUtil.readInt(scanner, "Opção: ");

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
                        bookRegisterController(scanner);
                        break;
                    case USER_REGISTER:
                        userRegisterController(scanner);
                        break;
                    case LEAD_BOOK:
                        lendBookController(scanner);
                        break;
                    case RETURN_BOOK:
                        returnBookController(scanner);
                        break;
                    case LIBRARY_BOOKS:
                        library.showLibraryBooks();
                        break;
                    case AVAILABLE_BOOKS:
                        library.showAvailableBooks();
                        break;
                    case BOOKS_USER:
                        showBooksUserController(scanner);
                        break;
                    case LIBRARY_USERS:
                        library.showUsers();
                        break;
                    default:
                        break;
                }
            } while (option != END);

            bookFileUtil.saveToFile(library.getBookList(),FILE_NAME_BOOK);
            userFileUtil.saveToFile(library.getUserList(),FILE_NAME_USER);
            System.out.println("\n----Programa encerrado----");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showBooksUserController(Scanner scanner) {
        library.showUserListBook(ScannerUtil.readInt(scanner, "Id do usuário: "));
    }

    private void returnBookController(Scanner scanner){
        System.out.println("\n-> Devolução de livro");
        library.returnBook(ScannerUtil.readString(scanner, "ISBN do livro: "),
                ScannerUtil.readInt(scanner, "Id do usuário: "));
    }

    private void lendBookController(Scanner scanner) {
        System.out.println("\n-> Empréstimo de livro");
        library.lendBook(ScannerUtil.readString(scanner, "ISBN do livro: "),
                ScannerUtil.readInt(scanner, "Id do usuário: "));
    }

    private void userRegisterController(Scanner scanner) {
        System.out.println("\n-> Cadastro de usuário");
        User user = createUser(scanner);
        library.registerNewUser(user);
    }

    private User createUser(Scanner scanner) {
        return new User(ScannerUtil.readString(scanner, "Nome: "));
    }

    private void bookRegisterController(Scanner scanner) {
        System.out.println("\n-> Cadastro de livro");
        Book book = createBook(scanner);
        library.registerNewBook(book);
    }

    private Book createBook(Scanner scanner) {
        return new Book(ScannerUtil.readString(scanner, "Título: "),
                ScannerUtil.readString(scanner, "Autor: "),
                ScannerUtil.readString(scanner, "ISBN: "));
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

