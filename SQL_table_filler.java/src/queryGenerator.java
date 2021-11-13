import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class queryGenerator {

    public queryGenerator() {

    }

    public static void generateQuery(String queryTable, String queryColumns, String queryColumnTypes, String queryColumnRanges, Integer rows, String dotSqlFile, Boolean replacesData) {

        boolean printsQueries = false;

        filePrinter sqlFile = new filePrinter(dotSqlFile);

        String table = queryTable;
        String columns = queryColumns;
        Integer numberOfRows = rows;
        if(replacesData){

            sqlFile.writeQuery(table,"DELETE FROM " + table +";");
        }


        for (int i  = 0;i<rows;i++){
            String rowData = buildRowData(queryColumnTypes,queryColumnRanges);
            sqlFile.writeQuery(table,columns,rowData);

            if(printsQueries){
                System.out.println("INSERT INTO " + table + " (" + columns+ ")");
                System.out.println("VALUES (" + rowData.substring(0,rowData.length()-2) + ");");
                System.out.println();
            }

        }

    }

    public static String buildRowData(String queryColumnTypes, String queryColumnRanges){

        List<String> columns = deserialize(queryColumnTypes,",");
        List<String> columnRange = deserialize(queryColumnRanges,",");

        List<String> randomRowData = new ArrayList<>();

        String randRowData = "";

        for(int i = 0;i<columns.size();i++){
            randomRowData.add(generateRandomValue(columns.get(i), columnRange.get(i)));
            randRowData += "'" + generateRandomValue(columns.get(i), columnRange.get(i)) +"', ";
        }

        return randRowData;
    }

    private static String generateRandomValue(String columnType,String columnRange){

        String randomValue = " ";
        //dependency injection
        randBox randBox1 = new randBox();

        if(columnType.equalsIgnoreCase("word")){

           randomValue = randBox1.randomNameGenerator(Integer.parseInt(columnRange));

        }

        else if(columnType.equalsIgnoreCase("number")){

            randomValue = randBox1.randomNumberMachine(columnRange);

        }

        else if(columnType.equalsIgnoreCase("bool")){

            randomValue = randBox1.trueOrFalse(columnRange);

        }

        else if(columnType.equalsIgnoreCase("date")) {

            randomValue = randBox1.randomDate(columnRange);


        }else if(columnType.equalsIgnoreCase("time")){

                randomValue = randBox1.randomTime(columnRange);


        }else{
            System.out.println("Error: Column type not supported, '"+ columnType +"' must match exactly word,number,bool, or date");
            System.exit(1);
        }

        return randomValue;

    }

    public static List<String> deserialize(String serializedString, String regex){

        return Arrays.asList(serializedString.split(regex));

    }

}
