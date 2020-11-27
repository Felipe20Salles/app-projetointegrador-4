package com.example.projetointegrador.classes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Lancamento implements Serializable {

    private long id;
    private String tipo;
    private String data;
    private float valor;
    private String descricao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private static final DateFormat FORMATO = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String toString(){
        return id + " - "+ tipo + ": "+ valor;
    }

}
