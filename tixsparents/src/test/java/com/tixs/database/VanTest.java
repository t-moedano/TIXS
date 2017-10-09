package com.tixs.database;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class VanTest {
    private List<Bairro> rotas = new ArrayList<>();
    private Van teste_van;
    private Escola teste_escola;
    private Responsavel teste_responsavel;
    private String nome_da_van = "minha van";

    @Before
    public void before(){
        rotas.add(new Bairro("Bairro 1"));
        rotas.add(new Bairro("Bairro 2"));
        rotas.add(new Bairro("Bairro 3"));
        teste_van = new Van (nome_da_van, rotas);
        teste_escola = new Escola("Moppe", "Urbanova");
    }

    @Test
    public void getNomeTeste() throws Exception {
        assertEquals(teste_van.getNome(), nome_da_van);
    }

    @Test
    public void setNomeTeste() throws Exception {
        String new_name = "outra van";
        teste_van.setNome(new_name);
        assertEquals(teste_van.nome, new_name);
    }

    @Test
    public void containsBairroTeste() throws Exception {
        assertTrue(teste_van.containsBairro("Bairro 2"));
    }

    @Test
    public void addEscolaTeste() throws Exception {
        teste_van.addEscola(teste_escola);
        assertTrue(teste_van.escolas.contains(teste_escola));
    }
}