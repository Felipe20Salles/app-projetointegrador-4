package com.example.projetointegrador.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetointegrador.classes.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    private final String TABELA =  "categoria";
    private final String[] CAMPOS = {"_id, descricao"};
    private DBHelper conexao;
    private SQLiteDatabase banco;

    public CategoriaDao(Context context){
        conexao = new DBHelper(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencherValores(Categoria categoria){
        ContentValues values = new ContentValues();
        values.put("descricao", categoria.getDescricao());
        return values;
    }

    public long Inserir(Categoria categoria){
        ContentValues values = preencherValores(categoria);
        return banco.insert(TABELA,null,values);
    }

    public long alterar(Categoria categoria){
        ContentValues values = preencherValores(categoria);
        return banco.update(TABELA,values,"_id = ?", new String[]{String.valueOf(categoria.getId())});
    }

    public long excluir(Categoria categoria){
        return banco.delete(TABELA,"_id = ?", new String[]{String.valueOf(categoria.getId())});
    }

    public List<Categoria> lista(){
        Cursor c = banco.query(TABELA,CAMPOS,null,null,null,null,null);
        List<Categoria> lista = new ArrayList<>();
        while (c.moveToNext()){
            Categoria categoria = new Categoria();
            categoria.setId(c.getLong(0));
            categoria.setDescricao(c.getString(1));
            lista.add(categoria);
        }
        return lista;
    }

}
