package util;

import java.util.Scanner;

public final class ScannerUtil {

    private ScannerUtil(){}

    public static String readString(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();

    }

    public static int readInt(Scanner scanner, String message) {
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
}
