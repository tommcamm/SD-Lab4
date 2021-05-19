package edu.tomm.camm.sdlab4.restservice;

import edu.tomm.camm.sdlab4.SdLab4Application;
import edu.tomm.camm.sdlab4.entities.Corso;
import edu.tomm.camm.sdlab4.entities.Esame;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManageCorso {

    @GetMapping("/api")
    public List<Corso> listaCorsi() {
        return SdLab4Application.corsi;
    }

    @GetMapping("/api/{IDCorsoDiLaurea}/esami")
    public List<Esame> listaEsami(@PathVariable String IDCorsoDiLaurea) {
        Corso cs = SdLab4Application.corsi
                .stream()
                .filter(c -> c.getCorsoDiLaurea().equals(IDCorsoDiLaurea))
                .findFirst()
                .orElse(null);

        if (cs != null)
            return cs.getEsami();
        else
            return null;
    }
}
