package com.example.projetointegrador.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetointegrador.DAO.LancamentoDao;
import com.example.projetointegrador.R;
import com.example.projetointegrador.classes.Lancamento;

public class TelaCadLancamento extends AppCompatActivity implements View.OnClickListener {
    EditText edtTipo, edtData, edtValor, edtCategoria;
    Button btnSalvar, btnExcluir;

    String acao;
    Lancamento l;
    LancamentoDao dao;

    private void criarComponentes() {
        edtTipo = findViewById(R.id.edtTipo);
        edtTipo.setOnClickListener(this);

        edtData = findViewById(R.id.edtData);
        edtData.setOnClickListener(this);

        edtValor = findViewById(R.id.edtValor);
        edtValor.setOnClickListener(this);

        edtCategoria = findViewById(R.id.edtCategoria);
        edtCategoria.setOnClickListener(this);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);

        btnExcluir = findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(this);
        btnExcluir.setVisibility(acao.equals("Cadastrar") ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cad_lancamento);

        acao = getIntent().getExtras().getString("acao");
        dao = new LancamentoDao(this);
        criarComponentes();

        if (getIntent().getExtras().getSerializable("Lan") != null) {
            l = (Lancamento) getIntent().getExtras().getSerializable("Lan");
            edtTipo.setText(l.getTipo());
            edtData.setText(l.getData());
            edtValor.setText(String.valueOf(l.getValor()));
            edtCategoria.setText(l.getDescricao());
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnSalvar) {
            l.setTipo(edtTipo.getText().toString());
            l.setData(edtData.getText().toString());
            l.setValor(Float.valueOf(edtValor.getText().toString()));
            l.setDescricao(edtCategoria.getText().toString());

            if (acao.equals("Cadastrar")) {
                Long id = dao.inserir(l);
                Intent voltar = new Intent(this, TelaLancamento.class);
                startActivity(voltar);
            } else {
                Long id = dao.alterar(l);
                Intent voltar = new Intent(this, TelaLancamento.class);
                startActivity(voltar);
            }
            finish();
        } else if (v == btnExcluir) {
            Long id = dao.deletar(l);
            Intent voltar = new Intent(this, TelaLancamento.class);
            startActivity(voltar);
        }
        finish();
    }
}