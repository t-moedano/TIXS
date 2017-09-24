package com.tixs.database;

import java.util.ArrayList;

/**
 * Created by moeda on 19/09/2017.
 */

public class Condutor
{
    private String id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String rua;
    private String bairro;
    private String numero;
    private String cep;
    private String modelo;
    private String placa;
    private ArrayList<Escola> lista_de_escolas;
    private ArrayList<Bairro> lista_de_bairros;

    public Condutor(String id, String nome, String sobrenome, String cpf,
                    String telefone, String rua, String bairro, String numero, String cep,
                    String modelo, String placa)
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
        lista_de_escolas = new ArrayList<Escola>();
        lista_de_bairros = new ArrayList<Bairro>();
    }

    public Condutor()
    {
        // Default constructor required for calls to DataSnapshot.getValue(Responsavel.class)
    }
}
