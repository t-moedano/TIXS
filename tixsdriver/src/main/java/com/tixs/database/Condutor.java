package com.tixs.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moeda on 19/09/2017.
 */

public class Condutor
{
    public String id;
    public String nome;
    public String sobrenome;
    public String cpf;
    public String telefone;
    public String rua;
    public String bairro;
    public String numero;
    public String cep;
    public String modelo;
    public String placa;
    public List bairros;
    public List escolas;

    public Condutor(String id, String nome, String sobrenome, String cpf,
                    String telefone, String rua, String bairro, String numero, String cep,
                    String modelo, String placa, List bairros, List escolas)
    {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.modelo = modelo;
        this.placa = placa;
        this.bairros = bairros;
        this.escolas = escolas;
    }

    public Condutor()
    {
        // Default constructor required for calls to DataSnapshot.getValue(Responsavel.class)
    }
}
