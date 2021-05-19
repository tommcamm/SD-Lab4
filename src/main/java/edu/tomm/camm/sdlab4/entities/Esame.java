package edu.tomm.camm.sdlab4.entities;

import java.util.Objects;

public class Esame {
    private final String nome;
    private final int cfu;

    public Esame(String nome, int cfu) {
        this.nome = nome;
        this.cfu = cfu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Esame esame = (Esame) o;
        return cfu == esame.cfu && nome.equals(esame.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cfu);
    }

    @Override
    public String toString() {
        return "Esame{" +
                "nome='" + nome + '\'' +
                ", cfu=" + cfu +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public int getCfu() {
        return cfu;
    }
}
