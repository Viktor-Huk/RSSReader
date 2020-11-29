package com.example.rssreader.utils.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public interface DateFormatter {

    String patternFirst = "EEE, dd MMM yyyy hh:mm:00";
    String patternSecond = "dd MMMM yyyy HH:mm";

    static Date formatStringToDate(String inputDate) {
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat(patternFirst, Locale.ENGLISH);

        try {
            date = formatter.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    static String formatDateToString(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat(patternSecond);

        return formatter.format(date);
    }
}
