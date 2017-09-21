package com.tixs.database;

/**
 * Created by moeda on 19/09/2017.
 */

public class Crianca
{
    public String nome;
    public String sobrenome;
    public String horarioEntrada;
    public String horarioSaida;
    public Escola escola;

    public Crianca()
    {

    }

    public Crianca(String nome, String sobrenome, String horarioEntrada, String horarioSaida, Escola escola)
    {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
        this.escola = escola;

    }
}
