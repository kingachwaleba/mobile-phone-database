package com.example.mobilephonedatabase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class InsertActivity extends AppCompatActivity {

    private EditText producerName;
    private EditText modelName;
    private EditText androidVersion;
    private EditText webPage;

    private Button webSiteButton;
    private Button cancelButton;
    private Button saveButton;

    private MobilePhoneViewModel mobilePhoneViewModel;

    public InsertActivity() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_layout);

        // Get the model view from the view model provider
        mobilePhoneViewModel = new ViewModelProvider(this).get(MobilePhoneViewModel.class);

        producerName = findViewById(R.id.producerNameInput);
        modelName = findViewById(R.id.modelNameInput);
        androidVersion = findViewById(R.id.androidVersionInput);
        webPage = findViewById(R.id.webSiteInput);

        webSiteButton = findViewById(R.id.webSiteButton);
        cancelButton = findViewById(R.id.cancelButton);
        saveButton = findViewById(R.id.saveButton);

        cancelButton.setOnClickListener(v -> finish());

        saveButton.setOnClickListener(v -> {
            MobilePhone  mobilePhone = new MobilePhone(producerName.getText().toString(), modelName.getText().toString(), androidVersion.getText().toString(), webPage.getText().toString());
            mobilePhoneViewModel.insert(mobilePhone);
            finish();
        });
    }
}
