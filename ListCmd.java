import java.util.List;

/**
 * This class should allow the user to display all books currently loaded into the library in different
 * formats(short or long).
 */
public class ListCmd extends LibraryCommand {
    /**
     * Execute the specific command.
     * <p>
     * Subclasses must override this method to specify corresponding behaviour.
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        try{
            List<BookEntry> strList;
            strList = data.getBookData();
            int j = strList.size();
            System.out.println(j + " books in library:");
            for (BookEntry bookEntry : strList) {//Iterate through and print each book entry to the console
                if (ListCmd.shortOrLong.equals("short")) {// only print each book’s title
                    System.out.println(bookEntry.getTitle());
                }
                if (ListCmd.shortOrLong.equals("long")) {//print all information in the format specified for BookEntries toString method
                    System.out.println(bookEntry.toString());
                    System.out.println();//print a new line
                }
            }
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected by the extending subclass.
     */
    public ListCmd(String argumentInput) {
        super(CommandType.LIST, argumentInput);
    }

    /**
     * String to remember the input for further use(either short or long).
     */
    public static String shortOrLong;
    /**
     * Parses the given command arguments and initialised necessary
     * parameters. In this default version, a blank argument is expected.
     * <p>
     * Subclasses should override this method for more specific argument
     * parsing.
     *
     * @param argumentInput argument input for this command
     * @return true if the given argument is blank, false otherwise
     * @throws NullPointerException if the given argumentInput is null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        if (super.parseArguments(argumentInput)){
            shortOrLong = "short";//short by default
            return true;
        }
        else if (!super.parseArguments(argumentInput) && argumentInput.equals("short")){
            shortOrLong = "short";//remembers argument as a string for further use
            return true;
        }
        else if (!super.parseArguments(argumentInput) && argumentInput.equals("long")){
            shortOrLong = "long";//remembers argument as a string for further use
            return true;
        }
        return false;
    }
}
