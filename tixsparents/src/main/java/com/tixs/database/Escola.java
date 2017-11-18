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

    public Endereco endereco;

    public Escola() {
        endereco = new Endereco();
    }

    public Escola(String nome, String bairro)
    {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override



    public String toString() {
        return nome + "," + endereco.toString();
    }
}
