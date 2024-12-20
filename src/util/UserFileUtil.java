package util;

import model.Book;
import model.User;

import java.util.ArrayList;
import java.util.List;

import static util.FileUtil.SEPARATOR;

public class UserFileUtil {
    final static  String FILE_NAME = "usersFile";
    final static String SEPARATOR_BOOKS = "&";
    final static String SEPARATOR_BOOK_ATRIBUTES = "/";

    public static void saveUsersToFile(List<User> UsersList) {
        FileUtil.saveStringObjectsToFile(convertListUsersToListStrings(UsersList),FILE_NAME);
    }

    public static List<User> loadUsersFromFile() {
        return convertListStringsToListUsers( FileUtil.loadStringObjectsFromFile(FILE_NAME));
    }

    private static List<String> convertListUsersToListStrings(List<User> UsersList){
        List<String> listString = new ArrayList<>();
        for (User user: UsersList){
            StringBuilder userBooks = new StringBuilder();
            for(Book book: user.lendedBooks){
                if (!userBooks.isEmpty()) {
                    userBooks.append(SEPARATOR_BOOKS);
                }
                userBooks.append(book.getTitle()).append(SEPARATOR_BOOK_ATRIBUTES)
                         .append(book.getAuthor()).append(SEPARATOR_BOOK_ATRIBUTES)
                         .append(book.getIsbn());
            }
            listString.add(user.getName() + SEPARATOR + userBooks);
        }
        return listString;
    }

    private static List<User> convertListStringsToListUsers(List<String> stringsList){
        List<User> listUsers = new ArrayList<>();
        for (String string: stringsList){
            String[] stringSplit = string.split(SEPARATOR);
            if (stringSplit.length == 2){
                User user = new User(stringSplit[0]);
                String[] stringSplitBooks = stringSplit[1].split(SEPARATOR_BOOKS);
                for(String bookString: stringSplitBooks){
                    String[] bookSplit = bookString.split(SEPARATOR_BOOK_ATRIBUTES);
                    user.addBook(new Book(bookSplit[0], bookSplit[1], bookSplit[2]));
                }
                listUsers.add(user);
            }
        }
        return listUsers;
    }
}
