package com.example.projetointegrador.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetointegrador.classes.Fornecedor;

import java.util.ArrayList;
import java.util.List;

public class FornecedorDao {

    private final String TABELA =  "fornecedor";
    private final String[] CAMPOS = {"_id, nome, telefone, email, uf"};
    private DBHelper conexao;
    private SQLiteDatabase banco;

    public FornecedorDao(Context context){
        conexao = new DBHelper(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencrerValores(Fornecedor fornecedor){
        ContentValues values = new ContentValues();
        values.put("nome", fornecedor.getNome());
        values.put("telefone", fornecedor.getTelefone());
        values.put("email", fornecedor.getEmail());
        values.put("uf", fornecedor.getUf());
        return values;
    }

    public long inserir(Fornecedor fornecedor){
        ContentValues values = preencrerValores(fornecedor);
        return banco.insert(TABELA,null,values);
    }

    public long alterar(Fornecedor fornecedor){
        ContentValues values = preencrerValores(fornecedor);
        return banco.update(TABELA,values,"_id = ?", new String[]{String.valueOf(fornecedor.getId())});
    }

    public long deletar(Fornecedor fornecedor){
        return banco.delete(TABELA,"_id = ?", new String[]{String.valueOf(fornecedor.getId())});
    }

    public List<Fornecedor> lista(){
        Cursor c = banco.query(TABELA,CAMPOS,null,null,null,null,null);
        List<Fornecedor> lista = new ArrayList<>();
        while (c.moveToNext()){
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(c.getLong(0));
            fornecedor.setNome(c.getString(1));
            fornecedor.setTelefone(c.getString(2));
            fornecedor.setEmail(c.getString(3));
            fornecedor.setUf(c.getString(4));
        }
        return lista;
    }
}