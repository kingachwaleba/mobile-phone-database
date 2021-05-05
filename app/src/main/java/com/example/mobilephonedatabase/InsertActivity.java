package com.example.mobilephonedatabase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class InsertActivity extends AppCompatActivity {

    private EditText producerName;
    private EditText modelName;
    private EditText androidVersion;
    private EditText webPage;

    private Long id;

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

        saveButton.setEnabled(false);

        // Handle the button that shows web page
        webSiteButton.setOnClickListener(v -> {
            String webPageAddress = webPage.getText().toString();

            if (webPageAddress.startsWith("http://")) {
                Intent webIntent = new Intent("android.intent.action.VIEW", Uri.parse(webPageAddress));
                startActivity(webIntent);
            }
            else {
                Toast.makeText(InsertActivity.this, R.string.webPageMessage, Toast.LENGTH_SHORT).show();
            }
        });

        // Read data about selected mobile phone
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getLong("phoneId");
            producerName.setText(bundle.getString("mobileProducer"));
            modelName.setText(bundle.getString("mobileModel"));
            androidVersion.setText(bundle.getString("mobileAndroidVersion"));
            webPage.setText(bundle.getString("mobileWebPage"));

            // Change text in save button and set it enabled
            saveButton.setText(R.string.updateButtonName);
            saveButton.setEnabled(true);
        }

        saveButton.setOnClickListener(v -> {
            MobilePhone  mobilePhone = new MobilePhone(producerName.getText().toString(), modelName.getText().toString(), androidVersion.getText().toString(), webPage.getText().toString());

            if (bundle != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putLong("changedPhoneId", id);
                bundle2.putString("changedMobileProducer", producerName.getText().toString());
                bundle2.putString("changedMobileModel", modelName.getText().toString());
                bundle2.putString("changedMobileAndroidVersion", androidVersion.getText().toString());
                bundle2.putString("changedMobileWebPage", webPage.getText().toString());

                Intent intent = new Intent();
                intent.putExtras(bundle2);
                setResult(RESULT_OK, intent);
            }
            else {
                mobilePhoneViewModel.insert(mobilePhone);
            }

            finish();
        });

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

        if (savedInstanceState != null) {
            String savedProducerName = savedInstanceState.getString("producerName");
            String savedModelName = savedInstanceState.getString("modelName");
            String savedAndroidVersion = savedInstanceState.getString("androidVersion");
            String savedWebPage = savedInstanceState.getString("webPage");

            producerName.setText(savedProducerName);
            modelName.setText(savedModelName);
            androidVersion.setText(savedAndroidVersion);
            webPage.setText(savedWebPage);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // getText() returns CharSequence not String
        producerName = findViewById(R.id.producerNameInput);
        modelName = findViewById(R.id.modelNameInput);
        androidVersion = findViewById(R.id.androidVersionInput);
        webPage = findViewById(R.id.webSiteInput);

        outState.putString("producerName", producerName.getText().toString());
        outState.putString("modelName", modelName.getText().toString());
        outState.putString("androidVersion", androidVersion.getText().toString());
        outState.putString("webPage", webPage.getText().toString());
    }

    public boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }
}
