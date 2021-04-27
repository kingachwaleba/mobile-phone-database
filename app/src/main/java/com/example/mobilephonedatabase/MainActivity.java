package com.example.mobilephonedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MobilePhoneListAdapter mobilePhoneListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set an adapter in the list
        // Set the layout of the elements of the list
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mobilePhoneListAdapter = new MobilePhoneListAdapter(this);
        recyclerView.setAdapter(mobilePhoneListAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}