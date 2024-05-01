package com.nerogene.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getFormattedDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
}
