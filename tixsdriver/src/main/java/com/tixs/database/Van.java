package com.tixs.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aline on 30/09/17.
 */

public class Van implements Serializable {

    @Exclude
    public String id = "";
    public String modelo = "";
    public String placa = "";
    public String nome = "";
    public String condutorID = "";
    //    public Condutor condutor = new Condutor();
    public List<String> bairrosIDs = new ArrayList<>();
    public List<Bairro> bairros = new ArrayList<>();
    public ArrayList<String> escolasIDs = new ArrayList<>();
    public ArrayList<Escola> escolas = new ArrayList<>();
    public List<String> criancasIDs = new ArrayList<>();
    public List<Crianca> criancas = new ArrayList<>();

    public Van() {

    }

    public Van(String nome, List<Bairro> rotas) {
//        this.condutor = condutor;
        this.nome = nome;
        this.bairros = rotas;
        for (Bairro p : rotas) {
            bairrosIDs.add(p.id);
        }
    }

    public boolean containsBairro(String bairro) {
        if (bairros == null || bairro == null) return false;
        for (Bairro p : bairros) {
            if (p.nome.contains(bairro)) return true;
        }
        return false;
    }

    public String toString() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public Condutor getCondutor() {
//        return condutor;
//    }

    public void setCondutor(Condutor condutor) {
        this.condutorID = condutor.id;
//        this.condutor = condutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getBairrosIDs() {
        return bairrosIDs;
    }

    public void setBairrosIDs(List<String> bairrosIDs) {
        this.bairrosIDs = bairrosIDs;
    }

    public List<Bairro> getBairros() {
        return bairros;
    }

    public void setBairros(List<Bairro> bairros) {
        this.bairros = bairros;
    }

    public void addRota(Bairro r) {
        bairros.add(r);
        bairrosIDs.add(r.id);
    }

    public void carregarRotas() {
        List<ValueEventListener> valueEventListeners = new ArrayList<>(bairrosIDs.size());
        for (String rid : bairrosIDs) {
            valueEventListeners.add(FirebaseDatabase.getInstance().getReference("bairros").child(rid)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Bairro r = dataSnapshot.getValue(Bairro.class);
                            r.id = dataSnapshot.getKey();
                            addRota(r);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    }));
        }
        for (int i = 0; i < bairrosIDs.size(); i++) {
            try {
                valueEventListeners.get(i).wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                FirebaseDatabase.getInstance().getReference("bairros").child(bairrosIDs.get(i))
                        .removeEventListener(valueEventListeners.get(i));
            }
        }

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void addEscola(Escola escola) {
        escolas.add(escola);
        escolasIDs.add(escola.id);
    }

    public void addBairro(Bairro bairro) {
        bairros.add(bairro);
        bairrosIDs.add(bairro.id);
    }
}
