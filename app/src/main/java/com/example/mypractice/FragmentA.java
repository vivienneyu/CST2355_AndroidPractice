package com.example.mypractice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class FragmentA extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG ="FragmentA";
    ListView listView;
    Communicator communicator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView was called");
      View view = inflater.inflate(R.layout.fragment_a, container, false);
        listView = view.findViewById(R.id.list_view);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.titles, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated was called");


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        communicator.respond(position);
        Log.d(TAG, "onItemClick was called");
    }
}

/**This is a better object-oriented design pattern
 * The Communicator interface is not exposed to FragmentB, principle of least privilege
 */
 interface Communicator {
    /***
     * This method is responsible for carrying data from FragmentA to FragmentB
     *
     * @param position
     */
    public void respond(int position);
}