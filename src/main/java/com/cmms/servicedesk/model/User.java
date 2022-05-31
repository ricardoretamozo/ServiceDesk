package com.cmms.servicedesk.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class User extends org.springframework.security.core.userdetails.User {

    private String Nombre;

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities, String nombre) {
        super(username, password, authorities);
        this.Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

}
