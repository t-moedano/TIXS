package com.tixs.database;

import com.google.firebase.database.Exclude;

/**
 * Classe ADO que representa um bairro.
 * author: Aline
 */

public class Bairro {
    @Exclude
    public String id;
    public String nome;

    public Bairro() {
        nome = "";
    }


    /**
     * Construtor do bairro com um nome
     *
     * @param nome
     */
    public Bairro(String nome) {
        this.nome = nome;
    }


    /**
     * @return nome do bairro
     */
    public String getNome() {
        return nome;
    }


    /**
     * @param nome do bairro
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Sobrescreve o método equals para comparar se um bairro é igual a outro
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;


        Bairro rota = (Bairro) o;

        return nome.equals(rota.nome);
    }

    /**
     * Define que o hash code do bairro será o nome
     * @return
     */
    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    @Override
    public String toString() {
        return nome;
    }
}
