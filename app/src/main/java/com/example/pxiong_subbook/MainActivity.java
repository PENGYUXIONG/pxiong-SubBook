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
    private static final String FILENAME = "tweets.sav";
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
        adapter = new ArrayAdapter<Model>(this, R.layout.list_item, Sublist);
        SubscribeList.setAdapter(adapter);


        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                MainActivity.this.startActivityForResult(intent, Activity2.request);
            }
        });


        SubscribeList.setDividerHeight(3);
        SubscribeList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           int position, long item) {

                Sublist.remove(position);
                TextView charge = (TextView) findViewById(R.id.money);
                if (Sublist.size() == 0){Charge = 0.0;}
                charge.setText("TotalCharge:$" + Charge);
                adapter.notifyDataSetChanged();
                Calculate();
                saveInFile();
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


            Calculate();
            TextView charge = (TextView) findViewById(R.id.money);
            charge.setText("TotalCharge:$" + Charge);
            adapter.notifyDataSetChanged();
            saveInFile();
            setResult(RESULT_OK, intent);
        }

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Model model = new Gson().fromJson(intent.getStringExtra("model"), Model.class);
            this.Sublist.add(model);


            Calculate();
            TextView charge = (TextView) findViewById(R.id.money);
            charge.setText("TotalCharge:$" + Charge);
            adapter.notifyDataSetChanged();
            saveInFile();
            setResult(RESULT_OK, intent);
        }
    }


    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-01-23
            Type listType = new TypeToken<ArrayList<Model>>(){}.getType();
            Sublist = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            Sublist = new ArrayList<Model>();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    private void saveInFile() {
        try {

            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(Sublist, out);
            out.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    public void Calculate() {
        int num = Sublist.size();
        Double Charge = 0.0;
        for (int i = 0; i < num; i = i + 1) {
            if (num == 0){break;}
            Model model = Sublist.get(i);
            String price = model.getPrice();
            Double Price = Double.valueOf(price).doubleValue();
            Charge = Charge + Price;
            TextView charge = (TextView) findViewById(R.id.money);
            charge.setText("TotalCharge:$" + Charge);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {

        // TODO Auto-generated method stub
        super.onStart();
        Log.i("LifeCycle --->", "onStart is called");
        loadFromFile();

        adapter = new ArrayAdapter<Model>(this, R.layout.list_item, Sublist);
        SubscribeList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        int num = Sublist.size();
        Double Charge = 0.0;
        for (int i = 0; i < num; i = i + 1) {
            Model model = Sublist.get(i);
            String price = model.getPrice();
            Double Price = Double.valueOf(price).doubleValue();
            Charge = Charge + Price;
            TextView charge = (TextView) findViewById(R.id.money);
            charge.setText("TotalCharge:$" + Charge);
        }

    }

}
