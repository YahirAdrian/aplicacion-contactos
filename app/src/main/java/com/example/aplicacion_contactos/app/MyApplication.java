package com.example.aplicacion_contactos.app;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

import com.example.aplicacion_contactos.R;
import com.example.aplicacion_contactos.models.Contacto;

import java.util.concurrent.atomic.AtomicInteger;

public class MyApplication extends Application {

    //TENEMOS QUE CREAR LAS IDS DE LOS CONTACTOS PARA REALM
    public static AtomicInteger contact_id = new AtomicInteger();

    @Override
    public void onCreate()
    {
        super.onCreate();
        //TENEMOS QUE INICIAR NUESTRO REALM
        Realm.init(getApplicationContext());
        RealmConfiguration configuracion_realm = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();

        //LE DAMOS NUESTRA CONFIGURACION A NUESTRO REALM
        Realm.setDefaultConfiguration(configuracion_realm);


        Realm realm = Realm.getDefaultInstance();
        contact_id = getIdByTable(realm, Contacto.class);
        realm.close();
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass)
    {
        RealmResults<T> results= realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}
