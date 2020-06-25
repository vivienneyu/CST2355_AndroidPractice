package com.example.mypractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private EditText mName, mPassword;
    private Button btnLogin;
    private CheckBox mCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        checkPreferences();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save the checkbox preferences
                if(mCheckbox.isChecked())
                {//set a checkbox when the application starts
                    editor.putString(getString(R.string.checkbox), "True");
                    //save the name & password
                    editor.putString(getString(R.string.name), mName.getText().toString());
                    editor.putString(getString(R.string.password), mPassword.getText().toString());
                    editor.commit();

                }else{
                    editor.putString(getString(R.string.checkbox), "False");
                    //save the name & password
                    editor.putString(getString(R.string.name), "");
                    editor.putString(getString(R.string.password), "");
                    editor.commit();
                }

            }
        });


    }

    public void initUI(){
        mName = findViewById(R.id.et_name);
        mPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.bt_login);
        mCheckbox = findViewById(R.id.cb_remember);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //sharedPreferences = getSharedPreferences("com.example.mypractice", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();//use to put items into the database



    }
    private void checkPreferences(){
        String checkbox = sharedPreferences.getString(getString(R.string.checkbox), "False");
        String name = sharedPreferences.getString(getString(R.string.name), "");
        String password = sharedPreferences.getString(getString(R.string.password), "");

        mName.setText(name);
        mPassword.setText(password);
        if(checkbox.equals("True")){
            mCheckbox.setChecked(true);
        }else
            mCheckbox.setChecked(false);
    }

}