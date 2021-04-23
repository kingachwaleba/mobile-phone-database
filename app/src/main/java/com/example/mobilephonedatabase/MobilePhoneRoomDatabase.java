package com.example.mobilephonedatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MobilePhone.class}, version = 1, exportSchema = false)
public abstract class MobilePhoneRoomDatabase extends RoomDatabase {

    // Abstract method returns DAO
    public abstract MobilePhoneDAO mobilePhoneDAO();

    // Singleton implementation
    private static volatile MobilePhoneRoomDatabase INSTANCE;

    static MobilePhoneRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MobilePhoneRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MobilePhoneRoomDatabase.class, "mobile_phones")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                MobilePhoneDAO mobilePhoneDAO = INSTANCE.mobilePhoneDAO();
            });
        }
    };
}
