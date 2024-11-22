package com.Llafore.WebCars_API.controller;

import com.Llafore.WebCars_API.entity.Veiculo;
import com.Llafore.WebCars_API.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VeiculoContoller {
    @Autowired
    VeiculoRepository repository;

    @PostMapping("/api/veiculo/recentes")
    public ResponseEntity<List<Veiculo>> recentes(){
        List<Veiculo> veiculos = repository.ultimos50();
        if(veiculos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(veiculos);
    }

    @PostMapping("/api/veiculo/pesquisa")
    public ResponseEntity<List<Veiculo>>  pesquisa(@RequestBody Veiculo entity){
        List<Veiculo> veiculos = repository.pesquisaModeloMarca(entity.getModelo()); // O geter está apontado para modelo, mas a query executa esse parametro tanto para modelo, quanto para marca, GAMBIARRA
        if(veiculos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(veiculos);
    }

    @PostMapping("/api/veiculo/tipo")
    public ResponseEntity<List<Veiculo>>  retornaTipo(@RequestBody Veiculo entity){
        List<Veiculo> veiculos = repository.pesquisaTipo(entity.getTipo());
        if(veiculos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(veiculos);
    }

    @PostMapping("/api/veiculo/marca")
    public ResponseEntity<List<Veiculo>>  retornaMarca(@RequestBody Veiculo entity){
        List<Veiculo> veiculos = repository.pesquisaMarca(entity.getMarca());
        if(veiculos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(veiculos);
    }

    @PostMapping("/api/veiculo/modelo")
    public ResponseEntity<List<Veiculo>>  retornaModelo(@RequestBody Veiculo entity){
        List<Veiculo> veiculos = repository.pesquisaModelo(entity.getModelo());
        if(veiculos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(veiculos);
    }
}
