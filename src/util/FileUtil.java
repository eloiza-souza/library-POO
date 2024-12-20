package util;

import model.Book;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public final static String SEPARATOR = ";";

    public static void saveStringObjectsToFile(List<String> objectsList, String fileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String objectString : objectsList) {
                writer.write(objectString + SEPARATOR);
                writer.newLine();
            }
            System.out.println("Dados salvos com sucesso no arquivo: " + fileName);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static List<String> loadStringObjectsFromFile(String fileName) {
        List<String> objectsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null)
                    objectsList.add(line);
            System.out.println("Dados carregados com sucesso do arquivo: " + fileName);
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados: " + e.getMessage());
        }
        return objectsList;
    }
}
