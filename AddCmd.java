import java.nio.file.Paths;

/**
 * a class that populates the database with data from a given csv file.
 */
public class AddCmd extends LibraryCommand{
    /**
     * Create the specified command and initialise it with
     * the given command argument.
     *
     * @param argumentInput argument input as expected by the extending subclass.
     */
    public AddCmd(String argumentInput) {
        super(CommandType.ADD, argumentInput);
    }

    /**
     * String to remember the path of the book for further use.
     */
    public static String BookPath;

    /**
     * Execute the specific command.
     * <p>
     * Subclasses must override this method to specify corresponding behaviour.
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        if(data == null){
            throw new NullPointerException();
        }
        else{
            data.loadData(Paths.get(BookPath));
        }

    }

    /**
     * Parses the given command arguments and initialised necessary
     * parameters. In this default version, a blank argument is expected.
     * <p>
     * Subclasses should override this method for more specific argument
     * parsing.
     * Checks if the argument is a valid path that indicates a file name which ends with .csv.
     * @param argumentInput argument input for this command
     * @return true if the given argument is blank, false otherwise
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        if(super.parseArguments(argumentInput)){
            return false;
        }
        else if(argumentInput.endsWith(".csv")){
            BookPath = argumentInput;//remembers the given argument as a string
            return true;
        }
        else{
            return false;
        }
    }
}
