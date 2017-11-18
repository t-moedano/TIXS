package com.tixs.database;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tixs.utils.ErrorDictionary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aline.
 */

public class Van implements Serializable {

    @Exclude
    public String id = "";
    public String modelo = "";
    public String placa = "";
    public String nome = "";
    public String condutorID = "";
    public List<String> enderecosIDs = new ArrayList<>();
    public List<Endereco> enderecos = new ArrayList<>();
    public ArrayList<String> escolasIDs = new ArrayList<>();
    public ArrayList<Escola> escolas = new ArrayList<>();
    public List<String> criancasIDs = new ArrayList<>();
    public List<Crianca> criancas = new ArrayList<>();

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double latitude;
    public double longitude;


    public Van() {

    }

    public Van(String nome, List<Endereco> rotas) {
        this.nome = nome;
        this.enderecos = rotas;
        for (Endereco p : rotas) {
            enderecosIDs.add(p.id);
        }
    }

    /**
     * Verifica se uma van atende determinado bairro
     *
     * @param bairro
     * @return true se a van atende o bairro. false caso contrário.
     */
    public boolean containsBairro(String bairro) {
        if (enderecos == null || bairro == null) return false;
        for (Endereco p : enderecos) {
            if (p.rua.contains(bairro)) return true;
        }
        return false;
    }

    /**
     * @return
     */
    public String toString() {
        return nome;
    }

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @param condutor
     */
    public void setCondutor(Condutor condutor) {
        this.condutorID = condutor.id;
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
    public List<String> getEnderecosIDs() {
        return enderecosIDs;
    }

    /**
     *
     * @param enderecosIDs
     */
    public void setEnderecosIDs(List<String> enderecosIDs) {
        this.enderecosIDs = enderecosIDs;
    }

    /**
     *
     * @return lista de enderecos
     */
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    /**
     *
     * @param enderecos
     */
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    /**
     * Adiciona um bairro a lista de enderecos que uma van atende
     * @param r
     */
    public void addEndereco(Endereco r) {
        Integer ind = enderecosIDs.indexOf(r.id);
        if(ind > -1) {
            enderecos.set(ind, r);
        } else {
            enderecos.add(r);
            enderecosIDs.add(r.id);
        }
    }

    /**
     * Lê as rotas do banco de dados
     */
    public void carregarRotas() {
        List<ValueEventListener> valueEventListeners = new ArrayList<>(enderecosIDs.size());
        for (String rid : enderecosIDs) {
            valueEventListeners.add(FirebaseDatabase.getInstance().getReference("enderecos").child(rid)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Endereco r = dataSnapshot.getValue(Endereco.class);
                            r.id = dataSnapshot.getKey();
                            addEndereco(r);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    }));
        }
        for (int i = 0; i < enderecosIDs.size(); i++) {
            try {
                valueEventListeners.get(i).wait();
            } catch (InterruptedException e) {
                Log.d(Van.class.getSimpleName(), ErrorDictionary.INTERRUPTED_EXCEPTION_ON_DATABASE);
            } finally {
                FirebaseDatabase.getInstance().getReference("enderecos").child(enderecosIDs.get(i))
                        .removeEventListener(valueEventListeners.get(i));
            }
        }

    }

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

    /**
     * Adiciona uma escola a uma determinada van
     * @param escola
     */
    public void addEscola(Escola escola) {
        escolas.add(escola);
        escolasIDs.add(escola.id);
    }

    /**
     * Adiciona um endereco a uma determinada van
     * @param endereco
     */
    public void addBairro(Endereco endereco) {
        enderecos.add(endereco);
        enderecosIDs.add(endereco.id);
    }

    public void addCrianca(Crianca crianca) {
        Integer ind = criancasIDs.indexOf(crianca.id);
        if(ind > -1) {
            criancas.set(ind, crianca);
        } else {
            criancas.add(crianca);
            criancasIDs.add(crianca.id);
        }
    }
}
