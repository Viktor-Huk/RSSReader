package com.example.rssreader.db;

import androidx.room.Room;

import com.example.rssreader.App;

public class DatabaseManager {

    private static RssRoomDatabase INSTANCE;

    public static RssRoomDatabase getDatabase() {

        if (INSTANCE == null) {

            RssRoomDatabase instance = Room.databaseBuilder(
                    App.getInstance(),
                    RssRoomDatabase.class,
                    "rss_database"
            ).build();

            INSTANCE = instance;
        }

        return INSTANCE;
    }
}
