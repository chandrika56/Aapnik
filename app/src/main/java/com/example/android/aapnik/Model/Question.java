package com.example.android.aapnik.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Simon on 11/6/2017.
 */

public class Question {
    public String title;
    public String body;

    /*annotation with parameter (value) is name used when serialising and deserialising objects
    * convert java object to json object*/
    @SerializedName("question_id")
    public String questionId;

    @Override
    public String toString() {
        return(title);
    }
}
