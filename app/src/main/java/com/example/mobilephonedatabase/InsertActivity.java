package com.example.mobilephonedatabase;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        saveButton.setEnabled(false);

        producerName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isEmpty(producerName)) {
                    Toast.makeText(InsertActivity.this, R.string.errorMessage, Toast.LENGTH_SHORT).show();
                    producerName.setError(getString(R.string.errorMessage));
                    saveButton.setEnabled(false);
                }
            }
        });

        modelName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isEmpty(modelName)) {
                    Toast.makeText(InsertActivity.this, R.string.errorMessage, Toast.LENGTH_SHORT).show();
                    modelName.setError(getString(R.string.errorMessage));
                    saveButton.setEnabled(false);
                }
            }
        });

        androidVersion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isEmpty(androidVersion)) {
                    Toast.makeText(InsertActivity.this, R.string.errorMessage, Toast.LENGTH_SHORT).show();
                    androidVersion.setError(getString(R.string.errorMessage));
                    saveButton.setEnabled(false);
                }
            }
        });

        webPage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isEmpty(webPage)) {
                    Toast.makeText(InsertActivity.this, R.string.errorMessage, Toast.LENGTH_SHORT).show();
                    webPage.setError(getString(R.string.errorMessage));
                    saveButton.setEnabled(false);
                }
            }
        });

        producerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isEmpty(modelName) && !isEmpty(androidVersion) && !isEmpty(webPage))
                    saveButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        modelName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isEmpty(producerName) && !isEmpty(androidVersion) && !isEmpty(webPage))
                    saveButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        androidVersion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isEmpty(modelName) && !isEmpty(producerName) && !isEmpty(webPage))
                    saveButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        webPage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isEmpty(modelName) && !isEmpty(androidVersion) && !isEmpty(producerName))
                    saveButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }
}
