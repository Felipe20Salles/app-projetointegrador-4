package com.example.projetointegrador.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "PI.DB";
    private static final int VERSAO_BANCO = 1;

    private String SQL_CREATE_LANCAMENTO = "CREATE TABLE if not exists lancamento (" +
            "_id INTEGER primary key autoincrement," +
            "tipo VARCHAR(11)," +
            "data DATE," +
            "valor FLOAT," +
            "descricao VARCHAR(255) );";

    private String SQL_CREATE_CATEGORIA = "CREATE TABLE if not exists categoria (" +
            "_id INTEGER primary key autoincrement," +
            "descricao VARCHAR(255) );";

    private String SQL_CREATE_FORNECEDOR = "CREATE TABLE if not exists fornecedor (" +
            "_id INTEGER primary key autoincrement," +
            "nome VARCHAR(255)," +
            "telefone VARCHAR(11)," +
            "email VARCHAR(255)," +
            "uf VARCHAR(2) );";

    private String SQL_CREATE_USUARIO = "CREATE TABLE if not exists usuario (" +
            "_id INTEGER primary key autoincrement," +
            "login VARCHAR(255)," +
            "senha VARCHAR(255)," +
            "email VARCHAR(255)," +
            "telefone VARCHAR(11)," +
            "renda FLOAT );";

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(SQL_CREATE_USUARIO);
        db.execSQL(SQL_CREATE_LANCAMENTO);
        db.execSQL(SQL_CREATE_CATEGORIA);
        db.execSQL(SQL_CREATE_FORNECEDOR);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
