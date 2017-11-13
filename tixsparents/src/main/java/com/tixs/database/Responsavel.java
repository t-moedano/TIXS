package com.tixs.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@IgnoreExtraProperties
public class Responsavel {

    @Exclude
    public String id = "";
    public String nome = "";
    public String sobrenome = "";
    public String cpf = "";
    public String telefone = "";
    public Endereco endereco = new Endereco();
    public List<String> criancasIDs = new ArrayList<>();
    public List<Crianca> criancas = new ArrayList<>();


    public Responsavel(String id, String nome, String sobrenome, String cpf,
                       String telefone, Endereco endereco, List<Crianca> criancas) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.criancas = criancas;
        criancasIDs = Collections.emptyList();
        for (Crianca c : criancas) {
            criancasIDs.add(c.id);
        }
    }

    /**
     * Default constructor required for calls to DataSnapshot.getValue(Responsavel.class)
     */
    public Responsavel() {

    }

    /**
     * Associa uma criança a um responsavel
     *
     * @param c
     */
    public void addCrianca(Crianca c) {
        criancas.add(c);
        criancasIDs.add(c.id);
    }

    /**
     * Lê uma lista de crianças do banco
     */
    public void carregarCrianca() {
        for (String cid : criancasIDs) {
            FirebaseDatabase.getInstance().getReference("criancas").child(cid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Crianca c = dataSnapshot.getValue(Crianca.class);
                    c.id = dataSnapshot.getKey();
                    addCrianca(c);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
