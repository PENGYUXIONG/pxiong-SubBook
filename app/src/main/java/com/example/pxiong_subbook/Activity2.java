/*
 * Activity2
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

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;


/*
*the add activity, going to add the needed object by getting the detail from the input text
* then pass the object to the main in the String format
 */
public class Activity2 extends AppCompatActivity implements View.OnClickListener{
    // check variable
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
        // get the views set
        add = (ImageButton) findViewById(R.id.imageButton2);
        name = (EditText) findViewById(R.id.Name);
        price =(EditText) findViewById(R.id.price);
        comments = (EditText) findViewById(R.id.comments);
        date = (EditText) findViewById(R.id.Date);
        add.setOnClickListener(this);
    }

    /*
    * if the add button is clicked, then the action need to be take
     */
    @Override
    public void onClick(View v){
        // get text from the edittext
        // set the intent
        Intent intent = new Intent();
        String Name = name.getText().toString();
        String Price = price.getText().toString();
        String Comments = comments.getText().toString();
        String Date = date.getText().toString();
        // idea from https://stackoverflow.com/questions/6290531/check-if-edittext-is-empty
        //2018 Feb. 4
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

        // set a new object
        Model model = new Model(Name, Price, Comments, Date);
        // pass the data to main
        intent.putExtra("model", new Gson().toJson(model));
        setResult(RESULT_OK, intent);
        // finish this activity and return to the main
        finish();
    }


}