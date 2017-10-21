package com.tixs.database;

import com.google.firebase.database.Exclude;

/**
 * Classe ADO que representa um bairro.
 */

public class Bairro
{
    @Exclude
    public String id;
    public String nome;

    public Bairro()
    {
        nome = "";
    }

    public Bairro(String nome)
    {
        this.nome = nome;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Bairro rota = (Bairro) o;

        return nome.equals(rota.nome);

    }

    @Override
    public int hashCode()
    {
        return nome.hashCode();
    }

    @Override
    public String toString()
    {
        return nome;
    }
}
