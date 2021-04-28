package com.example.mobilephonedatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// entities - sets classes that matches the databases tables
// version - the database version (class runs an appropriate migration)
// exportSchema = false - eliminates warnings during creating the database
@Database(entities = {MobilePhone.class}, version = 1, exportSchema = false)
public abstract class MobilePhoneRoomDatabase extends RoomDatabase {

    // Abstract method returns DAO
    public abstract MobilePhoneDAO mobilePhoneDAO();

    // Singleton implementation
    private static volatile MobilePhoneRoomDatabase INSTANCE;

    static MobilePhoneRoomDatabase getDatabase(final Context context) {
        // Create a new object as long as none exists
        if (INSTANCE == null) {
            synchronized (MobilePhoneRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MobilePhoneRoomDatabase.class, "mobile_phones")
                            // Set an object that handles events related to the database
                            .addCallback(sRoomDatabaseCallback)
                            // One of the types of migration - deleted and created databases again
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // A service that executes tasks in a separate thread
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // An object that handles call backs related to events in the database, like onCreate, onOpen
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Executing an operation in a separate thread
            // execute() takes in a runnable object and performs its task asynchronously
            databaseWriteExecutor.execute(() -> {
                MobilePhoneDAO mobilePhoneDAO = INSTANCE.mobilePhoneDAO();

                MobilePhone mobilePhone1 = new MobilePhone("Google", "Pixel 4", "8.1", "www.pixel4.com");
                MobilePhone mobilePhone2 = new MobilePhone("Samsung", "Galaxy S21", "8.0", "www.samsung.com");

                mobilePhoneDAO.insert(mobilePhone1);
                mobilePhoneDAO.insert(mobilePhone2);
            });
        }
    };
}
