package com.cmms.servicedesk.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class User extends org.springframework.security.core.userdetails.User {

    private String Nombre;
    private Integer Id;

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities, String nombre, Integer id) {
        super(username, password, authorities);
        this.Nombre = nombre;
        this.Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
