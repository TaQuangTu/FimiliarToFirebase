package com.taquangtu132gmail.taquangtu.firebase.model;

public class User
{
    private int mId;
    private String mName;
    private String additionalInformation;

    public User() {
    }

    public User(int mId, String mName, String additionalInformation) {
        this.mId = mId;
        this.mName = mName;
        this.additionalInformation = additionalInformation;
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

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAddtionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
