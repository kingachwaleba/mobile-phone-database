package com.example.mobilephonedatabase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    EditText producerName;
    EditText modelName;
    EditText androidVersion;
    EditText webPage;

    Button webSiteButton;
    Button cancelButton;
    Button saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_layout);

        producerName = findViewById(R.id.producerNameInput);
        modelName = findViewById(R.id.modelNameInput);
        androidVersion = findViewById(R.id.androidVersionInput);
        webPage = findViewById(R.id.webSiteInput);
    }
}