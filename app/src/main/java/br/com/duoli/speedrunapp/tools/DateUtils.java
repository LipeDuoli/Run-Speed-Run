package br.com.duoli.speedrunapp.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private DateUtils() {
    }

    public static String getYear(Date date){
        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        return yearFormat.format(date);
    }
}
