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

@SpringBootApplication
public class ServiceDeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDeskApplication.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
