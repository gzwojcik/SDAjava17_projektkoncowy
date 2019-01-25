package com.java17.blisskoncowy2.repository;

import com.java17.blisskoncowy2.model.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SymbolRepository extends JpaRepository<Symbol, Long> {
    List<Symbol> findByOpisenContaining(String text);
}
