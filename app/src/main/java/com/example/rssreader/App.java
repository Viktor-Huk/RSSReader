package com.example.rssreader;

import android.app.Application;

public class App extends Application {

    private static App INSTANCE;

    public static App getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new App();
        }
        return INSTANCE;
    }
}
