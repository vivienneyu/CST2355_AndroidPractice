package com.example.mypractice;
/**
 * ListView with BaseAdapter
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

    }


    @Override
    public void respond(String data) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentB frag_b = (FragmentB)manager.findFragmentById(R.id.fragment2) ;
       frag_b.changeText(data);
    }
}//end of MainActivity

