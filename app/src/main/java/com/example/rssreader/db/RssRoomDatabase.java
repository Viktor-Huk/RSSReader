package com.example.rssreader.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rssreader.App;
import com.example.rssreader.db.dao.RssDao;
import com.example.rssreader.db.entity.ArticleEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ArticleEntity.class}, version = 1, exportSchema = false)
public abstract class RssRoomDatabase extends RoomDatabase {

    private static volatile RssRoomDatabase INSTANCE;
    public static final ExecutorService databaseExecutor = Executors.newSingleThreadExecutor();

    public static RssRoomDatabase getDatabase() {
        if (INSTANCE == null) {
            synchronized (RssRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            App.getInstance(),
                            RssRoomDatabase.class,
                            "database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract RssDao getRssDao();
}
