package com.example.aplicacion_contactos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacion_contactos.R;
import com.example.aplicacion_contactos.models.Contacto;

import io.realm.Realm;
import io.realm.RealmList;


public class ViewContactActivity extends AppCompatActivity {

    public TextView textNombre;
    public TextView textDescripcion;
    public TextView textTelefono;
    public TextView textCorreo;
    public Button botonLlamar;
    public Button botonCorreo;
    public Realm realm;
    //public RealmList<Contacto> contactos;
    public int contactId;
    public String nombre, descripcion, correo, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        //FLECHA ATRAS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //RECOGEMOS LOS EXTRAS QUE LE PASAMOS AL ACTIVITY
        realm =Realm.getDefaultInstance();
        if(getIntent().getExtras() !=null)
        {
            contactId = getIntent().getExtras().getInt("id");
            nombre = getIntent().getExtras().getString("nombre");
            descripcion = getIntent().getExtras().getString("descripcion");
            telefono = getIntent().getExtras().getString("telefono");
            correo = getIntent().getExtras().getString("correo");

        }

        //ENLAZAMOS LOS ITEMS DE LA UI

        textNombre = (TextView) findViewById(R.id.nombreContacto);
        textDescripcion = (TextView) findViewById(R.id.descripcionContacto);
        textTelefono = (TextView) findViewById(R.id.telefonoContacto);
        textCorreo = (TextView) findViewById(R.id.correoContacto);
        botonCorreo = (Button) findViewById(R.id.botonCorreo);
        botonLlamar = (Button) findViewById(R.id.botonLlamar);

        rellenarDatos();

        botonLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(telefono != "")
                {
                    String llamada = "tel:" + telefono;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(llamada)));
                }else{
                    Toast.makeText(ViewContactActivity.this, "El numero de telefono es invalido", Toast.LENGTH_LONG).show();
                }
            }
        });

        botonCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] destinatario = {correo};
                String [] cc ={""};
                Intent emailIntent =new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, destinatario);
                emailIntent.putExtra(Intent.EXTRA_CC, cc);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Escribe un mensaje...");

                try{
                    startActivity(Intent.createChooser(emailIntent, "Enviar email"));
                    finish();
                }catch (android.content.ActivityNotFoundException ex)
                {
                    Toast.makeText(ViewContactActivity.this, "Hubo un error", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void rellenarDatos()
    {
        textNombre.setText(nombre);
        textDescripcion.setText(descripcion);
        textTelefono.setText(telefono);
        textCorreo.setText(correo);
    }
}
