package com.tixs.database;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by moeda on 19/09/2017.
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
    @Exclude
    public Escola escola;
    public String vanID;
    @Exclude
    public Responsavel responsavel;
    public String responsavelID;

    public Crianca() {

    }

    public Crianca(String nome, String sobrenome, String horarioEntrada, String horarioSaida, Escola escola, Responsavel responsavel) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
        this.escola = escola;
        this.escolaID = escola.id;
        this.responsavel = responsavel;
        this.responsavelID = responsavel.id;
    }

    public void setEscola(Escola escola) {
        this.escolaID = escola.id;
        this.escola = escola;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavelID = responsavel.id;
        this.responsavel = responsavel;
    }
}