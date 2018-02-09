package com.github.cs449project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FilteredBrowseActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_browse);

        Bundle bundle = getIntent().getExtras();
        String selection = bundle.getString("SELECTION");
        String filter = bundle.getString("FILTER");

        this.listView = (ListView) findViewById(R.id.listView);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> ids = new ArrayList<>();

        if (filter.equals("name")) {
            ids = databaseAccess.getIdsByName(selection);
        }

        if (filter.equals("type")) {
            ids = databaseAccess.getIdsByType(selection);
        }

        if (filter.equals("subtype")) {
            ids = databaseAccess.getIdsBySubType(selection);
        }

        if (filter.equals("set")) {
            ids = databaseAccess.getIdsBySet(selection);
        }

        if (filter.equals("artist")) {
            ids = databaseAccess.getIdsByArtist(selection);
        }

        if (filter.equals("colors")) {
            ids = databaseAccess.getIdsByColors(selection);
        }

        if (filter.equals("tag")) {
            ids = databaseAccess.getIdsByTag(selection);
        }

        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ids);
        this.listView.setAdapter(adapter);
    }
}
