package com.tixs.database;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * @author Thauany Moedano
 * Classe ADO que representa uma criança
 */

@IgnoreExtraProperties
public class Crianca {
    @Exclude
    public String id;
    public String nome;
    public String sobrenome;
    public String horarioEntrada;
    public String horarioSaida;
    public String escolaID;
    public Escola escola;
    public String vanID;
    public String responsavelID;

    /**
     * Construtor default
     */
    public Crianca()
    {

    }

    /**
     *
     * @param nome
     * @param sobrenome
     * @param horarioEntrada
     * @param horarioSaida
     * @param escola
     * @param responsavel
     */
    public Crianca(String nome, String sobrenome, String horarioEntrada, String horarioSaida,
                   Escola escola, Responsavel responsavel)
    {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
        this.escola = escola;
        this.escolaID = escola.id;
        this.responsavelID = responsavel.id;
    }

    /**
     *
     * @param escola
     */
    public void setEscola(Escola escola)
    {
        this.escolaID = escola.id;
        this.escola = escola;
    }

    /**
     *
     * @param responsavel
     */
    public void setResponsavel(Responsavel responsavel)
    {
        this.responsavelID = responsavel.id;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return new StringBuilder().append(nome).append(" ").append(sobrenome).toString();
    }
}
