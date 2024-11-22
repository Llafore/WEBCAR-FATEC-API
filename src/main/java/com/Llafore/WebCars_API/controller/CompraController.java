package com.Llafore.WebCars_API.controller;

import com.Llafore.WebCars_API.entity.Compra;
import com.Llafore.WebCars_API.entity.Usuario;
import com.Llafore.WebCars_API.entity.Veiculo;
import com.Llafore.WebCars_API.repository.CompraRepository;
import com.Llafore.WebCars_API.repository.UsuarioRepository;
import com.Llafore.WebCars_API.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @PostMapping
    public ResponseEntity<String> realizarPedido(@RequestBody Compra entity) {
        repository.save(entity);
        Usuario usuario = usuarioRepository.findById(entity.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Veiculo veiculo = veiculoRepository.findById(entity.getVeiculo().getId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));

        entity.setUsuario(usuario);
        entity.setVeiculo(veiculo);

        String mensagem = "Pedido " + entity.getId() + " foi solicitado com sucesso! \n" +
                "Em breve, um de nossos vendedores vão entrar em contato \n" +
                "Pelo telefone " + entity.getUsuario().getTelefone() +
                " ou pelo email " + entity.getUsuario().getEmail();
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }
}

