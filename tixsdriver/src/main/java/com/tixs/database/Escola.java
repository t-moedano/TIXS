package com.tixs.database;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * @author Thauany Moedano.
 * Classe ADO para representar uma escola
 */

@IgnoreExtraProperties
public class Escola
{
    public String nome;

    public String id;

    public Escola() {

    }

    public Escola(String nome, String bairro)
    {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
