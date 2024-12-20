package util;

import model.Book;

import java.util.ArrayList;
import java.util.List;

import static util.FileUtil.SEPARATOR;

public class BookFileUtil {
    final static  String FILE_NAME = "books";
    public static void saveBooksToFile(List<Book> booksList) {
        FileUtil.saveStringObjectsToFile(convertListBooksToListStrings(booksList),FILE_NAME);
    }

    public static List<Book> loadBooksFromFile(String fileName) {
        return convertListStringsToListBooks( FileUtil.loadStringObjectsFromFile(FILE_NAME));
    }

    private static List<String> convertListBooksToListStrings(List<Book> booksList){
        List<String> listString = new ArrayList<>();
        for (Book book: booksList){
            listString.add(book.getIsbn()+ SEPARATOR +
                           book.getTitle() + SEPARATOR +
                           book.getAuthor() + SEPARATOR +
                           book.isAvailable());
        }
        return listString;
    }

    private static List<Book> convertListStringsToListBooks(List<String> stringsList){
        List<Book> listBooks = new ArrayList<>();
        for (String string: stringsList){
            String[] stringSplit = string.split(SEPARATOR);
            if (stringSplit.length == 4){
                Book book = new Book(stringSplit[0], stringSplit[1], stringSplit[2]);
                if(stringSplit[3].equalsIgnoreCase("false")){
                    book.lend();
                }
                listBooks.add(book);
            }
        }
        return listBooks;
    }

}
