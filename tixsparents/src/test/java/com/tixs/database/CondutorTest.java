package com.tixs.database;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class CondutorTest {
    private String id = "123";
    private String nome = "Nome";
    private String sobrenome = "Sobrenome";
    private String cpf = "11111111111";
    private String telefone = "12999999999";
    private String rua = "Rua";
    private String bairro = "Bairro";
    private String numero = "123";
    private String cep = "12000000";
    private Condutor teste_condutor;

    @Before
    public void before(){
        teste_condutor = new Condutor (nome, sobrenome, cpf, telefone, rua, bairro, numero, cep);
    }

    @Test
    public void getNomeTeste() throws Exception {
        assertEquals(teste_condutor.getNome(), nome);
    }

    @Test
    public void setNomeTeste() throws Exception {
        String new_name = nome;
        teste_condutor.setNome(nome);
        assertEquals(teste_condutor.nome, nome);
    }
    @Test
    public void getCpfTeste() throws Exception {
        assertEquals(teste_condutor.getCpf(), cpf);
    }

    @Test
    public void setCpfTeste() throws Exception {
        String new_cpf = cpf;
        teste_condutor.setNome(cpf);
        assertEquals(teste_condutor.cpf, new_cpf);
    }
}
