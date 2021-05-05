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

    // Insert mobile phone to the database
    public void insert(MobilePhone mobilePhone) {
        MobilePhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mobilePhoneDAO.insert(mobilePhone);
        });
    }

    // Update one mobile phone from the database
    public void update(MobilePhone mobilePhone) {
        mobilePhoneDAO.update(mobilePhone);
    }

    // Delete one mobile phone from the database
    public void delete(MobilePhone mobilePhone) {
        mobilePhoneDAO.delete(mobilePhone);
    }

    // Delete all mobile phones using DAO
    public void deleteAll() {
        MobilePhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mobilePhoneDAO.deleteAll();
        });
    }
}
