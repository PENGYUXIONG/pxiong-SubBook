package com.example.pxiong_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;

public class Activity3 extends AppCompatActivity implements View.OnClickListener{

    public static int Edit = 2;
    private EditText name;
    private EditText price;
    private EditText comments;
    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ImageButton add = (ImageButton) findViewById(R.id.imageButton2);
        name = (EditText) findViewById(R.id.Name);
        price = (EditText) findViewById(R.id.price);
        comments = (EditText) findViewById(R.id.comments);
        date = (EditText) findViewById(R.id.Date);
        add.setOnClickListener(this);
        Intent intent = getIntent();
        Model model = new Gson().fromJson(intent.getStringExtra("model"), Model.class);
        Log.i("LifeCycle --->", model.toString());
        name.setText(model.name);
        price.setText(model.price);
        comments.setText(model.comments);
        date.setText(model.date);
    }


    @Override
    public void onClick(View v) {
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