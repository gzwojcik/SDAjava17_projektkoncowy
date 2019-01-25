package com.java17.blisskoncowy2.service;

import com.java17.blisskoncowy2.model.Symbol;
import com.java17.blisskoncowy2.repository.SymbolRepository;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.nio.ByteBuffer;

@Service
public class SymbolService {
    @Autowired
    private SymbolRepository symbolRepository;

    public Map<String, Long> szukajPoOpisie(String text) {
        String[] slowaLista;
        slowaLista = text.split(" ");

        Map<String, Long> stringSymbolMap = new LinkedHashMap<>();
        for (int i = 0; i < slowaLista.length; i++) {
            String znalezioneslowo = slowaLista[i];
            Symbol symbol = znajdzSymbolWBazie(znalezioneslowo);
            if(symbol!= null) {
                stringSymbolMap.put(slowaLista[i], symbol.getObrazek().getId());
            }else{
                stringSymbolMap.put(slowaLista[i], null);
            }
        }


        return stringSymbolMap;
    }


    public Map<String, String> szukajPoOpisieZObrazek(String text) {
        String[] slowaLista;
        slowaLista = text.split(" ");

        Map<String, String> stringSymbolMap = new LinkedHashMap<>();
        for (int i = 0; i < slowaLista.length; i++) {
            String znalezioneslowo = slowaLista[i];
            Symbol symbol = znajdzSymbolWBazie(znalezioneslowo);
            if(symbol!= null) {
                stringSymbolMap.put(slowaLista[i], Base64.encode(symbol.getObrazek().getObrazek()));
            }else{
                stringSymbolMap.put(slowaLista[i], null);
            }
        }


        return stringSymbolMap;
    }

    private Symbol znajdzSymbolWBazie(String fraza) {
        // 1 szukanie po pełne dopasowanie
        Optional<Symbol> symbolOptional = symbolRepository.findByOpisen(fraza);
        if (symbolOptional.isPresent()) {
            return symbolOptional.get();
        }
        symbolOptional = symbolRepository.findByOpis(fraza.toLowerCase()+"-");
        if (symbolOptional.isPresent()) {
            return symbolOptional.get();
        }
        List<Symbol> symbols = symbolRepository.findByOpisenContaining(fraza.toLowerCase() + ",");
        if (!symbols.isEmpty()) {
            return symbols.get(0);
        }
        symbols = symbolRepository.findByOpisenContaining(fraza.toLowerCase() + " ");
        if (!symbols.isEmpty()) {
            return symbols.get(0);
        }
        return null;
    }

}


// zwrocic id obrazka z kazdego obiektu w tej mapie

