package com.tixs.database;

/**
 * Classe ADO que representa um bairro.
 * author: Aline
 */

public class Endereco {

    public String id;
    public String rua;
    public String bairro;
    public Integer numero;
    public Integer cep;

    public Endereco() {
        rua = "";
    }


    /**
     * Construtor do bairro com um rua
     *
     * @param rua
     */
    public Endereco(String rua) {
        this.rua = rua;
    }


    /**
     * @return rua do bairro
     */
    public String getRua() {
        return rua;
    }


    /**
     * @param rua do bairro
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * Sobrescreve o método equals para comparar se um bairro é igual a outro
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;


        Endereco rota = (Endereco) o;

        return rua.equals(rota.rua);
    }

    /**
     * Define que o hash code do bairro será o rua
     * @return
     */
    @Override
    public int hashCode() {
        return rua.hashCode();
    }

    @Override
    public String toString() {
        return rua + "," + bairro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }
}
