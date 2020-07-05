package com.example.mypractice;
/**
 * ListView with Image
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
import android.widget.ArrayAdapter;
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
        MyAdapter myAdapter = new MyAdapter(this, titles, image, descriptions);
        listView.setAdapter(myAdapter);

    }


}//end of MainActivity

/**
 * Custom the adapter
 */
class MyAdapter extends ArrayAdapter<String>{
        Context context;
        int[] images;
        String[] titles, descriptions;


    // constructor
    public MyAdapter(@NonNull Context context, String[] titles, int[] imgs, String[] descriptions) {
        super(context, R.layout.layout_listview_item, R.id.tv_title, titles);
        this.context = context;
        this.images = imgs;
        this.titles = titles;
        this.descriptions = descriptions;
    }

    /**Approach #1
     * This first approach causes a lot of memories
     * As user scrolls, many more objects are created
     * A lot more Views are no longer seen and hence no longer used causing Garbage Collection
     * Both object creation and Garbage collection use CPU heavily
     * CPU runs on battery, so device power is drained faster and list is very slow
     * Amount of heap memory per app is limited so your app may crash
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View row =  inflater.inflate(R.layout.layout_listview_item, parent, false);
//
//        ImageView myImage = row.findViewById(R.id.iv_images);
//        TextView myTitle = row.findViewById(R.id.tv_title);
//        TextView myDescription = row.findViewById(R.id.tv_description);
//
//        myImage.setImageResource(images[position]);
//        myTitle.setText(titles[position]);
//        myDescription.setText(descriptions[position]);
//
//        return row;
//    }

    /**Approach #2
     * the param View convertView is null the first time your row is created,
     * Means that no previous object which can be recycled, otherwise it has a value
     * After it's created, it get recycled
     */
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        /**These two lines of codes are the most expensive operation
//         * We wanna make sure that these happens only when the row is created for the first time
//         * When we recycle the View, we don't want these two lines runs again
//         *
//         *         LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//         *         View row =  inflater.inflate(R.layout.layout_listview_item, parent, false);
//         */
//        View row = convertView;
//        if(row == null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            inflater.inflate(R.layout.layout_listview_item, parent, false);
//        }
//
//        ImageView myImage = row.findViewById(R.id.iv_images);
//        TextView myTitle = row.findViewById(R.id.tv_title);
//        TextView myDescription = row.findViewById(R.id.tv_description);
//
//        myImage.setImageResource(images[position]);
//        myTitle.setText(titles[position]);
//        myDescription.setText(descriptions[position]);
//
//        return row;
//    }

    /**Approach #3
     * Use the viewHolder to optimize the process
     * Don't call findViewById() multiple times, do it only when new rows created, it searches for your View
     * object in the entire hierarchy
     */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //When creating a row for the 1st time, perform the inflation, initialize the ViewHolder

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
        holder.myImage.setImageResource(images[position]);
        holder.myTitle.setText(titles[position]);
        holder.myDescription.setText(descriptions[position]);

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