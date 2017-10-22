package com.tixs.database;

import android.util.Log;

import com.google.firebase.database.Exclude;
import com.tixs.utils.ErrorDictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Thauany Moedano
 * Classe ADO para representar um condutor
 */

public class Condutor
{
    @Exclude
    public String id = "";
    public String nome = "";
    public String sobrenome = "";
    public String cpf = "";
    public String telefone = "";
    public String rua = "";
    public String bairro = "";
    public String numero = "";
    public String cep = "";
    public List<String> vansIDs = new ArrayList<>();
    public List<Van> vans = new ArrayList<>();


    /**
     * @return
     */
    public String toString() {
        return nome;
    }

    /**
     * @param nome
     * @param sobrenome
     * @param cpf
     * @param telefone
     * @param rua
     * @param bairro
     * @param numero
     * @param cep
     */
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
        vans = new ArrayList<Van>();
    }

    /**
     * Default constructor required for calls to DataSnapshot.getValue(Responsavel.class)
     */
    public Condutor()
    {

    }

    /**
     * Verifica se um condutor atende a determinado bairro
     * @param bairro
     * @return true se o condutor atende aquele bairro. false caso contrário.
     */
    public boolean containsBairro(String bairro) {
        if (vans == null) return false;
        for (Van p : vans) {
            if (p.nome.contains(bairro)) return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     *
     * @param sobrenome
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    /**
     *
     * @return
     */
    public String getCpf() {
        return cpf;
    }

    /**
     *
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     *
     * @return
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     *
     * @param telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     *
     * @return
     */
    public String getRua() {
        return rua;
    }

    /**
     *
     * @param rua
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     *
     * @return
     */
    public String getBairro() {
        return bairro;
    }

    /**
     *
     * @param bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     *
     * @return
     */
    public String getNumero() {
        return numero;
    }

    /**
     *
     * @param numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     *
     * @return
     */
    public String getCep() {
        return cep;
    }

    /**
     *
     * @param cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     *
     * @return
     */
    public List<String> getVansIDs() {
        return vansIDs;
    }

    /**
     *
     * @param vansIDs
     */
    public void setVansIDs(ArrayList<String> vansIDs) {
        this.vansIDs = vansIDs;
    }

    /**
     *
     * @return
     */
    public List<Van> getVans() {
        return vans;
    }

    /**
     *
     * @param vans
     */
    public void setVans(ArrayList<Van> vans) {
        this.vans = vans;
    }

    /**
     * Adiciona uma van na lista de vans de um condutor
     * @param van
     */
    public void addVan(Van van) {
        if (vansIDs.contains(van.id)) {
            Integer ind = vansIDs.indexOf(van.id);
            vans.set(ind, van);
        } else {
            vans.add(van);
            vansIDs.add(van.id);
        }
    }

    /**
     * Clona um objeto van
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException c) {
            Log.d(Condutor.class.getSimpleName(), ErrorDictionary.CLONE_NOT_SUPPORTED);
        }
        return o;
    }
}
