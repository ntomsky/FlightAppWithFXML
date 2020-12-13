package Helpers;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntryVerifiers {
    public static boolean isSSN_Valid(String SSN){
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(SSN);

        return matcher.find();
    }
public static int SSNtoDigits(String SSN){
    return Integer.parseInt(SSN.substring(0,3) + ""+SSN.substring(4,7) + "" + SSN.substring(8));
}

public static boolean flightNumValid(String flightNum){
    Pattern pattern = Pattern.compile("\\d+");
    Matcher matcher = pattern.matcher(flightNum);

    return matcher.find();
}
    public static boolean isTimeEntryValid(String flightTime){
        Pattern pattern = Pattern.compile("[0-2][0-9][:][0-5][0-9]");
        Matcher matcher = pattern.matcher(flightTime);

        return matcher.find();
    }

}
