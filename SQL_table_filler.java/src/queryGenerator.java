import javax.print.DocFlavor;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class queryGenerator {


    public queryGenerator() {

    }

    public static void generateQuery(String queryTable, String queryColumns, String queryColumnTypes, String queryColumnRanges, Integer rows, String dotSqlFile, Boolean replacesData) {

        boolean printsQueries = true;

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

        if(columnType.equalsIgnoreCase("word")){

           randomValue = randomNameGenerator(Integer.parseInt(columnRange));

        }

        else if(columnType.equalsIgnoreCase("number")){

            randomValue = randomNumberMachine(columnRange);

        }

        else if(columnType.equalsIgnoreCase("bool")){

            randomValue = trueOrFalse(columnRange);

        }

        else if(columnType.equalsIgnoreCase("date")){

            randomValue = randomDate(columnRange);


        }else{
            System.out.println("Error: Column type not supported, '"+ columnType +"' must match exactly word,number,bool, or date");
            System.exit(1);
        }

        return randomValue;

    }
    //System.out.println(queryGenerator.randomDate("10/15/2005to10/15/2021:mm/yyyy/dd"));
    private static String randomDate(String dateRange){

        List<String> beginRange0EndRange1= deserialize(dateRange,"to");

        String date1 = beginRange0EndRange1.get(0);
        String date2 = beginRange0EndRange1.get(1);

        List<String> date1Deser =deserialize(date1,"/");
        List<String> date2Deser =deserialize(date2,"/");

        String randDatePos1 = randomNumberGenerator(date1Deser.get(0),date2Deser.get(0));
        String randDatePos2 = randomNumberGenerator(date1Deser.get(1),date2Deser.get(1));
        String randDatePos3 = randomNumberGenerator(date1Deser.get(2),date2Deser.get(2));

        return randDatePos1 + "/" + randDatePos2 + "/" + randDatePos3;

    }


    private static String trueOrFalse(String tOrfOrtf){

        String trueOrFalse = "";

        if (tOrfOrtf.equalsIgnoreCase("t")){
            trueOrFalse = "true";
        }
        if (tOrfOrtf.equalsIgnoreCase("f")){
            trueOrFalse = "false";
        }
        if (tOrfOrtf.equalsIgnoreCase("tf")){
            trueOrFalse = Math.random() > .50 ? "true" : "false";
        }

        return trueOrFalse;
    }

    private static String randomNumberMachine(String numtonum){

        List<String> components = Arrays.asList(numtonum.split("to"));

        //format detector
            String format = "";
        List<String> formatAndC2 = new ArrayList<>();

        try {
            formatAndC2 = Arrays.asList(components.get(1).split(":"));
            format = formatAndC2.get(1);
        }catch(ArrayIndexOutOfBoundsException e){

        }

        DecimalFormat df = new DecimalFormat(format);

        //some component logic

        double max = Double.parseDouble(formatAndC2.get(0));
        double min = Double.parseDouble(components.get(0));
        double range = max - min + 1;


        double rand = (double) (Math.random() * range) + min;


        String formatted = df.format(rand);

        return formatted;
    }

    private static String randomNumberGenerator(String min, String max){
        double range = Double.parseDouble(max) - Double.parseDouble(min) + 1;

        double rand = (double) (Math.random() * range) + Double.parseDouble(min);

        DecimalFormat df = new DecimalFormat("#");

        String formatted = df.format(rand);

        return formatted;
    }

    private static String randomNameGenerator(int length) {

        List<String> seeds = new ArrayList<>();
        seeds.add("augustus");
        seeds.add("tiberius");
        seeds.add("caligula");
        seeds.add("claudius");
        seeds.add("nero");
        seeds.add("galba");
        seeds.add("otho");
        seeds.add("aulus");
        seeds.add("vespasian");
        seeds.add("titus");
        seeds.add("domitian");
        seeds.add("nerva");
        seeds.add("trajan");
        seeds.add("hadrian");
        seeds.add("marcus");
        seeds.add("lucius");
        seeds.add("commodus");
        seeds.add("publius");
        seeds.add("caracalla");
        seeds.add("macrinus");
        seeds.add("gordian");

        String randomName = "";

        while(randomName.length() < length) {

            int range = seeds.size() - 1;

            int rand = (int) (Math.random() * range) + 1;

            randomName = randomName + seeds.get(rand);

        }

        String upperFirst = randomName.substring(0,1).toUpperCase();
        String withUpper = upperFirst + randomName.substring(1,randomName.length()-1);

        randomName = withUpper.substring(0,length-1);

        return randomName;


    }

    private static List<String> deserialize(String serializedString, String regex){

        return Arrays.asList(serializedString.split(regex));

    }

}
