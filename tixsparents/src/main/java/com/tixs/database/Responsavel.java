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
    public String rua = "";
    public String bairro = "";
    public String numero = "";
    public String cep = "";
    public List<String> criancasIDs = new ArrayList<>();
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
     * Carrega os IDs do vetor de IDS nas criancas.
     */
    public void carregarIDs() {
        for (int i = 0; i < criancasIDs.size(); i++) {
            criancas.get(i).id = criancasIDs.get(i);
        }
    }
}
