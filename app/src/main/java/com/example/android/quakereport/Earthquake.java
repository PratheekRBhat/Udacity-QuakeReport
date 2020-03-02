package com.example.android.quakereport;

public class Earthquake {
    private String mLocation, mUrl;
    private double mMagnitude;
    private long mTimeInMilliseconds;

    public Earthquake(double magnitude, String location, long timeInMilliSeconds, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliSeconds;
        mUrl = url;
    }

    public double getMagnitude() { return mMagnitude;}

    public String getLocation() { return mLocation;}

    public long getTimeInMilliseconds() { return mTimeInMilliseconds;}

    public String getUrl() { return mUrl;}
}