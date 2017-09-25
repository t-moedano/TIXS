package com.tixs.database;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

/**
 * Created by moeda on 19/09/2017.
 */

public class Condutor
{
    @Exclude private String id;
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
    private ArrayList<Escola> escolas;
    private ArrayList<Ponto> bairros;

    public String toString() {
        return nome;
    }

    public Condutor(String nome, String sobrenome, String cpf,
                    String telefone, String rua, String bairro, String numero, String cep,
                    String modelo, String placa)
    {
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
        escolas = new ArrayList<Escola>();
        bairros = new ArrayList<Ponto>();
    }

    public Condutor()
    {
        // Default constructor required for calls to DataSnapshot.getValue(Responsavel.class)
    }

    public boolean containsBairro(String bairro) {
        if (bairros == null) return false;
        for (Ponto p : bairros) {
            if(p.getNome().contains(bairro)) return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public ArrayList<Escola> getEscolas() {
        return escolas;
    }

    public void setEscolas(ArrayList<Escola> escolas) {
        this.escolas = escolas;
    }

    public ArrayList<Ponto> getBairros() {
        return bairros;
    }

    public void setBairros(ArrayList<Ponto> bairros) {
        this.bairros = bairros;
    }
}
