package com.example.mypractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private ArrayList<Person> peopleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mListView = findViewById(R.id.lv_theList);
       Log.d(TAG, "onCreate: started.");

       initPerson();

        PersonListAdapter adapter = new PersonListAdapter(this, R.layout.list_item_layout, peopleList);
        mListView.setAdapter(adapter);

//        ArrayList<String> names = new ArrayList<>();
//        names.add("Vivi");
//        names.add("Wiwi");
//        names.add("Yiyi");
//        names.add("Xixi");
//        names.add("Zizi");
//
//        //this is a default adapter
//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item_layout, names);
//        list.setAdapter(adapter);adapter

    }

private void initPerson(){
    //Create the Person objects
    Person john = new Person("John", "12-20-2010", "Male");
    Person peter = new Person("Peter", "12-12-2012", "Male");
    Person jane = new Person("Jane", "13-12-2011", "Female");
    Person joe = new Person("Joe", "14-12-2011", "Female");

    peopleList = new ArrayList<>();
    peopleList.add(john);
    peopleList.add(peter);
    peopleList.add(jane);
    peopleList.add(joe);

}

}