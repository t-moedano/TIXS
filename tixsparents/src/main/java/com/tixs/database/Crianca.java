package com.tixs.database;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by moeda on 19/09/2017.
 */

@IgnoreExtraProperties
public class Crianca {

    public String id;
    public String nome;
    public String sobrenome;
    public String horarioEntrada;
    public String horarioSaida;
    public String escolaID;
    public Escola escola;
    public String vanID;
    public String responsavelID;
    public Boolean confirma_ida;
    public Boolean confirma_volta;

    /**
     * Construtor default
     */
    public Crianca() {
        confirma_ida = true;
        confirma_volta = true;
    }

    /**
     * @param nome
     * @param sobrenome
     * @param horarioEntrada
     * @param horarioSaida
     * @param escola
     * @param responsavel
     */
    public Crianca(String nome, String sobrenome, String horarioEntrada, String horarioSaida,
                   Escola escola, Responsavel responsavel) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
        this.escola = escola;
        this.escolaID = escola.id;
        this.responsavelID = responsavel.id;
    }

    /**
     * @param escola
     */
    public void setEscola(Escola escola) {
        this.escolaID = escola.id;
        this.escola = escola;
    }

    /**
     *
     * @param responsavel
     */
    public void setResponsavel(Responsavel responsavel) {
        this.responsavelID = responsavel.id;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return new StringBuilder().append(nome).append(" ").append(sobrenome).toString();
    }
}
