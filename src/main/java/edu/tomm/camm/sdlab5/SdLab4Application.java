package edu.tomm.camm.sdlab5;

import edu.tomm.camm.sdlab5.entities.Corso;
import edu.tomm.camm.sdlab5.entities.Esame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SdLab4Application {
    public static List<Corso> corsi;

    public static void main(String[] args) {
        // Fit della lista prima di fare il run della webapp
        corsi = new ArrayList<>();
        Corso cs1 = new Corso("Informatica");
        cs1.aggiungiEsame(new Esame("Sistemi Distribuiti", 8));
        cs1.aggiungiEsame(new Esame("Programmazione 1", 8));
        Corso cs2 = new Corso("Fisica");
        cs2.aggiungiEsame(new Esame("Analisi 1", 8));
        corsi.add(cs1);
        corsi.add(cs2);
        SpringApplication.run(SdLab4Application.class, args);
    }

}
