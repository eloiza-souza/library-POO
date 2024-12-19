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

        int option = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                showPrincipalMenu();
                option = readUserOption(scanner, "Opção: ", 1, END);

                if (option == END){
                    continue;
                }

                MenuOptions menuOption = MenuOptions.values()[option-1];

                switch (menuOption) {
                    case BOOK_REGISTER:
                        bookRegister(scanner);
                        break;

                }


            } while (option != END);

            System.out.println("----Programa encerrado----");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void bookRegister(Scanner scanner) {
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

    private int readUserOption(Scanner scanner, String message, int min, int max) {
        int option;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();

                if (option >= min && option <= max)
                    return option;
                else
                    System.out.println("Opção fora do intervalo. Tente novamente.");
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
            }
        }
    }

    private void showPrincipalMenu() {
        System.out.println("Bem vindo ao Sistema de Gerenciamento de Biblioteca");
        System.out.println("Escolha uma opção:");
        int index = 1;
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println(index++ + ". " + option.getDescription());
        }
        System.out.println(END + ". Sair");
    }


}

