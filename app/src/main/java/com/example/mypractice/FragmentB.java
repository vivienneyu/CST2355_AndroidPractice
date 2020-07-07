package com.example.mypractice;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentB extends Fragment {
    private static final String TAG ="FragmentB";
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView was called");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView = getActivity().findViewById(R.id.text_view);
        Log.d(TAG, "onActivityCreated was called");
    }

    public void changeData(int position){
        Log.d(TAG, "changeData was called");
        Resources resources = getResources();
        String[] descriptions = resources.getStringArray(R.array.descriptions);
        textView.setText(descriptions[position]);
    }

}