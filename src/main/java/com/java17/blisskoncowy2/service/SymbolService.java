package com.java17.blisskoncowy2.service;

import com.java17.blisskoncowy2.model.Symbol;
import com.java17.blisskoncowy2.repository.SymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.nio.ByteBuffer;

@Service
public class SymbolService {
    @Autowired
    private SymbolRepository symbolRepository;

    public Map<String, Long> szukajPoOpisie(String text) {
        String[] slowaLista;
       slowaLista= text.split(" ");

       Map<String , Long> stringSymbolMap= new HashMap<>();
        for (int i=0;i<slowaLista.length;i++) {
            String znalezioneslowo = slowaLista[i];
            List<Symbol> symbol=  symbolRepository.findByOpisenContaining(znalezioneslowo);
            if (!symbol.isEmpty()){
                stringSymbolMap.put(slowaLista[i], symbol.get(0).getObrazek().getId());
            }else {
                stringSymbolMap.put(slowaLista[i], null);
            }
        }


        return stringSymbolMap;
    }




    public Map<String, Long> szukajPoOpisieZObrazek(String text) {
        String[] slowaLista;
        slowaLista= text.split(" ");

        Map<String , Long> stringSymbolMap= new HashMap<>();
        for (int i=0;i<slowaLista.length;i++) {
            String znalezioneslowo = slowaLista[i];
            List<Symbol> symbol=  symbolRepository.findByOpisenContaining(znalezioneslowo);
            if (!symbol.isEmpty()){
                byte[] byteTable=symbol.get(0).getObrazek().getObrazek();
                Long longbuffer;
                longbuffer=(ByteBuffer.wrap(byteTable.getLong()));
                stringSymbolMap.put(slowaLista[i], symbol.get(0).getObrazek().getObrazek());
            }else {
                stringSymbolMap.put(slowaLista[i], null);
            }
        }


        return stringSymbolMap;
    }

}


// zwrocic id obrazka z kazdego obiektu w tej mapie

