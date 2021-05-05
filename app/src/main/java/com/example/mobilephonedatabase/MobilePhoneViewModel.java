package com.example.mobilephonedatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MobilePhoneViewModel extends AndroidViewModel {

    private final MobilePhoneRepository mobilePhoneRepository;
    private final LiveData<List<MobilePhone>> allMobilePhones;

    public MobilePhoneViewModel(@NonNull Application application) {
        super(application);

        mobilePhoneRepository = new MobilePhoneRepository(application);
        allMobilePhones = mobilePhoneRepository.getAllMobilePhones();
    }

    // Return all mobile phones
    public LiveData<List<MobilePhone>> getAllMobilePhones() {
        return allMobilePhones;
    }

    // Insert mobile phone to the database
    public void insert(MobilePhone mobilePhone) {
        mobilePhoneRepository.insert(mobilePhone);
    }

    // Update one mobile phone from the database
    public void update(MobilePhone mobilePhone) {
        mobilePhoneRepository.update(mobilePhone);
    }

    // Delete all mobile phones
    public void deleteAll() {
        mobilePhoneRepository.deleteAll();
    }

}
