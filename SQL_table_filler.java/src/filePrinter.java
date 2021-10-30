import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class filePrinter {

    //Instance variables
    private final String PATH_TO_LOG_FILE;
    private final File LOG_FILE;

    //Default constructor
    public filePrinter(String pathToLogFile, File logFile) {
        this.PATH_TO_LOG_FILE = pathToLogFile;
        this.LOG_FILE = logFile;
    }
    //Overload constructor w/logic for create new log file if does not exist
    public filePrinter(String pathToLogFile) {
        this.PATH_TO_LOG_FILE = pathToLogFile;
        this.LOG_FILE = new File(pathToLogFile);

        try {
            LOG_FILE.createNewFile();

        } catch (IOException ioEx) {
            System.out.println("I/O exception");
        }
    }

    public void writeQuery(String table, String columns, String rowData) {

        //Appends above log entry to file of a new line
        try(PrintWriter writeToLogFile = new PrintWriter(new FileOutputStream(LOG_FILE,true))) {
            writeToLogFile.println("INSERT INTO " + table + " (" + columns+ ")");
            writeToLogFile.println("VALUES (" + rowData.substring(0,rowData.length()-2) + ");");
            writeToLogFile.println();
            //System.out.println("A line was printed");

        } catch (IOException ioEx) {
            System.out.println("I/O Exception");
        }


    }

    public void writeQuery(String table, String query) {

        //Appends above log entry to file of a new line
        try(PrintWriter writeToLogFile = new PrintWriter(new FileOutputStream(LOG_FILE,true))) {
            writeToLogFile.println(query);

            writeToLogFile.println();
            //System.out.println("A line was printed");

        } catch (IOException ioEx) {
            System.out.println("I/O Exception");
        }


    }


}
