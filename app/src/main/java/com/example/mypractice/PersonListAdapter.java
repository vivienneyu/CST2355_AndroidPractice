package com.example.mypractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PersonListAdapter extends ArrayAdapter<Person> {

    private static final String TAG = "PersonListAdapter";
    private Context mContext;
    private int mResource;

    public PersonListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Person> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //get the persons information
        String name = getItem(position).getName();
        String birthday = getItem(position).getBirthday();
        String gender = getItem(position).getGender();

        //Create the person object with the information
        Person person = new Person(name, birthday,gender);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView)convertView.findViewById(R.id.tv_1);
        TextView tvBirthday = convertView.findViewById(R.id.tv_2);
        TextView tvGender = convertView.findViewById(R.id.tv_3);

        tvName.setText(name);
        tvBirthday.setText(birthday);
        tvGender.setText(gender);
        return convertView;
    }
}
