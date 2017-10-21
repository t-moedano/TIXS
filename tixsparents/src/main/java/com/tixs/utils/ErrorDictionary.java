package com.tixs.utils;

/**
 * @author Thauany Moedano
 * Classe que contém constantes de mensagem de erro
 */

public class ErrorDictionary
{

    /**
     * Mensagem de erro quando o método de clone falha.
     */
    public static String CLONE_NOT_SUPPORTED = "Falha ao clonar o objeto.";

    /**
     * Mensagem de erro quando a conexão com o banco é interrompida.
     */
    public static String INTERRUPTED_EXCEPTION_ON_DATABASE = "Falha. A conexão foi interrompida";

    /**
     * Mensagem de erro quando nenhum par de coordenadas é encontrado para o endereço em questão.
     */
    public static String ADDRESS_NOT_FOUND = "Falha. Endereço não encontrado";
}
