package com.example.projetointegrador.classes;

import java.io.Serializable;

public class Categoria implements Serializable {

    private long id;
    private String descricao;

    public Categoria(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Categoria() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return id + " - "  + descricao;
    }
}
