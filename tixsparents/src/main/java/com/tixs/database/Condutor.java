package com.tixs.database;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

/**
 * Created by moeda on 19/09/2017.
 */

public class Condutor
{
    @Exclude
    public String id;
    public String nome;
    public String sobrenome;
    public String cpf;
    public String telefone;
    public String rua;
    public String bairro;
    public String numero;
    public String cep;
    public ArrayList<String> vansIDs;
    @Exclude
    public ArrayList<Van> vans;
    public ArrayList<String> escolasIDs;
    @Exclude
    public ArrayList<Escola> escolas;

    public String toString() {
        return nome;
    }

    public Condutor(String nome, String sobrenome, String cpf,
                    String telefone, String rua, String bairro, String numero, String cep)
    {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        escolas = new ArrayList<Escola>();
        vans = new ArrayList<Van>();
    }

    public Condutor()
    {
        // Default constructor required for calls to DataSnapshot.getValue(Responsavel.class)
    }

    public boolean containsBairro(String bairro) {
        if (vans == null) return false;
        for (Van p : vans) {
            if (p.nome.contains(bairro)) return true;
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

    public ArrayList<Escola> getEscolas() {
        return escolas;
    }

    public void setEscolas(ArrayList<Escola> escolas) {
        this.escolas = escolas;
    }

    public ArrayList<String> getEscolasIDs() {
        return escolasIDs;
    }

    public void setEscolasIDs(ArrayList<String> escolasIDs) {
        this.escolasIDs = escolasIDs;
    }

    public ArrayList<String> getVansIDs() {
        return vansIDs;
    }

    public void setVansIDs(ArrayList<String> vansIDs) {
        this.vansIDs = vansIDs;
    }

    public ArrayList<Van> getVans() {
        return vans;
    }

    public void setVans(ArrayList<Van> vans) {
        this.vans = vans;
    }
}
