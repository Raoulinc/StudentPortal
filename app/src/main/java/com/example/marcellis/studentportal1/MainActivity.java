package com.example.marcellis.studentportal1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CustomListAdapter customListAdapter;
    private ListView listView;
    private List<ListItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);
        //Initialize the item list
        items = new ArrayList<ListItem>();

        items.add(new ListItem("VLO", "https://home.informatica.hva.nl/vlo/"));
        items.add(new ListItem("DMCI", "https://ict.dmci.hva.nl/"));
        items.add(new ListItem("Rooster", "https://rooster.hva.nl/"));
        items.add(new ListItem("Resultaten", "https://resultaten.hva.nl/"));

        customListAdapter = new CustomListAdapter(items, this);

        listView.setAdapter(customListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View listItem, int position, long id) {
                ListItem clickedItem = (ListItem) parent.getItemAtPosition(position);
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(clickedItem.getUrl()));
                startActivity(myIntent);
            }

        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPortal.class);
                startActivityForResult(intent, 1234);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            // Checks if the requestCode is correct
            if (requestCode == 1234) {
                // Gets the values
                String url = data.getStringExtra("url");
                String title = data.getStringExtra("title");

                // Add the values to the list
                items.add(new ListItem(title, url));

                // Refresh the listView
                customListAdapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
