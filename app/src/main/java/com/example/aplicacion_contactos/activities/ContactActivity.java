package com.example.aplicacion_contactos.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.aplicacion_contactos.R;
import com.example.aplicacion_contactos.adapters.ContactAdapter;
import com.example.aplicacion_contactos.models.Contacto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ContactActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Contacto>>, AdapterView.OnItemClickListener {

    private Realm realm;
    private FloatingActionButton fab;
    private ContactAdapter adapter;
    private RealmResults<Contacto> contactos;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //REALM
        realm = Realm.getDefaultInstance();
        //CONSULTA DE LOS CONTACTOS
        contactos = realm.where(Contacto.class).findAll();
        contactos.addChangeListener(this);

        adapter = new ContactAdapter(this, R.layout.list_view_contact_item, contactos);
        listView =  (ListView) findViewById(R.id.listViewContact);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        fab = (FloatingActionButton) findViewById(R.id.fabAddContact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createContact();
            }
        });

        registerForContextMenu(listView);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onChange(RealmResults<Contacto> contactos) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent= new Intent(ContactActivity.this, ViewContactActivity.class);
        //ENVIAMOS AL ACTIVITY LOS DATOS DEL CONTACTO
        intent.putExtra("id", contactos.get(position).getId());
        intent.putExtra("nombre", contactos.get(position).getNombre());
        intent.putExtra("descripcion", contactos.get(position).getDescripcion());
        intent.putExtra("telefono", contactos.get(position).getTelefono());
        intent.putExtra("correo", contactos.get(position).getCorreo());
        startActivity(intent);
    }

    private void createContact() {
        Intent intentViewContact = new Intent(ContactActivity.this, CreateContactActivity.class);
        startActivity(intentViewContact);
    }

    //  METODOS QUE HAY QUE SOBREESCRIBIR

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_delete_item, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.delete_contact:
                borrarContacto(contactos.get(info.position));
        }
        return super.onContextItemSelected(item);
    }

    private void borrarContacto(Contacto contacto)
    {
        realm.beginTransaction();
        contacto.deleteFromRealm();
        realm.commitTransaction();
    }
}
