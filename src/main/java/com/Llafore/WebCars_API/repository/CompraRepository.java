package com.Llafore.WebCars_API.repository;

import com.Llafore.WebCars_API.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends
        JpaRepository<Compra,Integer> {}
