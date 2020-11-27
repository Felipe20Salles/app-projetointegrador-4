package com.example.projetointegrador.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetointegrador.classes.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private final String TABELA = "usuario";
    private final String[] CAMPOS = {"_id, login, senha, email, telefone, renda"};
    private DBHelper conexao;
    private SQLiteDatabase banco;

    public UsuarioDao(Context context) {
        conexao = new DBHelper(context);
        banco = conexao.getWritableDatabase();
    }


    private ContentValues preencherValores(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("email", usuario.getEmail());
        values.put("telefone", usuario.getFone());
        values.put("renda", usuario.getRenda());
        return values;
    }

    public long inserir(Usuario usuario) {
        ContentValues values = preencherValores(usuario);
        return banco.insert(TABELA, null, values);
    }

    public long alterar(Usuario usuario) {
        ContentValues values = preencherValores(usuario);
        return banco.update(TABELA, values, "_id = ?", new String[]{String.valueOf(usuario.getId())});
    }

    public long excluir(Usuario usuario) {
        return banco.delete(TABELA, "_id = ?", new String[]{String.valueOf(usuario.getId())});
    }

    public List<Usuario> lista() {
        Cursor c = banco.query(TABELA, CAMPOS, null, null, null, null, null);
        List<Usuario> lista = new ArrayList<>();
        while (c.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setId(c.getLong(0));
            usuario.setLogin(c.getString(1));
            usuario.setSenha(c.getString(2));
            usuario.setEmail(c.getString(3));
            usuario.setFone(c.getString(4));
            usuario.setRenda(c.getFloat(5));
        }
        return lista;
    }

    public Usuario consultaLogin(String login, String senha) {
        String sql = "select * from usuario WHERE login = ? and senha = ?";

        String[] valores = new String[]{login, senha};

        Cursor c = banco.rawQuery(sql, valores);

        Usuario usuario;

        if (c.moveToNext()) {
            usuario = new Usuario();
            usuario.setId(c.getInt(0));
            usuario.setLogin(c.getString(1));
            return usuario;
        }
        return null;
    }
}
