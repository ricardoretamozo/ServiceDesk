package com.cmms.servicedesk.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @NotNull(message = "El campo usuario no puede ser null")
    //@Size(min = 8 , message = "Ingresa un DNI valido")
    @Column(name = "s_usuario" , length = 8 , columnDefinition = "char(8)",unique = true)
    private String usuario;

    @NotNull(message = "El campo password no puede ser null")
    @NotBlank(message = "El campo password minimo tiene que tener 7 carateres")
    @Size(min = 7 , max = 256, message = "El campo password minimo tiene que tener 7 carateres")
    @Column(name = "s_password" , length = 256)
    private String password;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @Column(name = "s_correo", length = 80)
    private String correo;

    @Column(name = "s_celular", length = 15)
    private String celular;

    @Column(name = "f_fecha_nacimiento")
    private LocalDate fecha;

    //@NotNull(message = "El campo sexo no puede ser null")
    //@NotBlank(message = "El campo sexo no puede ser vacio")
    @Column(name = "s_sexo", length = 1, columnDefinition = "char(1)")
    private char sexo;

    @NotNull(message = "El campo estado no puede ser null")
    @Column(name = "s_activo", length = 1, columnDefinition = "char(1)")
    private char activo;

    @ManyToOne
    @JoinColumn(name = "n_id_perfil", nullable = false,columnDefinition = "smallint")
    private PerfilPersona perfilPersona;

}
