package com.github.cs449project;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

// http://www.java2s.com/Code/Android/UI/UsingGridViewtodisplayimages.htm reference

public class FilteredBrowseActivity extends AppCompatActivity {
    private ListView listView;
    public Integer[] idList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_browse);

        Bundle bundle = getIntent().getExtras();
        String selection = bundle.getString("SELECTION");
        String filter = bundle.getString("FILTER");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> ids = new ArrayList<>();

        if (filter.equals("name")) {
            ids = databaseAccess.getImgsByName(selection);
        }

        if (filter.equals("type")) {
            ids = databaseAccess.getImgsByType(selection);
        }

        if (filter.equals("subtype")) {
            ids = databaseAccess.getImgsBySubType(selection);
        }

        if (filter.equals("set")) {
            ids = databaseAccess.getImgsBySet(selection);
        }

        if (filter.equals("artist")) {
            ids = databaseAccess.getImgsByArtist(selection);
        }

        if (filter.equals("colors")) {
            ids = databaseAccess.getImgsByColors(selection);
        }

        if (filter.equals("tag")) {
            ids = databaseAccess.getImgsByTag(selection);
        }

        databaseAccess.close();

        idList = new Integer[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            idList[i] = getResources().getIdentifier(ids.get(i), "drawable", getPackageName());
        }

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));


    }

    public class ImageAdapter extends BaseAdapter
    {
        private Context context;

        public ImageAdapter(Context c)
        {
            context = c;
        }

        //---returns the number of images---
        public int getCount() {
            return idList.length;
        }

        //---returns the ID of an item---
        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        //---returns an ImageView view---
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(475, 665));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(1, 1, 1, 1);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(idList[position]);
            return imageView;
        }
    }
}
