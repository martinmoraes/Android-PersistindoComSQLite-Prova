package br.com.appviral.persistindocomsqlite.Entidade;

/**
 * Created by 978907 on 26/09/2016.
 */
public class Dependente {
    public static final String TABELA = "dependentes";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOME = "nome";
    public static final String CAMPO_DE_MAIOR = "maior";
    public static final String CAMPO_ID_PESSOA = "idpessoa";


    public long id;
    public String nome;
    public boolean deMaior;


    public String getDeMaior(){
        if(deMaior)
            return "true";
        else return "false";
    }

    public void setDeMaior(String valor){
        if(valor.equals("true"))
            deMaior = true;
        else deMaior = false;
    }
}
