package br.com.appviral.persistindocomsqlite.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.appviral.persistindocomsqlite.Entidade.Dependente;
import br.com.appviral.persistindocomsqlite.Entidade.Pessoa;

/**
 * Created by 978907 on 26/09/2016.
 */
public class DependentesDAO {

    Context context;
    static DBSQLite dbsqLite;
    public static ArrayList<Dependente> listaDependentes = new ArrayList<>();

    public DependentesDAO(Context context, long id) {
        this.context = context;
        dbsqLite = new DBSQLite(context);

        this.listar(id);
    }

    public boolean inserir(Dependente dependente, long id_pessoa) {
        SQLiteDatabase db = dbsqLite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dependente.CAMPO_NOME, dependente.nome);
        values.put(dependente.CAMPO_DE_MAIOR, dependente.getDeMaior());
        values.put(dependente.CAMPO_ID_PESSOA, id_pessoa);
        Long id = db.insert(dependente.TABELA, null, values);
        db.close();
        if (id > 0) {
            dependente.id = id;
        }
        return id > 0;
    }

    public void listar(long id_pessoa) {
        SQLiteDatabase db = dbsqLite.getReadableDatabase();
        listaDependentes.clear();

        String selectQuery = "SELECT  " +
                Dependente.CAMPO_ID + "," +
                Dependente.CAMPO_NOME + "," +
                Dependente.CAMPO_DE_MAIOR +
                " FROM " + Dependente.TABELA +
                " WHERE " + Dependente.CAMPO_ID_PESSOA +"="+ id_pessoa;

        Cursor cursor = db.rawQuery(selectQuery, null);
        Dependente dependente;

        if (cursor.moveToFirst()) {
            do {
                dependente = new Dependente();
                dependente.id = cursor.getLong(0);
                dependente.nome = cursor.getString(1);
                String deMaior = cursor.getString(2);
                if(deMaior.equals("true"))
                    dependente.deMaior = true;
                else dependente.deMaior = false;

                listaDependentes.add(dependente);
            } while (cursor.moveToNext());
            db.close();
        }
    }

    public boolean alterar(Dependente dependente) {
        SQLiteDatabase db = dbsqLite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dependente.CAMPO_NOME, dependente.nome);
        values.put(dependente.CAMPO_DE_MAIOR, dependente.getDeMaior());
        String whare = dependente.CAMPO_ID + " = ?";

        int id = db.update(Dependente.TABELA, values, whare, new String[]{String.valueOf(dependente.id)});
        db.close();
        return id > 0;
    }

    public boolean excluir(Long id) {
        SQLiteDatabase db = dbsqLite.getWritableDatabase();
        String whare = Dependente.CAMPO_ID + " = ?";
        int ret = db.delete(Dependente.TABELA, whare, new String[]{String.valueOf(id)});
        db.close();

        return ret > 0;
    }
}
