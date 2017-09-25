package com.tixs.database;

/**
 * Created by rodolfo on 24/09/17.
 */

public class Ponto {
    private String nome;

    public Ponto (){
        nome = "";
    }

    public Ponto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ponto ponto = (Ponto) o;

        return nome.equals(ponto.nome);

    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
