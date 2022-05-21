package com.cmms.servicedesk;

import com.cmms.servicedesk.model.PerfilPersona;
import com.cmms.servicedesk.model.Persona;
import com.cmms.servicedesk.service.PerfilPersonaService;
import com.cmms.servicedesk.service.PersonaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class ServiceDeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDeskApplication.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    CommandLineRunner run(PerfilPersonaService perfilService, PersonaService personaService) {
        return args -> {
            PerfilPersona perfil1 = new PerfilPersona(17, "COORDINADOR INFORMATICO", "1", 'S');
            PerfilPersona perfil2 = new PerfilPersona(18, "ASISTENTE INFORMATICO", "1", 'S');
            PerfilPersona perfil3 = new PerfilPersona(19, "SOPORTE TECNICO", "1", 'S');
            PerfilPersona perfil4 = new PerfilPersona(20, "USUARIO COMUN", "1", 'S');
            perfilService.create(perfil1);
            perfilService.create(perfil2);
            perfilService.create(perfil3);
            perfilService.create(perfil4);

            personaService.create(new Persona(null, "Juan","apellido","73824465", "73824465", "cocacola",null,'M','A',perfil1));
            personaService.create(new Persona(null, "Juan1","apellido","83824465","83824465","cocacola",null,'M','A',perfil2));
            personaService.create(new Persona(null, "Juan2","apellido","93824465","93824465","cocacola",null,'M','A',perfil3));
            personaService.create(new Persona(null, "Juan3","apellido","13824465","13824465","cocacola",null,'M','A',perfil4));

        };
    }*/
}
