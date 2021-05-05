package com.example.mobilephonedatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MobilePhoneDAO {

    // The operation will be aborted when there is any conflict
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(MobilePhone mobilePhone);

    @Update(onConflict = OnConflictStrategy.ABORT)
    void update(MobilePhone mobilePhone);

    @Delete
    void delete(MobilePhone mobilePhone);

    @Query("DELETE FROM mobilephone")
    void deleteAll();

    // Return LiveData container with the list of the mobile phones
    // It enables to received notifications when data is changed
    @Query("SELECT id, producer, model, androidVersion, wwwPage FROM mobilephone ORDER BY producer")
    LiveData<List<MobilePhone>> getAlphabetizedMobilePhones();
}
