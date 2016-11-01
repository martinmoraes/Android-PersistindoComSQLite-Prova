package br.com.appviral.persistindocomsqlite.Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.appviral.persistindocomsqlite.Entidade.Dependente;
import br.com.appviral.persistindocomsqlite.Entidade.Pessoa;

/**
 * Created by Martin on 13/05/2016.
 */
public class DBSQLite extends SQLiteOpenHelper {
    private static final String NOME_BASE = "base.db";
    private static final int VERSAO = 4;

    public DBSQLite(Context context) {
        super(context, NOME_BASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CRIA_TABELA_PESSOA = "CREATE TABLE " + Pessoa.TABELA  + "("
                + Pessoa.CAMPO_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Pessoa.CAMPO_NOME + " TEXT, "
                + Pessoa.CAMPO_FONE + " TEXT, "
                + Pessoa.CAMPO_EMAIL + " TEXT )";
        db.execSQL(CRIA_TABELA_PESSOA);

        String CRIA_TABELA_DEPENDENTE = "CREATE TABLE " + Dependente.TABELA  + "("
                + Dependente.CAMPO_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Dependente.CAMPO_NOME + " TEXT, "
                + Dependente.CAMPO_DE_MAIOR + " TEXT, "
                + Dependente.CAMPO_ID_PESSOA + " INTEGER )";
        db.execSQL(CRIA_TABELA_DEPENDENTE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String CRIA_TABELA_DEPENDENTE = "CREATE TABLE " + Dependente.TABELA  + "("
                + Dependente.CAMPO_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Dependente.CAMPO_NOME + " TEXT, "
                + Dependente.CAMPO_DE_MAIOR + " TEXT, "
                + Dependente.CAMPO_ID_PESSOA + " INTEGER )";
        db.execSQL(CRIA_TABELA_DEPENDENTE);
    }
}
