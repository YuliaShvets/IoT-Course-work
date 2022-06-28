package ua.lviv.iot.parkingServer.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateToday {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static String getDateToday() {
        return DATE_FORMAT.format(Calendar.getInstance().getTime());
    }

}