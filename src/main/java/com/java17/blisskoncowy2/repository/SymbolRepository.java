package com.java17.blisskoncowy2.repository;

import com.java17.blisskoncowy2.model.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SymbolRepository extends JpaRepository<Symbol, Long> {
    List<Symbol> findByOpisenContaining(String text);
    Optional<Symbol> findByOpisen(String opisEn);

    @Query(value = "select * from symbol where opisen like :opis%", nativeQuery = true)
    Optional<Symbol> findByOpis(@Param("opis")String opis);
}
