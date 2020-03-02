package com.example.android.quakereport;

public class Earthquake {
    private String mMagnitude, mLocation;
    private long mTimeInMilliseconds;

    public Earthquake(String magnitude, String location, long timeInMilliSeconds){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliSeconds;
    }

    public String getMagnitude() { return mMagnitude;}

    public String getLocation() { return mLocation;}

    public long getTimeInMilliseconds() { return mTimeInMilliseconds;}
}