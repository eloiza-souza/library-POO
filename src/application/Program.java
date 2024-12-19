package application;

import enums.MenuOptions;
import service.Library;

import java.util.Scanner;

public class Program {
    final int END = MenuOptions.values().length + 1;

    public static void main(String[] args) {
        Program program = new Program();
        program.librarySystem();
    }

    public void librarySystem() {

        int option = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                showPrincipalMenu();
                option = readUserOption(scanner, "Opção: ", 1,END);
            } while (option != END);

            System.out.println("----Programa encerrado----");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

