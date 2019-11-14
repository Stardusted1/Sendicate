package com.stardusted1.Sendicate.app.service;

public class Variables {
    public static int getLatestUserID() {
        return LatestUserID;
    }

    public static void setLatestUserID(int latestUserID) {
        LatestUserID = latestUserID;
    }

    public static long AddNewUser(){
        int id = getLatestUserID();
        LatestUserID++;
        return id;
    }

    private static int LatestUserID;

}
