package com.example.mobilephonedatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MobilePhoneRepository {

    private MobilePhoneDAO mobilePhoneDAO;
    private LiveData<List<MobilePhone>> allMobilePhones;

    MobilePhoneRepository(Application application) {
        MobilePhoneRoomDatabase mobilePhoneRoomDatabase =
                MobilePhoneRoomDatabase.getDatabase(application);

        // A repository uses the DAO object to refer to the database
        mobilePhoneDAO = mobilePhoneRoomDatabase.mobilePhoneDAO();

        // Read all mobile phones from DAO
        allMobilePhones = mobilePhoneDAO.getAlphabetizedMobilePhones();
    }

    // Return all mobile phones
    public LiveData<List<MobilePhone>> getAllMobilePhones() {
        return allMobilePhones;
    }

    // Delete all mobile phones using DAO
    public void deleteAll() {
        MobilePhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mobilePhoneDAO.deleteAll();
        });
    }
}
