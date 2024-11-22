package com.Llafore.WebCars_API.repository;

import com.Llafore.WebCars_API.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends
        JpaRepository<Usuario,Integer> {

    @Query(value = "select * from usuarios where email=?1 and senha=?2", nativeQuery = true)
    Optional<Usuario> login(String email, String senha);

    @Query(value = "select * from usuarios where cpf = ?1", nativeQuery = true)
    Optional<Usuario> recuperaSenha(String cpf);
}
