package com.example.aplicacion_contactos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplicacion_contactos.R;
import com.example.aplicacion_contactos.models.Contacto;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private Context contexto;
    private int layout;
    private List<Contacto> contactos;

    public ContactAdapter(Context contexto, int layout, List<Contacto> contactos)
    {
        this.contactos = contactos;
        this.contexto = contexto;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int position) {
        return contactos.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;


        if(convertView == null)
        {
            //INFLAMOS  LA VISTA CON NUESTRO LAYOUT
            convertView = LayoutInflater.from(contexto).inflate(layout, null);
            vh = new ViewHolder();
            //ENLAZAMOS LOS ELEMENTOS DE LA INTERFAZ
            vh.imagen_contacto = (ImageView) convertView.findViewById(R.id.imgContact);
            vh.nombre_contacto = (TextView) convertView.findViewById(R.id.contactName);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        Contacto contacto = contactos.get(position);
        vh.nombre_contacto.setText(contacto.getNombre());
        vh.imagen_contacto.setImageResource(R.drawable.icono_contacto);

        return convertView;
    }

    public class ViewHolder{
        TextView nombre_contacto;
        ImageView imagen_contacto;
    }
}
