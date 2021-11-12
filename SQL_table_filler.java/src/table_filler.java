import java.text.SimpleDateFormat;
import java.util.Random;

public class table_filler{

//this application takes an example INSERT query, its columns, types and ranges,
// and fills a .sql file with data dummy to easily fill up a table





    public static void main(String[] args) {

        //MODIFY THIS
        String dotSqlFile ="change_this_output_file.sql";
        boolean replacesData = true;

        String Table1 = "account"; //insert into this table
        String Columns1 ="username,first_name,last_name,birthday"; //in these columns
        String ColumnTypes1 ="word,word,word,date";
        String ColumnRanges1 ="10,12,12,01/01/1935to12/31/2003";

        //ColumnRanges formatting guide
        //word = length
        //number = number range and format like -100.67to200.801form#.###
        //bool = tf, t,f
        //date = any format date range like MM/DD/YYYYtoMM/DD/YYYY
        //time = time format like "00:00:00to23:59:59/HMSpm"

        Integer numberOfRows1 = 25;

        queryGenerator.generateQuery(Table1,Columns1,ColumnTypes1,ColumnRanges1,numberOfRows1,dotSqlFile,replacesData);



    }
}
