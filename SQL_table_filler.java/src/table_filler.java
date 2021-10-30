public class table_filler{

//this application takes an example INSERT query, its columns, types and ranges,
// and fills a .sql file with data dummy to easily fill up a table
    public static void main(String[] args) {

        //MODIFY THIS
        String dotSqlFile ="account_dummy_data.sql";
        boolean replacesData = true;

        String Table = "account"; //insert into this table
        String Columns ="username,first_name,last_name,birthday,favorite_number"; //in these columns
        String ColumnTypes ="word,word,word,date,number";
        String ColumnRanges ="12,12,12,01/01/1935to12/31/2003,1to100:#";

        //ColumnRanges formatting guide
        //word = length
        //number = number range and format like -100.67to200.800:#.###
        //bool = tf, t,f
        //date = any format date range like MM/DD/YYYYtoMM/DD/YYYY

        Integer numberOfRows = 200; //Took my machine ~ 30s to fill 200k queries

        queryGenerator.generateQuery(Table,Columns,ColumnTypes,ColumnRanges,numberOfRows,dotSqlFile,replacesData);


    }
}
