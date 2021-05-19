package edu.tomm.camm.sdlab4.restservice;

import edu.tomm.camm.sdlab4.SdLab4Application;
import edu.tomm.camm.sdlab4.entities.AjaxResponseBody;
import edu.tomm.camm.sdlab4.entities.Corso;
import edu.tomm.camm.sdlab4.entities.Esame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/api")
    public ResponseEntity<?> aggiuntaEsame(@RequestParam String nomeCorso,
                                           @RequestParam String nomeEsame,
                                           @RequestParam String cfu) {
        List<Corso> listaCorsi = SdLab4Application.corsi;
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
        List<Corso> listaCorsi = SdLab4Application.corsi;

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
