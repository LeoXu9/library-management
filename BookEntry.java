import java.lang.String;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable class encapsulating data for a single book entry.
 */

public class BookEntry {
    /** a private instance field to represent title of the book. */
    private String title;
    /** a private instance field to represent authors of the book. */
    private String[] authors;
    /** a private instance field to represent rating of the book. */
    private float rating;
    /** a private instance field to represent ISBN of the book. */
    private String ISBN;
    /** a private instance field to represent pages of the book. */
    private int pages;

    /** A construction method without the parameters
     */
    public BookEntry() {
    }


    /** Construction method of BookEntry that instantiates the five variables and also
     *
     * @param title title of the book
     * @param authors authors of the book
     * @param rating rating of the book
     * @param ISBN ISBN of the book
     * @param pages pages of the book
     */
    public BookEntry(String title, String[] authors, float rating, String ISBN, int pages) {
        //if the title is empty then print an error message.
        if (title.isEmpty()){
            System.err.println("title must not be null.");
        }
        this.title = title;

        //if the authors are empty then print an error message.
        if (isEmptyStringArray(authors)){
            System.err.println("authors must not be null.");
        }
        this.authors = authors;

        //if the rating is out of bound then print an error message.
        if (rating < 0 || rating > 5) {
            System.err.println(rating + " should be between 0 and 5.");
        }
        this.rating = rating;

        //if the ISBN is empty then print an error message.
        if (ISBN.isEmpty()){
            System.err.println("ISBN must not be null.");
        }
        this.ISBN = ISBN;

        //if the pages are negative then print an error message.
        if (pages < 0) {
            System.err.println(pages + " should be positive.");
        }
        this.pages = pages;
    }


    /** Method for getting the title of the book
     *
     * @return title of the book
     */
    public String getTitle() {
        return title;
    }

    /** Method for getting the authors of the book
     *
     * @return authors of the book
     */
    public String[] getAuthors() {
        return authors;
    }

    /** Method for getting the rating of the book
     *
     * @return rating of the book
     */
    public float getRating() {
        return rating;
    }

    /** Method for getting the ISBN of the book
     *
     * @return ISBN of the book
     */
    public String getISBN() {
        return ISBN;
    }

    /** Method for getting the pages of the book
     *
     * @return pages of the book
     */
    public int getPages() {
        return pages;
    }

    /**
     * get the first author in the string array.
     * @return
     */
    public String getFirstAuthor(){
        return authors[0];
    }
    /** Required format of toString method.
     *
     * @return the kind of format which is asked in the papers
     */


    @Override
    public String toString() {
        return title + "\n" +
                "by " + replacement(authors) + "\n" +
                "Rating: " + df.format(getRating()) + "\n" +
                "ISBN: " + ISBN + "\n" +
                pages + " pages";
    }

    /**
     * Reconstruct the format of authors for toString.
     * @param myArrayList String array of the authors of the book
     * @return a string without brackets
     */
    public static String replacement (String[] myArrayList) {
        return Arrays.toString(myArrayList)
                .replace("[", "")  //remove the left bracket
                .replace("]", ""); // remove the right bracket

    }

    /**
     * Turns rating into 2dp.
     */
    private static final DecimalFormat df = new DecimalFormat("0.00");
    /**
     * Helping method for the construction method.
     *
     * @param array authors is a string array
     * @return whether every element of the array is empty
     */
    public boolean isEmptyStringArray(String [] array){
        for (String s : array) {
            if (s != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Some initial instance checking first and then compare all five instance fields with the given parameter.
     *
     * @param o  a new type Object
     * @return true if all five instance fields are equal, false otherwise
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntry)) return false;
        BookEntry bookEntry = (BookEntry) o;
        return Float.compare(bookEntry.getRating(), getRating()) == 0 &&
                getPages() == bookEntry.getPages() &&
                Objects.equals(getTitle(), bookEntry.getTitle()) &&
                Arrays.equals(getAuthors(), bookEntry.getAuthors()) &&
                Objects.equals(getISBN(), bookEntry.getISBN());
    }

    /**
     * Generates a hash code from all five instance fields.
     *
     * @return a hash code with int type
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(getTitle(), getRating(), getISBN(), getPages());
        result = 31 * result + Arrays.hashCode(getAuthors());
        return result;
    }

}