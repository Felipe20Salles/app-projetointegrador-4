package com.example.projetointegrador.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetointegrador.R;

public class TelaPrincipal extends AppCompatActivity implements View.OnClickListener {

    Button btnLancamento;
    Button btnFornecedor;
    Button btnCategoria;
    Button btnRelatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLancamento = findViewById(R.id.btnLancamento);
        btnLancamento.setOnClickListener(this);

        btnFornecedor = findViewById(R.id.btnFornecedor);
        btnFornecedor.setOnClickListener(this);

        btnCategoria = findViewById(R.id.btnCategoria);
        btnCategoria.setOnClickListener(this);

        btnRelatorio = findViewById(R.id.btnRelatorios);
        btnRelatorio.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnLancamento) {
            Intent telaLancamento = new Intent(this, TelaLancamento.class);
            startActivity(telaLancamento);
        } else if (v == btnFornecedor) {
            Intent telaFornecedor = new Intent(this, TelaFornecedor.class);
            startActivity(telaFornecedor);
        } else if (v == btnCategoria) {
            Intent telaCategoria = new Intent(this, TelaCategoria.class);
            startActivity(telaCategoria);
        } else if (v == btnRelatorio){
            Intent telaRelatorio = new Intent(this,TelaRelatorio.class);
            startActivity(telaRelatorio);
        }
    }
}