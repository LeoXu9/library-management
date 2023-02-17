import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** 
 * Class responsible for loading
 * book data from file.
 */
public class LibraryFileLoader {

    /**
     * Contains all lines read from a book data file using
     * the loadFileContent method.
     * <p>
     * This field can be null if loadFileContent was not called
     * for a valid Path yet.
     * <p>
     * NOTE: Individual line entries do not include line breaks at the
     * end of each line.
     */
    private List<String> fileContent;

    /**
     * Create a new loader. No file content has been loaded yet.
     */
    public LibraryFileLoader() {
        fileContent = null;
    }

    /**
     * Load all lines from the specified book data file and
     * save them for later parsing with the parseFileContent method.
     * <p>
     * This method has to be called before the parseFileContent method
     * can be executed successfully.
     *
     * @param fileName file path with book data
     * @return true if book data could be loaded successfully, false otherwise
     */
    public boolean loadFileContent(Path fileName) {
        Objects.requireNonNull(fileName, "Given filename must not be null.");
        boolean success = false;

        try {
            fileContent = Files.readAllLines(fileName);
            success = true;
        } catch (IOException | SecurityException e) {
            System.err.println("ERROR: Reading file content failed: " + e);
        }

        return success;
    }

    /**
     * Has file content been loaded already?
     *
     * @return true if file content has been loaded already.
     */
    public boolean contentLoaded() {
        return fileContent != null;
    }

    /**
     * Parse file content loaded previously with the loadFileContent method.
     *
     * @return books parsed from the previously loaded book data or an empty list
     * if no book data has been loaded yet.
     */
    public List<BookEntry> parseFileContent() {
        try {
            List<BookEntry> bookEntryList = new ArrayList<>();
            if (contentLoaded()) {
                for (int i = 1; i < fileContent.size(); i++) {//first line is a header so start from the second
                    String originalString = fileContent.get(i);//original string contains all the five fields of a book
                    String[] splitString = originalString.split(",");//split the string by commas
                    String splitStringName = splitString[0];//the title is the first element
                    String splitStringAuthors = splitString[1];//the authors are the second element
                    String splitStringRating = splitString[2];//the rating is the third element
                    String splitStringISBN = splitString[3];//the ISBN is the fourth element
                    String splitStringPages = splitString[4];//the pages are the fifth element

                    String[] splitAuthors = splitStringAuthors.split("-");//authors are split because there are multiple ones
                    BookEntry newBookEntry = new BookEntry(splitStringName, splitAuthors, Float.parseFloat(splitStringRating), splitStringISBN, Integer.parseInt(splitStringPages));
                    bookEntryList.add(newBookEntry);

                }
                return bookEntryList;
            } else {
                System.err.println("ERROR: No content loaded before parsing.");
                return Collections.emptyList();
            }
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException("exception");
        }

    }
}