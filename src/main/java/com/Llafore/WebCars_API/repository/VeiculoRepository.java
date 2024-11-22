package com.Llafore.WebCars_API.repository;

import com.Llafore.WebCars_API.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends
        JpaRepository<Veiculo,Integer> {
    @Query(value = "select * from veiculos order by 1 desc limit 50", nativeQuery = true)
    List<Veiculo> ultimos50();

    @Query(value = "select * from veiculos where modelo like concat('%',?1,'%') or marca like concat('%',?1,'%')", nativeQuery = true)
    List<Veiculo> pesquisaModeloMarca(String parametro);

    @Query(value = "select * from veiculos where tipo = ?1", nativeQuery = true)
    List<Veiculo> pesquisaTipo(String parametro);

    @Query(value = "select * from veiculos where marca = ?1", nativeQuery = true)
    List<Veiculo> pesquisaMarca(String parametro);

    @Query(value = "select * from veiculos where modelo = ?1", nativeQuery = true)
    List<Veiculo> pesquisaModelo(String parametro);
}
