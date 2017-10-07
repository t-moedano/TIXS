package com.tixs.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ThrowOnExtraProperties
public class Responsavel {

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
    public List<String> criancasIDs = new ArrayList<>();
    @Exclude
    public List<Crianca> criancas = new ArrayList<>();


    public Responsavel(String id, String nome, String sobrenome, String cpf,
                       String telefone, String rua, String bairro, String numero, String cep, List<Crianca> criancas) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cep = cep;
        this.criancas = criancas;
        criancasIDs = Collections.emptyList();
        for (Crianca c : criancas) {
            criancasIDs.add(c.id);
        }
    }

    public Responsavel() {
        // Default constructor required for calls to DataSnapshot.getValue(Responsavel.class)
    }

    public void addCrianca(Crianca c) {
        criancas.add(c);
        criancasIDs.add(c.id);
    }

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
