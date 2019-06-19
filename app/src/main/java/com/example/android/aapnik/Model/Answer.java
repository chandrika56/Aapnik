package com.example.android.aapnik.Model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

/**
 * Created by Simon on 11/7/2017.
 */


public class Answer {
    @SerializedName("answer_id")
    public int answerId;

    @SerializedName("is_accepted")
    public boolean accepted;

    JSONObject owner = new JSONObject ();



public String link;
    public int score;
    public int view_count;
public int questionId;
    public int creation_date;
    public int last_activity_date;
    public String profile_image;
    public int answer_count;

    public Answer() throws JSONException {
    }

    /*toString method is used when we need a string representaton of object*/
    @Override
    public String toString() {


   {         return "Answer{" +
           ", No_of_Answers="+answer_count+"\n"+
                    ", Link="+link+"\n"+
                    "answerId=" + questionId +"\n"+
                    ", accepted=" + (accepted ? "Yes" : "No") +"\n"+
                    ", score=" + score +"\n"+
                    ", creation_date="+creation_date+"\n"+
                    ", last_activity_date="+last_activity_date+"\n"+
                    ", view_count="+view_count+"\n"+
                    ",profile_image"+profile_image+




        '}';
        }
    }
}
