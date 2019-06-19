package com.example.android.aapnik.Model;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 11/7/2017.
 */
public class FakeDataProvider {
    public static List<Question> getQuestions(){
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i<10; i++) {
            Question question = new Question();
            question.questionId = String.valueOf(i);
            question.title = String.valueOf(i);
            question.body = String.valueOf(i) + "Body";
            questions.add(question);
        }
        return questions;
    }
    public static List<Answer> getAnswers() throws JSONException {
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i<10; i++) {
            Answer answer = new Answer();
            answer.answer_count=i;
            answer.profile_image=" ";
            answer.questionId = i;
            answer.accepted = false;
            answer.score = i;
            answer.last_activity_date=i;
            answer.creation_date=i;
            answer.view_count=i;
            answer.link=" ";



            answers.add(answer);
        }
        return answers;
    }

}
