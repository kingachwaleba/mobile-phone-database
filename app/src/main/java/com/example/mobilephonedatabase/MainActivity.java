package com.example.mobilephonedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private MobilePhoneViewModel mobilePhoneViewModel;
    private MobilePhoneListAdapter mobilePhoneListAdapter;

    private FloatingActionButton floatingActionButton;

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
        // When the data has been changed
        mobilePhoneViewModel.getAllMobilePhones().observe(this, mobilePhones -> {
            mobilePhoneListAdapter.setMobilePhoneList(mobilePhones);
        });

        floatingActionButton = findViewById(R.id.fabMain);

        floatingActionButton.setOnClickListener(v -> insertActivity());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Create menu in the app based on the XML file
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.deleteAll) {
            mobilePhoneViewModel.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Invoke one activity into another
    public void insertActivity() {
        Intent intent = new Intent(MainActivity.this, InsertActivity.class);
        startActivityForResult(intent, 0);
    }
}