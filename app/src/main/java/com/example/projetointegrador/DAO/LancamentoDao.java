package com.example.projetointegrador.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetointegrador.classes.Lancamento;

import java.util.ArrayList;
import java.util.List;

public class LancamentoDao {

    private final String TABELA = "lancamento";
    private final String[] CAMPOS = {"_id, tipo, data, valor, descricao"};
    private DBHelper conexao;
    private SQLiteDatabase banco;

    public LancamentoDao(Context context) {
        conexao = new DBHelper(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencherValores(Lancamento lancamento) {
        ContentValues values = new ContentValues();
        values.put("tipo", lancamento.getTipo());
        values.put("data", lancamento.getData().toString());
        values.put("valor", lancamento.getValor());
        values.put("descricao", lancamento.getDescricao());
        return values;
    }

    public long inserir(Lancamento lancamento) {
        ContentValues values = preencherValores(lancamento);
        return banco.insert(TABELA, null, values);
    }

    public long alterar(Lancamento lancamento) {
        ContentValues values = preencherValores(lancamento);
        return banco.update(TABELA, values, "_id = ?", new String[]{String.valueOf(lancamento.getId())});
    }

    public long deletar(Lancamento lancamento) {
        return banco.delete(TABELA, "_id = ?", new String[]{String.valueOf(lancamento.getId())});
    }

    public List<Lancamento> lista() {
        Cursor c = banco.query(TABELA, CAMPOS, null, null, null, null, null);
        List<Lancamento> lista = new ArrayList<>();
        while (c.moveToNext()) {
            Lancamento lancamento = new Lancamento();
            lancamento.setId(c.getLong(0));
            lancamento.setTipo(c.getString(1));
            lancamento.setData(c.getString(2));
            lancamento.setValor(c.getFloat(3));
            lancamento.setDescricao(c.getString(4));
        }
        return lista;
    }
}
