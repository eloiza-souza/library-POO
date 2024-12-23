package util;

import model.Book;

import java.util.ArrayList;
import java.util.List;

import static util.FileUtil.SEPARATOR;

public class BookFileUtil implements FileUtil<Book> {

    @Override
    public List<String> convertToListStrings(List<Book> list) {
        List<String> listString = new ArrayList<>();
        for (Book book : list) {
            listString.add(book.getTitle() + SEPARATOR +
                    book.getAuthor() + SEPARATOR +
                    book.getIsbn() + SEPARATOR +
                    book.isAvailable());
        }
        return listString;
    }

    @Override
    public List<Book> convertToListT(List<String> stringsList) {
        List<Book> listBooks = new ArrayList<>();
        for (String string : stringsList) {
            String[] stringSplit = string.split(SEPARATOR);
            if (stringSplit.length == 4) {
                Book book = new Book(stringSplit[0], stringSplit[1], stringSplit[2]);
                if (stringSplit[3].equalsIgnoreCase("false")) {
                    book.lend();
                }
                listBooks.add(book);
            }
        }
        return listBooks;
    }
}
