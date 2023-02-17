import java.util.List;

/**
 * a class that allows the user to remove specific books from the library
 */
public class RemoveCmd extends LibraryCommand{
    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected by the extending subclass.
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if any of the given parameters are null.
     */
    public RemoveCmd(String argumentInput) {
        super(CommandType.REMOVE, argumentInput);
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
        List<BookEntry> strList;
        strList = data.getBookData();
        int size = strList.size();//original size of the list
        //go through all book entries in the library and remove any matches
        //with the given remove value
        strList.removeIf(o -> String.valueOf(o).contains(rememberThis));
        int size2 = strList.size();//size of the list after remova
        int count = size - size2;//difference between the sizes of lists before and after removal
        if(size2!=size && authorOrTitle.equals("AUTHOR") ){//if the sizes are different and have author as parameter
            System.out.println(count+" books removed for author: "+rememberThis);//prints out message
        }

        else if(size2!=size && authorOrTitle.equals("TITLE") ){//if the sizes are different and have title as parameter
            System.out.println(rememberThis + ": removed successfully.");//prints out message
        }
        else if(size2 == size && authorOrTitle.equals("AUTHOR")){//if no books are removed and have author as parameter
            System.out.println(count+" books removed for author: "+rememberThis);//prints out message
        }
        else{//if no books are removed and have title as parameter
            System.out.println(rememberThis + ": not found.");//prints out message
        }

    }

    /**
     * Parses the given command arguments and initialised necessary
     * parameters. In this default version, a blank argument is expected.
     * <p>
     * Subclasses should override this method for more specific argument
     * parsing.
     * Return true if the argument is valid and false otherwise. A valid
     * argument is either AUTHOR or TITLE followed by a string value.
     * @param argumentInput argument input for this command
     * @return true if the given argument is blank, false otherwise
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        if (super.parseArguments(argumentInput)) {
            return false;
        // checks if the argument input starts with legal parameters
        } else if (((argumentInput.startsWith("AUTHOR") || argumentInput.startsWith("TITLE"))) &&!SecondWord(argumentInput).isBlank()) {
            rememberThis = SecondWord(argumentInput);//indicates a value which is either a full title or full author name
            authorOrTitle = FirstWord(argumentInput);//indicates which parameter should be considered for removal
            return true;//Return true if argument is either AUTHOR or TITLE followed by a string value
        }
        return false;//return false otherwise
    }

    public static String authorOrTitle;//remembers the parameter for further use
    public static String rememberThis;//remembers the value for further use

    public static String SecondWord(String x) {

        //Finds the position of the first space, adds one to it to find the position of the first character of the second word
        int pos = x.indexOf(" ") + 1;

        int pos1 = x.length();//finds the last index of the string

        return x.substring(pos, pos1);//extracts the second to the last words of the string
    }
    public static String FirstWord(String x) {

        String[] arr = x.split(" ");//split the the string into a string array

        return arr[0];//return the first element of the string array
    }
}
