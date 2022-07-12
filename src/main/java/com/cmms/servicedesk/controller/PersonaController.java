package com.cmms.servicedesk.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cmms.servicedesk.model.PerfilPersona;
import com.cmms.servicedesk.model.Persona;
import com.cmms.servicedesk.model.User;
import com.cmms.servicedesk.service.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping("/personas")
    public ResponseEntity<List<Persona>> findAll(){
        return ResponseEntity.ok(personaService.findAll());
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<Persona> findById(@PathVariable("id") Integer idPersona){
        return personaService.findById(idPersona)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/personas/dni/{dni}")
    public ResponseEntity<Persona> findByDni(@PathVariable("dni") String dni){
        return personaService.findByDni(dni)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/personas")
    public ResponseEntity<Persona> create(@Valid @RequestBody Persona persona){
        return new ResponseEntity<>(personaService.create(persona), HttpStatus.CREATED);
    }

    @PostMapping("/personas/register")
    public ResponseEntity<Persona> register(@Valid @RequestBody Persona persona){
        return new ResponseEntity<>(personaService.create(persona), HttpStatus.CREATED);
    }

    @PutMapping("/personas")
    public ResponseEntity<Persona> update(@Valid @RequestBody Persona persona){
        return personaService.findById(persona.getIdpersona())
                .map(c->ResponseEntity.ok(personaService.update(persona)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/personas/{id}")
    public ResponseEntity<Persona> updateActivo(@PathVariable("id") Integer idPersona){
        return personaService.findById(idPersona)
                .map(c -> {
                    char activo = c.getActivo();
                    if(activo == 'S'){
                        c.setActivo('N');
                    }else {c.setActivo('S');}
                    personaService.update(c);
                    return ResponseEntity.ok(c);
                })
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
    /*@PostMapping("/register-persona/usuario-comun")
    public void registerPerson(@Valid @RequestBody Persona persona,
                               HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        personaService.create(persona);
        User user = (User)authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt (new Date(System.currentTimeMillis()+60*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token=JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
                .withIssuer (request.getRequestURL().toString())
                .sign(algorithm);
        response.setHeader("access_token", access_token);
        response.setHeader("refresh_token", refresh_token);
        Map<String, String> tokens=new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        tokens.put("rol", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).toString());
        tokens.put("name", user.getNombre());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }*/
    @GetMapping("/refreshtoken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String dni = decodedJWT.getSubject();
                Persona usuario = personaService.findByDni(dni).get();
                Collection<PerfilPersona> perfilPersonas = new ArrayList<>();
                perfilPersonas.add(usuario.getPerfilPersona());
                String access_token=JWT.create()
                        .withSubject(usuario.getDni())
                        .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",perfilPersonas.stream().map(PerfilPersona::getPerfil).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens=new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                tokens.put("rol", perfilPersonas.stream().map(PerfilPersona::getPerfil).collect(Collectors.toList()).toString());
                tokens.put("name", usuario.getNombre()+usuario.getApellido());
                tokens.put("identificador", usuario.getIdpersona().toString());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error=new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
