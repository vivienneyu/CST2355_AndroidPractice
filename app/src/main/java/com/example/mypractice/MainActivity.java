package com.example.mypractice;
/**
 * ListView with BaseAdapter
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Communicator{

    private static final String TAG = "MainActivity";
    FragmentA f1;
    FragmentB f2;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, " onCreate was called");
        setContentView(R.layout.layout_main);

        manager = getSupportFragmentManager();
        /**In both portrait and landscape mode, the fragment A is always there
         * We need to initialize the f1 first here
         */

        f1 = (FragmentA)manager.findFragmentById(R.id.fragment1);
        f1.setCommunicator(this);


    }


    @Override
    public void respond(int position) {
        Log.d(TAG, " respond was called");

        /**if f2 is not null and is visible, that means we can see the fragment2 too
         * that means we are in the landscape mode.
         * Otherwise we are inside the portrait mode
         */

        f2 =(FragmentB) manager.findFragmentById(R.id.fragment2);
        if (f2 != null && f2.isVisible()){
            f2.changeData(position);//change the data of the activity

        }else{
            Intent intent = new Intent(this, AnotherActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);

        }

    }
}//end of MainActivity

