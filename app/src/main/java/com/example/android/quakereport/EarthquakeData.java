package com.example.android.quakereport;

import org.json.JSONObject;

import java.net.URI;
import java.util.Date;
import java.util.regex.Pattern;

public class EarthquakeData {

    private String mplace;
    private double mMag;
    private long mTimeInMilliseconds;
    private String mUrl;



    public EarthquakeData(double mag, String place, long time, String Url) {
        mMag = mag;
        mplace = place;
        mTimeInMilliseconds = time;
        mUrl = Url;
    }


    public String getMplace() {
        return  mplace;
    }

    public double getmMag() {
        return mMag;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getmUrl() {
        return mUrl;
    }
}
