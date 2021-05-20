package edu.tomm.camm.sdlab5.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Corso {
    private final String corsoDiLaurea;
    private final List<Esame> esami;

    public Corso(String corsoDiLaurea, List<Esame> esami) {
        this.corsoDiLaurea = corsoDiLaurea;
        this.esami = esami;
    }

    public Corso(String corsoDiLaurea) {
        this.corsoDiLaurea = corsoDiLaurea;
        this.esami = new ArrayList<>();
    }

    public void aggiungiEsame(Esame esame){
        assert esame != null;
        esami.add(esame);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corso corso = (Corso) o;
        return corsoDiLaurea.equals(corso.corsoDiLaurea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corsoDiLaurea);
    }

    @Override
    public String toString() {
        return "Corso{" +
                "corsoDiLaurea='" + corsoDiLaurea + '\'' +
                ", esami=" + esami +
                '}';
    }

    public String getCorsoDiLaurea() {
        return corsoDiLaurea;
    }

    public List<Esame> getEsami() {
        return esami;
    }
}
