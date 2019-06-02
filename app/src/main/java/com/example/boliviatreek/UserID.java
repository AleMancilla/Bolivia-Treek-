package com.example.boliviatreek;

import android.support.annotation.NonNull;

public class UserID {

    public String userID;
    public <T extends  UserID> T whitId(@NonNull final String id)
    {
        this.userID = id;
        return (T) this;
    }
}
