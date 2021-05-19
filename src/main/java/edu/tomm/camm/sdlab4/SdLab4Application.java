package edu.tomm.camm.sdlab4;

import edu.tomm.camm.sdlab4.entities.Corso;
import edu.tomm.camm.sdlab4.entities.Esame;
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
        Corso cs = new Corso("Informatica");
        cs.aggiungiEsame(new Esame("Sistemi Distribuiti", 8));
        corsi.add(cs);
        SpringApplication.run(SdLab4Application.class, args);
    }

}
