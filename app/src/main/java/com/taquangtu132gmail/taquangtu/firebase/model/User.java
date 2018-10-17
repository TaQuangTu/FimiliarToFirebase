package com.taquangtu132gmail.taquangtu.firebase.model;

public class User
{
    private int mId;
    private String mName;
    private String addtionalInformation;

    public User() {
    }

    public User(int mId, String mName, String addtionalInformation) {
        this.mId = mId;
        this.mName = mName;
        this.addtionalInformation = addtionalInformation;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getAddtionalInformation() {
        return addtionalInformation;
    }

    public void setAddtionalInformation(String addtionalInformation) {
        this.addtionalInformation = addtionalInformation;
    }
}
