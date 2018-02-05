package com.example.pxiong_subbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.xml.transform.Result;


public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "Subbook.sav";
    private ListView SubscribeList;
    private ArrayList<Model> Sublist;
    private ArrayAdapter<Model> adapter;
    public Double Charge = 0.0;
    public int Position;

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.add);
        SubscribeList = (ListView) findViewById(R.id.SubscribeList);



        Sublist = new ArrayList<Model>();
        adapter = new ArrayAdapter<Model>(this,R.layout.list_item, Sublist);
        SubscribeList.setAdapter(adapter);




        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                MainActivity.this.startActivityForResult(intent, Activity2.request);

            }
        });


        SubscribeList.setDividerHeight(3);
        SubscribeList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                  int position, long item) {
                Model model = Sublist.get(position);
                String price = model.getPrice();
                Double Price = Double.valueOf(price).doubleValue();
                Sublist.remove(position);
                Charge = Charge - Price;
                TextView charge = (TextView) findViewById(R.id.money);
                charge.setText("TotalCharge:" + Charge);
                adapter.notifyDataSetChanged();
                return true;
            }
        });


        SubscribeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long item) {
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                Model model = Sublist.get(position);
                Position = position;
                intent.putExtra("model", new Gson().toJson(model));
                MainActivity.this.startActivityForResult(intent, Activity3.Edit);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Model model = new Gson().fromJson(intent.getStringExtra("model"), Model.class);
            Model oldmodel = Sublist.get(Position);
            this.Sublist.remove(Position);
            this.Sublist.add(Position, model);

            String price = model.getPrice();
            String oldprice = oldmodel.getPrice();
            Double Price = Double.valueOf(price).doubleValue();
            Double oldPrice = Double.valueOf(oldprice).doubleValue();
            Charge = Charge+Price;
            Charge = Charge - oldPrice;
            TextView charge = (TextView) findViewById(R.id.money);
            charge.setText("TotalCharge:" + Charge);
            adapter.notifyDataSetChanged();
            setResult(RESULT_OK, intent);
        }

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Model model = new Gson().fromJson(intent.getStringExtra("model"), Model.class);
            this.Sublist.add(model);

            String price = model.getPrice();
            Double Price = Double.valueOf(price).doubleValue();
            Charge = Charge+Price;
            TextView charge = (TextView) findViewById(R.id.money);
            charge.setText("TotalCharge:" + Charge);
            adapter.notifyDataSetChanged();
            setResult(RESULT_OK, intent);
        }
    }




}
