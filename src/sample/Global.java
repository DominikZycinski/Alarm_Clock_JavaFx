package sample;

import java.time.LocalTime;
import java.util.ArrayList;

public class Global {

    private static String x = String.valueOf(LocalTime.now());
    static String time = x.substring(0,5) ;
    static ArrayList<String > timeTable = new ArrayList<String>();
}
