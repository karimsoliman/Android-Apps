package com.example.android.quakereporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class earthquake {

    private double magnitude;
    private String location;
    private Long date;
    private String Url;

    public earthquake(double mag, String location, Long date){
        this.magnitude = mag;
        this.location = location;
        this.date = date;
    }

    public void setUrl(String url) { this.Url = url; }

    public String getUrl() {return this.Url; }

    public double getMagnitude() {
        return this.magnitude;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return dateFormatter(this.date);
    }

    public String getTime() {return timeFormatter(this.date);}

    private String dateFormatter(Long date){
        Date newDate = new Date(date);
        SimpleDateFormat dateformatter = new SimpleDateFormat("MMM d, yyyy");
        return dateformatter.format(newDate);
    }

    private String timeFormatter(Long date){
        Date newTime = new Date(date);
        SimpleDateFormat timeformatter = new SimpleDateFormat("h:mm a");
        return  timeformatter.format(newTime);
    }

}
