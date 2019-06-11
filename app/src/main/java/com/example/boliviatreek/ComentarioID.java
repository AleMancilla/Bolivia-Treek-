package com.example.boliviatreek;

import android.support.annotation.NonNull;

public class ComentarioID {

    public String userID;
    public <T extends ComentarioID> T whitId(@NonNull final String id)
    {
        this.userID = id;
        return (T) this;
    }
}
