package com.java17.blisskoncowy2.controller;
import com.java17.blisskoncowy2.model.Symbol;
import com.java17.blisskoncowy2.service.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SymbolController {

    @Autowired
    private SymbolService symbolService;

    @GetMapping("/tlumacz")
    public Symbol textToSymbol(@RequestParam (name = "opis_en") String opis) {
        Map<String,Long> symbolList = symbolService.szukajPoOpisie(opis);
        //Symbol znaleziony = symbolList.entrySet().forEach(stringLongEntry ->new Symbol());

        return null;
    }

    List<Symbol> symbolList = new ArrayList<Symbol>();
// logic to build student data
//model.addAttribute("symbols", symbolList);

}
