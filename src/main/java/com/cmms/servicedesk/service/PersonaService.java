package com.cmms.servicedesk.service;

import com.cmms.servicedesk.model.Persona;
import com.cmms.servicedesk.model.User;
import com.cmms.servicedesk.repository.IPersonaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PersonaService implements IPersonaService, UserDetailsService {



    @Autowired
    private IPersonaRepository personaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> findById(Integer id) {
        return personaRepository.findById(id);
    }

    @Override
    public Persona create(Persona cliente) {
        log.info("Se creo un nuevo usuario");
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return personaRepository.save(cliente);
    }

    @Override
    public Persona update(Persona cliente) {
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return personaRepository.save(cliente);
    }

    @Override
    public void delete(Integer id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Optional<Persona> findByDni(String dni) {
        return personaRepository.findByDni(dni);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Persona usuario = personaRepository.findByDni(username).get();
        if (usuario == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //usuario.getPerfilPersona().forEach(perfilPersona -> authorities.add(new SimpleGrantedAuthority(perfilPersona.getNombre())));
        authorities.add(new SimpleGrantedAuthority(usuario.getPerfilPersona().getPerfil()));
        return new User(usuario.getDni(), usuario.getPassword(),authorities, usuario.getNombre()+" "+usuario.getApellido(), usuario.getIdpersona());
    }
}
