package com.ejemplo.recyclerpokemon;

/**
 * Created by SERGIO on 14/12/17.
 */

public class Pokemon {
    private int id;
    private String nombre;
    private String url;

    public Pokemon(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {

        return nombre.substring(0,1).toUpperCase()+nombre.substring(1);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
