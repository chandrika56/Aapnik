package com.example.android.aapnik;

import com.example.android.aapnik.Model.Answer;
import com.example.android.aapnik.Model.Question;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Simon on 11/7/2017.
 */

public interface StackOverflowAPI {
    String BASE_URL = "https://api.stackexchange.com";

    @GET("/2.2/questions?order=desc&sort=votes&site=stackoverflow&tagged=android&filter=withbody")
    Call<ListWrapper<Question>> getQuestions();

    @GET("/2.2/questions?order=desc&sort=activity&site=stackoverflow")
    Call<ListWrapper<Answer>> getAnswersForQuestion();

    @GET("2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<ListWrapper<Answer>> getAnswersForQuestion2();

    @GET("2.2/questions?order=desc&sort=votes&site=stackoverflow")
    Call<ListWrapper<Answer>> getAnswersForQuestion3();


}
