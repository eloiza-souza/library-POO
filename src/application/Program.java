package application;

import enums.MenuOptions;
import service.Library;

public class Program {


    public static void main(String[] args) {
        Program program = new Program();
        program.librarySystem();
    }

    public void librarySystem(){
        showPrincipalMenu();

    }

    private void showPrincipalMenu(){
        System.out.println("Bem vindo ao Sistema de Gerenciamento de Biblioteca");
        System.out.println("Escolha uma opção:");
        int index = 1;
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println(index++ + ". " + option.getDescription());
        }
        System.out.println(index + ". Sair");
    }


}

