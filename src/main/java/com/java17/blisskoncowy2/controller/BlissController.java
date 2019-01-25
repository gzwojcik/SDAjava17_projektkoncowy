package com.java17.blisskoncowy2.controller;


import com.java17.blisskoncowy2.service.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class BlissController {

    @Autowired
    private SymbolService symbolService;

    @GetMapping(path = "/tlumaczenie")
    public String getSymbols() {
        return "symbols";
    }

    @PostMapping("/tlumaczForm")//co tu ma byc?
    public String znajdzSymbolWgOpisu(String tekstDoTlumaczenia, Model model) {
        Map <String,Long> mapaTlumaczen =symbolService.szukajPoOpisie(tekstDoTlumaczenia);

        model.addAttribute("mapatlumaczen",mapaTlumaczen);

            return "stronaWynikowa";
    }

    // todo: drugi mapping (identyzcny) który woła ten sam serwis, ale drugą metodę, a na koncu wywoluje drugi html (do stworzenia)

    @GetMapping(path = "/tlumaczenieObrazek")
    public String getSymbols2() {
        return "symbolsObrazek";
    }

    @PostMapping("/tlumaczFormObrazek")//co tu ma byc?
    public String znajdzSymbolWgOpisuZobrazek(String tekstDoTlumaczenia, Model model) {
        Map<String, String> mapaTlumaczen = symbolService.szukajPoOpisieZObrazek(tekstDoTlumaczenia);

        model.addAttribute("mapatlumaczen", mapaTlumaczen);

        return "stronaWynikowaPic";
    }
}
