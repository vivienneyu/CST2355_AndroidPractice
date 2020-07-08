package com.example.mypractice;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        FragmentManager manager = getSupportFragmentManager();
        FragmentB f2 = (FragmentB) manager.findFragmentById(R.id.fragment2);
        if(f2 != null){
            f2.changeData(position);
        }

    }//end of onCreate
}//end of AnotherActivity Class
