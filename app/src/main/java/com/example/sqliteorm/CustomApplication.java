package com.example.sqliteorm;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class CustomApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        Stetho.Initializer initializer = initializerBuilder.build();

        Stetho.initialize(initializer);
    }
}

