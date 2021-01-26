package com.ehb.apptodoplanning.model.util;

import android.arch.persistence.room.TypeConverter;

import org.threeten.bp.LocalDate;


public class DateConverters {
    @TypeConverter
    public static String toDateString (LocalDate date){
    return (date == null)?null: date.toString();
    }
    @TypeConverter
    public static LocalDate toDate ( String dateString){
        return (dateString ==null)?null:LocalDate.parse( dateString );
    }
    /*@TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }*/
}
