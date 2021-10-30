public class table_filler{

//this application takes an example INSERT query, its columns, types and ranges,
// and fills a .sql file with data dummy to easily fill up a table
    public static void main(String[] args) {

        //MODIFY THIS
        String dotSqlFile ="change_this_output_file.sql";
        boolean replacesData = true;

        String Table = "account"; //insert into this table
        String Columns ="username,first_name,last_name,birthday,favorite_number"; //in these columns
        String ColumnTypes ="word,word,bool,date,number,number";
        String ColumnRanges ="5,15,tf,01/01/1935to12/31/2003,1to100:#,-2147483648000to2147483648000";

        //ColumnRanges formatting guide
        //word = length
        //number = number range and format like -100.67to200.800:#.###
        //bool = tf, t,f
        //date = any format date range like MM/DD/YYYYtoMM/DD/YYYY

        Integer numberOfRows = 3;

        queryGenerator.generateQuery(Table,Columns,ColumnTypes,ColumnRanges,numberOfRows,dotSqlFile,replacesData);


    }
}
