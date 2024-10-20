package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>ICS4U</p>
 * <p>AY Jackson SS</p>
 * <p>Utility class for date manipulation.</p>
 *
 * <p>This class provides methods for adding days to a date and converting a string to a date object.
 * The date format used throughout the class is "EEE MMM dd HH:mm:ss zzz yyyy" (e.g., Mon Jan 15 17:39:08 EST 2024).</p>
 *
 * @author Daniel, Meherzad, Stanley
 * @since 2024-01-15
 */
public class DateUtils {
    /**
     * The date format used in the class for parsing and formatting dates.
     */
    private static final String DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy"; // Ex. Mon Jan 15 17:39:08 EST 2024
    /**
     * The calendar instance used for date manipulation.
     */
    private static final Calendar CALENDAR = Calendar.getInstance();
    /**
     * The date formatter instance used for parsing and formatting dates.
     */
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private DateUtils() {}

    /**
     * Adds a specified number of days to a given date.
     *
     * <p>This method calculates and returns a new {@code Date} by adding the specified number of days
     * to the provided base date. The operation is performed using the internal {@code Calendar} instance
     * to ensure accuracy and consideration of factors such as daylight saving time.</p>
     *
     * @param baseDate The base date.
     * @param daysToAdd The number of days to add.
     * @return A new {@code Date} representing the result of adding the specified days to the base date.
     *
     * @throws IllegalArgumentException If the baseDate is {@code null}.
     *
     */
    public static Date addDays(Date baseDate, int daysToAdd) {
        if (baseDate == null) {
            throw new NullPointerException("baseDate cannot be null.");
        }

        CALENDAR.setTime(baseDate);
        CALENDAR.add(Calendar.DAY_OF_WEEK, daysToAdd);
        return CALENDAR.getTime();
    }

    /**
     * Converts a string representation of a date to a {@code Date} object using the specified date format.
     *
     * <p><strong>Date Format Template:</strong> "EEE MMM dd HH:mm:ss zzz yyyy" (e.g., Mon Jan 15 17:39:08 EST 2024)</p>
     * <p><strong>Example:</strong> {@code DateUtils.stringToDate("Mon Jan 15 17:39:08 EST 2024");}</p>
     *
     * @param date The string representation of the date.
     * @return A {@code Date} object parsed from the input string, or {@code null} if the parsing fails.
     *
     * @throws IllegalArgumentException If the input date string is not in the expected format.
     *
     * @see SimpleDateFormat#parse(String)
     * @see #DATE_FORMAT
     * @see <a href="https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/text/SimpleDateFormat.html">SimpleDateFormat</a>
     * @see <a href="https://docs.oracle.com/en/java/javase/21/docs/api/java.sql/java/sql/Date.html">Date</a>
     *
     */
    public static Date stringToDate(String date) {
        try {
            return DATE_FORMATTER.parse(date);
        } catch (ParseException e) {
            System.err.println("Invalid date format.");
            return null;
        }
    }

}
