package com.example.mypractice;
/**
 * ListView with BaseAdapter
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Communicator{
    private static final String TAG = "MainActivity";
    FragmentB f2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, " onCreate was called");
        setContentView(R.layout.layout_main);

    }


    @Override
    public void respond(int position) {
        Log.d(TAG, " respond was called");
        FragmentManager manager = getSupportFragmentManager();
        f2 =(FragmentB) manager.findFragmentById(R.id.fragment2);
        f2. changeData(position);
    }
}//end of MainActivity

