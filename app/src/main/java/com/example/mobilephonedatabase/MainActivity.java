package com.example.mobilephonedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.sax.Element;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements MobilePhoneListAdapter.OnItemClickListener {

    private MobilePhoneViewModel mobilePhoneViewModel;
    private MobilePhoneListAdapter mobilePhoneListAdapter;

    private FloatingActionButton floatingActionButton;

    private ItemTouchHelper itemTouchHelper;

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

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Get id of selected mobile phone
                int position = viewHolder.getAdapterPosition();
                MobilePhone mobilePhone = mobilePhoneViewModel.getAllMobilePhones().getValue().get(position);
                // Delete selected mobile phone
                mobilePhoneViewModel.delete(mobilePhone);
            }
        };

        itemTouchHelper = new ItemTouchHelper(simpleCallback);

        // Connect callback with recycler view
        itemTouchHelper.attachToRecyclerView(recyclerView);

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

    @Override
    public void onItemClick(MobilePhone mobilePhone) {
        Intent intent = new Intent(MainActivity.this, InsertActivity.class);

        long id = mobilePhone.getId();
        String producer = mobilePhone.getProducer();
        String model = mobilePhone.getModel();
        String androidVersion = mobilePhone.getAndroidVersion();
        String webPage = mobilePhone.getWwwPage();

        Bundle bundle = new Bundle();

        bundle.putLong("phoneId", id);
        bundle.putString("mobileProducer", producer);
        bundle.putString("mobileModel", model);
        bundle.putString("mobileAndroidVersion", androidVersion);
        bundle.putString("mobileWebPage", webPage);

        intent.putExtras(bundle);

        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int taskCode, int exitCode, Intent result) {
        super.onActivityResult(taskCode, exitCode, result);

        if (exitCode == RESULT_OK) {
            Bundle bundle = result.getExtras();

            long id = bundle.getLong("changedPhoneId");
            String producer = bundle.getString("changedMobileProducer");
            String model = bundle.getString("changedMobileModel");
            String androidVersion = bundle.getString("changedMobileAndroidVersion");
            String webPage = bundle.getString("changedMobileWebPage");

            MobilePhone mobilePhone = new MobilePhone(id, producer, model, androidVersion, webPage);

            mobilePhoneViewModel.update(mobilePhone);
        }
    }
}