package com.tixs.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aline on 30/09/17.
 */

public class Van {

    @Exclude
    public String id = "";
    public String modelo = "";
    public String placa = "";
    public String nome = "";
    public String condutorID = "";
    //    @Exclude public Condutor condutor = new Condutor();
    public List<String> rotasIDs = new ArrayList<>();
    public List<Rota> rotas = new ArrayList<>();
    public ArrayList<String> escolasIDs = new ArrayList<>();
    public ArrayList<Escola> escolas = new ArrayList<>();

    public Van() {

    }

    public Van(String nome, List<Rota> rotas) {
//        this.condutor = condutor;
        this.nome = nome;
        this.rotas = rotas;
        for (Rota p : rotas) {
            rotasIDs.add(p.id);
        }
    }

    public boolean containsBairro(String bairro) {
        if (rotas == null || bairro == null) return false;
        for (Rota p : rotas) {
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

    public List<String> getRotasIDs() {
        return rotasIDs;
    }

    public void setRotasIDs(List<String> rotasIDs) {
        this.rotasIDs = rotasIDs;
    }

    public List<Rota> getRotas() {
        return rotas;
    }

    public void setRotas(List<Rota> rotas) {
        this.rotas = rotas;
    }

    public void addRota(Rota r) {
        rotas.add(r);
        rotasIDs.add(r.id);
    }

    public void carregarRotas() {
        List<ValueEventListener> valueEventListeners = new ArrayList<>(rotasIDs.size());
        for (String rid : rotasIDs) {
            valueEventListeners.add(FirebaseDatabase.getInstance().getReference("bairros").child(rid)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Rota r = dataSnapshot.getValue(Rota.class);
                            r.id = dataSnapshot.getKey();
                            addRota(r);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    }));
        }
        for (int i = 0; i < rotasIDs.size(); i++) {
            try {
                valueEventListeners.get(i).wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                FirebaseDatabase.getInstance().getReference("bairros").child(rotasIDs.get(i))
                        .removeEventListener(valueEventListeners.get(i));
            }
        }

    }
}
