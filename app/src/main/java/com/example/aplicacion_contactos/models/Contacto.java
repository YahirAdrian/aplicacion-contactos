package com.example.aplicacion_contactos.models;

import com.example.aplicacion_contactos.app.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Contacto extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private String nombre;
    @Required
    private String descripcion;
    @Required
    private String correo;
    @Required
    private String telefono;

    public Contacto(){

    }
    //LOS DATOS PARA NUESTRO CONTACTO SON: NOMBRE, DESCRIPCION, CORREO Y  TELEFONO
    public Contacto(String nombre, String descripcion, String correo, String telefono)
    {
        this.id = MyApplication.contact_id.incrementAndGet();
        this.nombre = nombre;
        this.correo = correo;
        this.descripcion = descripcion;
        this.telefono = telefono;

    }

    //METODOS GETTERS
    public int getId()
    {
        return id;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getCorreo()
    {
        return correo;
    }

    //METODOS SETTERS

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

}
