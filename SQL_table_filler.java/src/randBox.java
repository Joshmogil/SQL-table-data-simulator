import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class randBox {

    public String randomTime(String timeRange){
        //takes in form of 11:11:00to17:00:01/hmsAM

        List<String> beginRange0EndRangeAndForm1= deserialize(timeRange,"to");

        String time1 = beginRange0EndRangeAndForm1.get(0);

        String timeForm = beginRange0EndRangeAndForm1.get(1);

        List<String> endRange0Form1= deserialize(timeForm,"/");

        String time2 = endRange0Form1.get(0);




        List<String> time1Deser =deserialize(time1,":");
        List<String> time2Deser =deserialize(time2,":");

        List<Integer> timeListIntegerMin =  new ArrayList<>();
        List<Integer> timeListIntegerMax =  new ArrayList<>();

        for(String s : time1Deser)timeListIntegerMin.add(Integer.valueOf(s));
        for(String s : time2Deser)timeListIntegerMax.add(Integer.valueOf(s));

        long minTimeCode = timeListIntegerMin.get(0)*60*60 + timeListIntegerMin.get(1)*60 + timeListIntegerMin.get(2);
        long maxTimeCode = timeListIntegerMax.get(0)*60*60 + timeListIntegerMax.get(1)*60 + timeListIntegerMax.get(2);

        int randomTimeCode = Integer.parseInt(randomNumberGenerator((String.valueOf(minTimeCode)),(String.valueOf(maxTimeCode))));

        int hours = (randomTimeCode/(60*60));
        randomTimeCode-= hours*60*60;
        int minutes = (randomTimeCode/(60));
        randomTimeCode-= minutes*60;
        int seconds = randomTimeCode;

        String amOrPm ="";
        if(timeRange.toUpperCase().contains("PM")||timeRange.toUpperCase().contains("AM")){
            if(hours>11){
                amOrPm = "pm";
            }else{
                amOrPm ="am";
            }

            if (hours>12) {
                hours = hours % 12;
            }
        }

        String hoursf = String.valueOf(hours);
        String minutesf = String.valueOf(minutes);
        String secondsf = String.valueOf(seconds);

        if (hoursf.length()==1){
            hoursf = "0"+ hoursf;
        }
        if (minutesf.length()==1){
            minutesf = "0"+ minutesf;
        }
        if (secondsf.length()==1){
            secondsf = "0"+ secondsf;
        }

        //HMS detector
        String timeOut ="";
        if(timeRange.toUpperCase().contains("H")){
            timeOut+=hoursf+":";
        }
        if(timeRange.toUpperCase().contains("M")){
            timeOut+=minutesf+":";
        }
        if(timeRange.toUpperCase().contains("S")){
            timeOut+=secondsf+":";
        }

        timeOut = timeOut.substring(0,timeOut.length()-1);

        if(amOrPm=="") {
            return timeOut;
        }else{
            return timeOut+amOrPm;
        }

    }

    public String randomDate(String dateRange){

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

    public String trueOrFalse(String tOrfOrtf){

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

    public String randomNumberMachine(String numtonum){

        List<String> components = Arrays.asList(numtonum.split("to"));

        //format detector
        String format = "";
        List<String> formatAndC2 = new ArrayList<>();

        try {
            formatAndC2 = Arrays.asList(components.get(1).split("form"));
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

    public String randomNumberGenerator(String min, String max){
        double range = Double.parseDouble(max) - Double.parseDouble(min) + 1;

        double rand = (double) (Math.random() * range) + Double.parseDouble(min);

        DecimalFormat df = new DecimalFormat("#");

        String formatted = df.format(rand);

        return formatted;
    }

    public String randomNameGenerator(int length) {

        List<String> seeds = new ArrayList<>();
        seeds.add("lorem");
        seeds.add("ipsum");
        seeds.add("dolor");
        seeds.add("sit");
        seeds.add("amet");
        seeds.add("consectetur");
        seeds.add("elit");
        seeds.add("aulus");
        seeds.add("sed");
        seeds.add("tempor");
        seeds.add("do");
        seeds.add("ut");
        seeds.add("magna");
        seeds.add("aliqua");
        seeds.add("enim");
        seeds.add("omnis");
        seeds.add("nemo");
        seeds.add("qui");
        seeds.add("minima");
        seeds.add("ullam");
        seeds.add("modi");

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

    public String randomEmailGenerator(int length){
        String emailName = randomNameGenerator(length);
        String emailSuffix = "@gmail.com";
        double fourSidedCoin = Math.random();

        if (fourSidedCoin < .25){
            emailSuffix = "@hotmail.com";
        }
        if (fourSidedCoin >= .25 && fourSidedCoin<.50){
            emailSuffix = "@yahoo.com";
        }
        if (fourSidedCoin >= .50 && fourSidedCoin<.75){
            emailSuffix = "@gmail.com";
        }
        if (fourSidedCoin >= .75 && fourSidedCoin<1.01){
            emailSuffix = "@outlook.com";
        }

        return emailName + emailSuffix;
    }

    public List<String> deserialize(String serializedString, String regex){

        return Arrays.asList(serializedString.split(regex));

    }

}
