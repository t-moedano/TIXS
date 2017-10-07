package com.tixs.database;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by moeda on 19/09/2017.
 */

@IgnoreExtraProperties
public class Escola
{
    public String nome;
    public String bairro;
    @Exclude
    public String id;

    public Escola() {

    }

    public Escola(String nome, String bairro)
    {
        this.nome = nome;
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return nome + ", " + bairro;
    }
}
