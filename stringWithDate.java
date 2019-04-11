package com.example.assignment3;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class to add date and time to input ArrayList.
 */
public class stringWithDate {
    private Date today = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    private String simpleDateTime;
    private String inputString;

    /**
     * Constructor for the class.
     * @param inputString
     */
    public stringWithDate(String inputString) {
        this.inputString = inputString;
        this.simpleDateTime = dateFormat.format(today);
    }

    /**
     * Get method for date/time.
     * @return simpleDateTime
     */
    public String getDateTime() {
        return simpleDateTime;
    }

    /**
     * Set method for date/time.
     * @param dateTime
     */
    public void setDateTime(String dateTime) {
        this.simpleDateTime = dateTime;
    }

    /**
     * Get method for input.
     * @return inputString
     */
    public String getInputString() {
        return inputString;
    }

    /**
     * Set method for input.
     * @param inputString
     */
    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    /**
     * Method to put together input with date/time.
     * @return
     */
    @Override
    public String toString(){
        return inputString + " " + simpleDateTime;
    }
}
