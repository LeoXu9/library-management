import java.util.*;

/**
 * a class that allows the user to display book entries in
 * specific groups
 */
public class GroupCmd extends LibraryCommand{
    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     *
     * @param argumentInput argument input as expected by the extending subclass.
     */
    public GroupCmd(String argumentInput) {
        super(CommandType.GROUP, argumentInput);
    }

    /**
     * Execute the specific command.
     * <p>
     * Subclasses must override this method to specify corresponding behaviour.
     *
     * @param data book data to be considered for command execution.
     * this part of code does not work all correctly
     * I have trouble figuring out where the problem is
     *
     */
    @Override
    public void execute(LibraryData data) {
        List<BookEntry> strList;//new book entry list
        strList = data.getBookData();//get data from input
        if (argument.equals("TITLE")&&strList.size()!=0) {//checks the parameter and size
            System.out.println("Grouped data by TITLE");//prints out the message required
            strList.sort(Comparator.comparing(BookEntry::getTitle));//sort the list by one of its internal properties
            for (int i =0; i<strList.size(); i++) {//iterate through the sorted list
                if(i == 0){//for the first element
                    System.out.println("## " + strList.get(i).getTitle().charAt(0));//prints out the first letter indicator
                }
                else if(strList.get(i).getTitle().charAt(0)!=strList.get(i-1).getTitle().charAt(0)){//if the first character is same
                    System.out.println("## " + strList.get(i).getTitle().charAt(0));//prints out the author names
                }
                System.out.println(strList.get(i).getTitle());//prints out the titles*/
            }
        }
        else if (argument.equals("AUTHOR")&&strList.size()!=0) {//checks the parameter and size
            System.out.println("Grouped data by AUTHOR");//prints out the message required
            strList.sort(Comparator.comparing(BookEntry::getFirstAuthor));//sort the list by one of its internal properties
            for (int i =0; i<strList.size(); i++) {//iterate through the sorted list
                if(i == 0){
                    System.out.println("## " + strList.get(i).getFirstAuthor());//prints out the first author
                }
                else if(!strList.get(i).getFirstAuthor().equals(strList.get(i-1).getFirstAuthor())){
                    System.out.println("## " + strList.get(i).getFirstAuthor());//prints out the author names
                }
                System.out.println(strList.get(i).getTitle());//prints out the titles*/
            }
        }
        else if (strList.size()<1){//if the library is empty
            System.out.println("The library has no book entries.");//prints out message
        }
    }
    /**
     * Parses the given command arguments and initialised necessary
     * parameters. In this default version, a blank argument is expected.
     * <p>
     * Subclasses should override this method for more specific argument
     * parsing.
     *
     * Return true if the argument is either AUTHOR or TITLE.
     * @param argumentInput argument input for this command
     * @return true if the given argument is blank, false otherwise
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        if(super.parseArguments(argumentInput)){
            return false;
        }
        // checks if the argument input starts with legal parameters
        else if(argumentInput.equals("AUTHOR")||argumentInput.equals("TITLE")) {
            argument = argumentInput;//remembers the argument for further use
            return true;//Return true if argument is either AUTHOR or TITLE
        }
        else{
            return false;//return false otherwise
        }
    }

    /**
     * remembers the parameter of the input for further use
     */
    public static String argument;
}