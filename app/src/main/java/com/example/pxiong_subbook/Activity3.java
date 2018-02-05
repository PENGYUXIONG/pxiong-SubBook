/*
 * Activity3
 *
 * Version 1.0
 *
 * January 30, 2018
 *
 * Copyright (c) 2018 Pengyu Xiong CMPUT301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and condition of the Code of Student Behaviour at University of Alberta.
 * You can find a copy of the license in this project. Otherwise please contact pxiong@ualberta.ca
 */
package com.example.pxiong_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class Activity3 extends AppCompatActivity implements View.OnClickListener{
    // checkcode for Deit
    public static int Edit = 2;
    private EditText name;
    private EditText price;
    private EditText comments;
    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        // state the buttons and texts
        ImageButton add = (ImageButton) findViewById(R.id.imageButton2);
        name = (EditText) findViewById(R.id.Name);
        price = (EditText) findViewById(R.id.price);
        comments = (EditText) findViewById(R.id.comments);
        date = (EditText) findViewById(R.id.Date);
        add.setOnClickListener(this);
        // set the intent to receive data
        Intent intent = getIntent();
        Model model = new Gson().fromJson(intent.getStringExtra("model"), Model.class);
        name.setText(model.name);
        price.setText(model.price);
        comments.setText(model.comments);
        date.setText(model.date);
    }


    /*
    * pass the data back to main after the data is edited
    * if the data does not in correct form redo the action and throw a text
    * to warn
     */
    @Override
    public void onClick(View v) {
        // pass the object in the dorm of String
        Intent intent = new Intent();
        String Name = name.getText().toString();
        String Price = price.getText().toString();
        String Comments = comments.getText().toString();
        String Date = date.getText().toString();
        // Have to fill values in certain categories
        if (Name.matches("")) {
            Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Price.matches("")) {
            Toast.makeText(this, "You did not enter a price", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Date.matches("")) {
            Toast.makeText(this, "You did not enter a date", Toast.LENGTH_SHORT).show();
            return;
        }
        // set new object
        Model model = new Model(Name, Price, Comments, Date);
        // pass data
        intent.putExtra("model", new Gson().toJson(model));

        setResult(RESULT_OK, intent);
        // finish this activity and return
        finish();
    }



}