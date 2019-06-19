package com.example.android.aapnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.aapnik.Adapter.RecyclerViewAdapter;
import com.example.android.aapnik.Model.Answer;
import com.example.android.aapnik.Model.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sortcreation extends AppCompatActivity implements View.OnClickListener {

    private StackOverflowAPI stackOverflowAPI;
    private String token;
    private Button authenticateButton;
    private Spinner questionsSpinner;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sortcreation);
        questionsSpinner = (Spinner) findViewById(R.id.questions_spinner);
        questionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "Spinner item selected", Toast.LENGTH_LONG).show();

                Question question = (Question) parent.getAdapter().getItem(position);
                stackOverflowAPI.getAnswersForQuestion2().enqueue(answersCallback);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       /* List<Question> questions = FakeDataProvider.getQuestions();
        ArrayAdapter<Question> arrayAdapter = new ArrayAdapter<Question>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, questions);
        questionsSpinner.setAdapter(arrayAdapter);*/


        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager (sortcreation.this));

        createStackoverflowAPI();
        stackOverflowAPI.getQuestions().enqueue(questionsCallback);
       /* List<Answer> answers = FakeDataProvider.getAnswers();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(answers);*/
//        recyclerView.setAdapter(adapter);
    }

    private void createStackoverflowAPI() {
        Gson gson = new GsonBuilder ()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StackOverflowAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        stackOverflowAPI = retrofit.create(StackOverflowAPI.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (token != null){
            authenticateButton.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case android.R.id.text1:
                if (token != null) {
                    // TODO
                } else {
                    Toast.makeText(this, "You need to authenticate first", Toast.LENGTH_LONG).show();
                }
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            token = data.getStringExtra("token");
        }
    }


    //callback for question and answer
    Callback<ListWrapper<Question>> questionsCallback = new Callback<ListWrapper<Question>>() {
        @Override
        public void onResponse(Call<ListWrapper<Question>> call, Response<ListWrapper<Question>> response) {
            if (response.isSuccessful()) {
                ListWrapper<Question> questions = response.body();
                ArrayAdapter<Question> arrayAdapter = new ArrayAdapter<Question>(sortcreation.this, android.R.layout.simple_spinner_dropdown_item, questions.items);
                questionsSpinner.setAdapter(arrayAdapter);
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Question>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    Callback<ListWrapper<Answer>> answersCallback = new Callback<ListWrapper<Answer>>() {
        @Override
        public void onResponse(Call<ListWrapper<Answer>> call, Response<ListWrapper<Answer>> response) {
            if (response.isSuccessful()) {
                List<Answer> data = new ArrayList<> ();
                data.addAll(response.body().items);
                recyclerView.setAdapter(new RecyclerViewAdapter (data));
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Answer>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    Callback<ResponseBody> upvoteCallback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                Toast.makeText(sortcreation.this, "Upvote successful", Toast.LENGTH_LONG).show();
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
                Toast.makeText(sortcreation.this, "You already upvoted this answer", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            t.printStackTrace();
        }
    };
}
