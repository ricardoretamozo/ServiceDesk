package com.cmms.servicedesk.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "n_id_persona")
    private Integer idpersona;

    @NotNull(message = "El campo nombre no puede ser null")
    @NotBlank(message = "El campo nombre minimo tiene que tener 3 carateres")
    @Size(min = 3 , max = 80)
    @Column(name = "s_nombre" , length = 80)
    private String nombre;

    @NotNull(message = "El campo apellido no puede ser null")
    @NotBlank(message = "El campo apellido minimo tiene que tener 3 carateres")
    @Size(min = 3 , max = 80)
    @Column(name = "s_apellido" , length = 80)
    private String apellido;


    @NotNull(message = "El campo DNI no puede ser null")
    //@Size(min = 8 , message = "Ingresa un DNI valido")
    @Column(name = "s_dni" , length = 8 , columnDefinition = "char(8)",unique = true)
    private String dni;

    @NotNull(message = "El campo password no puede ser null")
    @NotBlank(message = "El campo password minimo tiene que tener 7 carateres")
    @Size(min = 7 , max = 256)
    @Column(name = "s_password" , length = 256)
    private String password;

    @Column(name = "f_fecha_nacimiento")
    private Date fecha;

    //@NotNull(message = "El campo sexo no puede ser null")
   // @NotBlank(message = "El campo sexo no puede ser vacio")
    @Column(name = "s_sexo", length = 1, columnDefinition = "char(1)")
    private char sexo;

    @NotNull(message = "El campo password no puede ser null")
    @Column(name = "s_activo", length = 1)
    private char activo;

    @ManyToOne
    @JoinColumn(name = "n_id_perfil", nullable = false,columnDefinition = "smallint")
    private PerfilPersona perfilPersona;

    public Persona() {
    }

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
//        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        formatofecha.
        this.fecha = fecha ;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }

    public PerfilPersona getPerfilPersona() {
        return perfilPersona;
    }

    public void setPerfilPersona(PerfilPersona perfilPersona) {
        this.perfilPersona = perfilPersona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(idpersona, persona.idpersona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpersona);
    }
}
