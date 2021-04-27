package com.example.mobilephonedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MobilePhoneViewModel mobilePhoneViewModel;
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

        // Get the model view from the view model provider
        mobilePhoneViewModel = new ViewModelProvider(this).get(MobilePhoneViewModel.class);

        // Set the new mobile phones list in the adapter
        // When the data has been change
        mobilePhoneViewModel.getAllMobilePhones().observe(this, mobilePhones -> {
            mobilePhoneListAdapter.setMobilePhoneList(mobilePhones);
        });
    }
}