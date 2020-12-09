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

}
