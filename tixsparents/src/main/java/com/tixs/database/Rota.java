package com.tixs.database;

import com.google.firebase.database.Exclude;

/**
 * Created by aline on 24/09/17.
 */

public class Rota {
    @Exclude
    public String id;
    public String nome;

    public Rota() {
        nome = "";
    }

    public Rota(String nome) {
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

        Rota rota = (Rota) o;

        return nome.equals(rota.nome);

    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
