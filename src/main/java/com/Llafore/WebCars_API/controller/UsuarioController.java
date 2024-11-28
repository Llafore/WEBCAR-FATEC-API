package com.Llafore.WebCars_API.controller;

import com.Llafore.WebCars_API.entity.Usuario;
import com.Llafore.WebCars_API.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UsuarioController  {
    @Autowired
    UsuarioRepository repository;

    @PostMapping("/api/usuario")
    public ResponseEntity<String> gravar(@RequestBody Usuario entity){
        repository.save(entity);
        String mensagem = "Usuario " + entity.getNome() + " foi cadastrado com exito!";
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }

//    @GetMapping("/api/usuario/{id}")
//    public Usuario carregar(@PathVariable int id){
//        Optional<Usuario> entity = repository.findById(id);
//        if(entity.isPresent()){
//            return entity.get();
//        } else {
//            return null;
//        }
//    }

    @PostMapping("/api/usuario/login")
    public ResponseEntity<Usuario> fazerLogin(@RequestBody Usuario entity){
        Optional<Usuario> retorno = repository.login(entity.getEmail(), entity.getSenha());
        if(retorno.isPresent()){
            if(retorno.get().getSenha().equals(entity.getSenha())){
                return ResponseEntity.ok(retorno.get());
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/api/usuario/esqueci")
    public Usuario recuperaSenha(@RequestBody Usuario entity){
        Optional<Usuario> retorno = repository.recuperaSenha(entity.getCpf());
        if(retorno.isPresent()){
            return retorno.get();
        } else {
            return null;
        }
    }
}
