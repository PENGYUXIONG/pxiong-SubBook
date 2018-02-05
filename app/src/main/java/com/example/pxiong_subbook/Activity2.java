package com.example.pxiong_subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.gson.Gson;

import java.util.Date;


public class Activity2 extends AppCompatActivity implements View.OnClickListener{
    public static int request = 1;

    private ImageButton add;
    private EditText name;
    private EditText price;
    private EditText comments;
    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        add = (ImageButton) findViewById(R.id.imageButton2);
        name = (EditText) findViewById(R.id.Name);
        price =(EditText) findViewById(R.id.price);
        comments = (EditText) findViewById(R.id.comments);
        date = (EditText) findViewById(R.id.Date);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent();
        String Name = name.getText().toString();
        String Price = price.getText().toString();
        String Comments = comments.getText().toString();
        String Date = date.getText().toString();

        Model model = new Model(Name, Price, Comments, Date);

        intent.putExtra("model", new Gson().toJson(model));
        setResult(RESULT_OK, intent);
        finish();
    }

}