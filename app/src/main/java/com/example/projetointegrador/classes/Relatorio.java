package com.example.projetointegrador.classes;


import java.util.List;

public class Relatorio {
    private List<Lancamento> lista;
    private float renda;

    public float totalGasto(){
        float soma = 0;
        for (Lancamento lancamento : lista){
            soma = soma + lancamento.getValor();
        }
        return soma;
    }
}
