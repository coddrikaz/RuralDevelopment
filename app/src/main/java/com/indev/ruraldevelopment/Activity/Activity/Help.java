package com.indev.ruraldevelopment.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.indev.ruraldevelopment.R;

public class Help extends AppCompatActivity {
    Button calling;
  //  TextView txtphn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Helpline");
        calling=findViewById(R.id.calling);
      //  txtphn=findViewById(R.id.txtphn);

        calling.setOnClickListener(view ->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+916264792693"));
            startActivity(intent);
       });



    }
    }
