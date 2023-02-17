import java.util.List;
import java.lang.*;

/**
 *  a class that allows the user to search for specific books within the library and display them.
 */
public class SearchCmd extends LibraryCommand {
    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected by the extending subclass.
     */
    public SearchCmd(String argumentInput) {
        super(CommandType.SEARCH, argumentInput);
    }

    /**
     * Execute the specific command.
     * <p>
     * Subclasses must override this method to specify corresponding behaviour.
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        List<BookEntry> strList;//new book entry list
        strList = data.getBookData();//get the data input
        String[] myStringArray = new String[strList.size()];//makes a string array that contains all the titles
        for (int i = 0; i < strList.size(); i++) {
            myStringArray[i] = strList.get(i).getTitle();
        }
        boolean doesContain = false;//initiate a boolean value that is false originally
        for (String s : myStringArray) {
            if (containsIgnoreCase(s, rememberArgument)) {//use of the helper method
                System.out.println(s);//prints out the title
                doesContain = true;//boolean value is true if input search value is contained in any of the book titles
            }
        }
            if(!doesContain){//if input search value is not contained in any of the book titles
                System.out.println("No hits found for search term: " + rememberArgument);//prints out corresponding message
        }
    }

    /**
     * method that determines if the larger string contains the smaller string ignoring cases.
     * @param str a larger string
     * @param subString a smaller string
     * @return true if the larger string contains the smaller string ignoring cases.
     */
    public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());//all converted to lower cases so that cases are ignored.
    }

    /**
     * String to remember the argument for further use.
     */
    public static String rememberArgument;

    /**
     * Parses the given command arguments and initialised necessary
     * parameters. In this default version, a blank argument is expected.
     * <p>
     * Subclasses should override this method for more specific argument
     * parsing.
     * Return true if the argument is valid(single word).
     * @param argumentInput argument input for this command
     * @return true if the given argument is blank, false otherwise
     * @throws NullPointerException if the given argumentInput is null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {

        if(super.parseArguments(argumentInput)){
            return false;
        }
        else if(singleWord(argumentInput)){
            rememberArgument = argumentInput;//remembers the input for further use.
            return true;//Return true if the argument is valid(single word).
        }
        else{
            return false;
        }
    }

    /**
     * method that determines if the input value is a single word.
     * @param s input value
     * @return true if the input value is a single word
     */
    public static boolean singleWord(String s) {
        return (s.length() > 0 && s.split("\\s+").length == 1); //splits the string by white spaces and checks its length
    }
}
