package ua.lviv.iot.parkingServer;

import java.text.SimpleDateFormat;

public class Date {
    public static String getTimeNow() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return date;

    }
}
