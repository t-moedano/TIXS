package com.tixs.database;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * @author Thauany Moedano
 */

@IgnoreExtraProperties
public class Escola
{
    public String nome;

    public String id;

    /**
     * Construtor default
     */
    public Escola() {

    }

    /**
     * @param nome
     * @param bairro
     */
    public Escola(String nome, String bairro)
    {
        this.nome = nome;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return nome;
    }
}
