package com.example.mypractice;
/**
 * ListView with BaseAdapter
 */

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    ListView listView;
    String[] titles;
    String[] descriptions;
    int[] image = {R.drawable.cat_d,R.drawable.cat_d, R.drawable.cat_d,
            R.drawable.cat_d, R.drawable.cat_d, R.drawable.cat_d, R.drawable.cat_d, R.drawable.cat_d, R.drawable.cat_d};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview);
       Log.d(TAG, "onCreate: started.");

       //step 1: prepare the data source
        Resources res = getResources();
        titles = res.getStringArray(R.array.titles);
        descriptions = res.getStringArray(R.array.descriptions);

        listView = findViewById(R.id.lv_theList);
        listView.setAdapter(new MyAdapter(this));

    }


}//end of MainActivity

/**
 * Custom the adapter
 * 1. Create a class that extends BaseAdapter and implement all the methods
 * 2. Maintain some arrray inside your BaseAdapter class that will contain all the data(image, title and descriptions)
 * 3. Use the getView() to fill the data from your array inside the custom structure of that single row for each row
 */
class MyAdapter extends BaseAdapter {
        ArrayList<SingleRow> list;
        Context context;
    // constructor
    public MyAdapter( Context context) {
       list= new ArrayList<SingleRow>();
        /**The above line of code just create an object with nothing in it
         * We need to get the titles, descriptions and images into a single row object
         * Put the SingleRow object inside the arraylist
         */
       Resources res = context.getResources();
       String[] title = res.getStringArray(R.array.titles);
        String[] description = res.getStringArray(R.array.descriptions);
       int[] image = {R.drawable.cat_d,R.drawable.cat_d, R.drawable.cat_d,
                R.drawable.cat_d, R.drawable.cat_d, R.drawable.cat_d, R.drawable.cat_d, R.drawable.cat_d, R.drawable.cat_d};

       for(int i = 0; i<9; i++){
           list.add(new SingleRow(title[i], description[i], image[i]));
       }
        this.context = context;
    }
    /**u1, u2, u3, u4
     * u1->title, description and image
     * make a composite object to hold multiple elements
     * We need to make a class to SingleRow
     */
    // return the numbers of
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    //this is useful in the database, it returns the index of the item in the database
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**Get the root view
     * Use the root view to find other views
     * Set the values
     */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        MyViewHolder holder;
        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.layout_listview_item, parent, false);
            holder = new MyViewHolder(row);

            //store the holder so that you don't create it again while recycling
            row.setTag(holder);
            Log.d("MyAdapter", "Creating a new row");
        }else //recycling: retrieve the stored holder when recycling
            {
            holder= (MyViewHolder) row.getTag();
            Log.d("MyAdapter", "Recycling stuff");
        }

        /**set the values for the Views inside your row by accessing them through your Viewholder
         * The values must be set everytime
         */

        SingleRow sr = list.get(position);
        holder.myDescription.setText(sr.description);
        holder.myTitle.setText(sr.title);
        holder.myImage.setImageResource(sr.image);

        return row;
    }


}//end of MyAdapter

/**ViewHolder for Approach #3
 * Design Pattern#1
 */
class MyViewHolder{
    ImageView myImage ;
    TextView myTitle ;
     TextView myDescription ;

    MyViewHolder(View view){
         myImage = view.findViewById(R.id.iv_images);
         myTitle = view.findViewById(R.id.tv_title);
         myDescription = view.findViewById(R.id.tv_description);
    }

}//end of MyViewHolder

class SingleRow{
    String title, description;
    int image;

    SingleRow(String title, String description, int image){
        this.description = description;
        this.title = title;
        this.image = image;
    }
}