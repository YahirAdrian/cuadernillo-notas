package com.example.aplicacionnotas.app;

import android.app.Application;

import com.example.aplicacionnotas.models.Board;
import com.example.aplicacionnotas.models.Note;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApplication extends Application {
    public static AtomicInteger BoardId = new AtomicInteger();
    public static AtomicInteger NoteId = new AtomicInteger();

    @Override
    public void onCreate()
    {
        super.onCreate();
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();

        Realm.setDefaultConfiguration(config);

        Realm realm = Realm.getDefaultInstance();
        BoardId = getIdByTable(realm, Board.class);
        NoteId = getIdByTable(realm, Note.class);
        realm.close();
        //ESTO PUEDE QUE ESTE MAL
    }

    private void setUpRealmConfig()
    {

    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass)
    {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() >0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}
