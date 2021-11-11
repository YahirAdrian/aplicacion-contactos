package com.example.aplicacion_contactos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplicacion_contactos.R;
import com.example.aplicacion_contactos.models.Contacto;

import io.realm.Realm;
import io.realm.RealmList;

public class CreateContactActivity extends AppCompatActivity {

    public TextView text_nombreContacto;
    public TextView text_descripcionContacto;
    public TextView text_telefonoContacto;
    public TextView text_correoContacto;
    public Button botonCrear;
    private Realm realm;
    private RealmList<Contacto> contactos;

    String nombreContacto, descripcionContacto, telefonoContacto, correoContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //FLECHA ATRAS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        realm = Realm.getDefaultInstance();

        //RECOGEMOS LOS ELEMENTOS DE LA UI
        text_nombreContacto = (TextView) findViewById(R.id.editNombreContacto);
        text_descripcionContacto = (TextView) findViewById(R.id.editDescripcionContacto);
        text_telefonoContacto = (TextView) findViewById(R.id.editTelefonoContacto);
        text_correoContacto = (TextView) findViewById(R.id.editCorreoContacto);
        botonCrear = (Button) findViewById(R.id.botonCrear);



        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectContactData();
                createContact(nombreContacto, descripcionContacto, telefonoContacto, correoContacto);
                finish();
                //Intent intentMenu = new Intent(CreateContactActivity.this, ContactActivity.class);
                //startActivity(intentMenu);
            }
        });

    }

    private void createContact(String nombre, String descripcion, String telefono, String correo)
    {
        realm.beginTransaction();
        Contacto contacto = new Contacto(nombre, descripcion, correo, telefono);
        realm.copyToRealm(contacto);
        realm.commitTransaction();
    }

    private void collectContactData()
    {
        //RECOGEMOS LOS VALORES DE LOS INPUTS
        nombreContacto =  text_nombreContacto.getText().toString().trim();
        descripcionContacto = text_descripcionContacto.getText().toString().trim();
        telefonoContacto = text_telefonoContacto.getText().toString().trim();
        correoContacto = text_correoContacto.getText().toString().trim();
    }

    //

}
