package com.tixs.database;

public class Responsavel
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


    public Responsavel(String id, String nome, String sobrenome, String cpf,
                       String telefone, String rua, String bairro, String numero, String cep)
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
    }

    public Responsavel()
    {
        // Default constructor required for calls to DataSnapshot.getValue(Responsavel.class)
    }



}
