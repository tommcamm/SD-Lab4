package edu.tomm.camm.sdlab5.restservice;

import edu.tomm.camm.sdlab5.SdLab5Application;
import edu.tomm.camm.sdlab5.entities.AjaxResponseBody;
import edu.tomm.camm.sdlab5.entities.Corso;
import edu.tomm.camm.sdlab5.entities.Esame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManageCorso {

    @GetMapping("/api")
    public List<Corso> listaCorsi() {
        return SdLab5Application.corsi;
    }

    @GetMapping("/api/{IDCorsoDiLaurea}/esami")
    public List<Esame> listaEsami(@PathVariable String IDCorsoDiLaurea) {
        Corso cs = SdLab5Application.corsi
                .stream()
                .filter(c -> c.getCorsoDiLaurea().equals(IDCorsoDiLaurea))
                .findFirst()
                .orElse(null);

        if (cs != null)
            return cs.getEsami();
        else
            return null;
    }

    @GetMapping("/api/{IDCorsoDiLaurea}/{IDEsame}")
    public Esame getEsame(@PathVariable String IDCorsoDiLaurea, @PathVariable String IDEsame) {
        List<Esame> esami = listaEsami(IDCorsoDiLaurea);
        if (esami != null) {
            return esami.stream()
                    .filter(c -> c.getNome().equals(IDEsame))
                    .findFirst()
                    .orElse(null);
        } else {
            return null;
        }
    }

    @PostMapping("/api")
    public ResponseEntity<?> aggiuntaEsame(@RequestParam String nomeCorso,
                                           @RequestParam String nomeEsame,
                                           @RequestParam String cfu) {
        List<Corso> listaCorsi = SdLab5Application.corsi;
        AjaxResponseBody body = new AjaxResponseBody();

        if(listaCorsi.contains(new Corso(nomeCorso))){
            List<Esame> listaEsami = listaCorsi.get(listaCorsi.indexOf(new Corso(nomeCorso))).getEsami();
            if(listaEsami.contains(new Esame(nomeEsame, Integer.parseInt(cfu)))) {
                body.setMsg("exam_already_inserted");
                return ResponseEntity.badRequest().body(body);
            }
            listaEsami.add(new Esame(nomeEsame, Integer.parseInt(cfu)));
            body.setMsg("success");
            return ResponseEntity.ok(body);
        } else {
            body.setMsg("course_not_found");
            return ResponseEntity.badRequest().body(body);
        }
    }

    @PutMapping("/api/{IDCorsoDiLaurea}/{IDEsame}")
    public ResponseEntity<?> modificaEsame(@PathVariable String IDCorsoDiLaurea,
                                           @PathVariable String IDEsame,
                                           @RequestParam String nomeEsame,
                                           @RequestParam String cfu
                                           ) {
        AjaxResponseBody body = new AjaxResponseBody();
        List<Corso> listaCorsi = SdLab5Application.corsi;

        if(listaCorsi.contains(new Corso(IDCorsoDiLaurea))){
            List<Esame> listaEsami = listaCorsi.get(listaCorsi.indexOf(new Corso(IDCorsoDiLaurea))).getEsami();

            if(listaEsami.contains(new Esame(IDEsame, 0))) {
                listaEsami.remove(new Esame(IDEsame, 0));
                listaEsami.add(new Esame(nomeEsame, Integer.parseInt(cfu)));

                return ResponseEntity.ok(new Esame(nomeEsame, Integer.parseInt(cfu)));
            } else {
                body.setMsg("exam_not_found");
                return ResponseEntity.badRequest().body(body);
            }

        } else {
            body.setMsg("course_not_found");
            return ResponseEntity.badRequest().body(body);
        }
    }
}
