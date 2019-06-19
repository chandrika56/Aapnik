package com.example.android.aapnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sorting extends AppCompatActivity{

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sorting);
            Button creation=findViewById (R.id.button5);
            Button activity=findViewById (R.id.button4);
            Button votes=findViewById (R.id.button6);
            creation.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    startActivity (new Intent (Sorting.this,sortcreation.class));
                }
            });
            activity.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    startActivity (new Intent (Sorting.this,MainActivity.class));
                }
            });
            votes.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    startActivity (new Intent (Sorting.this,sortVote.class));
                }
            });
    }
}
