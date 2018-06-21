package br.com.duoli.speedrunapp.tools;

import org.joda.time.Period;

public class StringUtils {

    private StringUtils() {
    }

    public static String parseRunTime(String time){
        Period period = Period.parse(time);

        StringBuilder stringBuilder = new StringBuilder();
        if (period.getHours() > 0){
            stringBuilder.append(period.getHours());
            stringBuilder.append("h ");
        }
        if (period.getMinutes() > 0){
            stringBuilder.append(period.getMinutes());
            stringBuilder.append("m ");
        }
        if (period.getSeconds() > 0){
            stringBuilder.append(period.getSeconds());
            stringBuilder.append("s ");
        }
        if (period.getMillis() > 0){
            stringBuilder.append(period.getMillis());
            stringBuilder.append("ms ");
        }
        stringBuilder.trimToSize();

        return stringBuilder.toString();
    }
}
