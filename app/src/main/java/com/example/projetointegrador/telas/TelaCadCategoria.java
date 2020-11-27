package com.example.projetointegrador.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetointegrador.DAO.CategoriaDao;
import com.example.projetointegrador.R;
import com.example.projetointegrador.classes.Categoria;

public class TelaCadCategoria extends AppCompatActivity implements View.OnClickListener {


    EditText edtDescricaoCat;
    Button btnSalvar;
    Button btnExcluir;

    String acao;
    Categoria c;
    CategoriaDao dao;

    private void criarComponentes() {
        edtDescricaoCat = findViewById(R.id.edtDescricaoCat);
        edtDescricaoCat.setOnClickListener(this);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(this);
        btnExcluir.setVisibility(acao.equals("Cadastrar") ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cad_categoria);

        acao = getIntent().getExtras().getString("acao");
        dao = new CategoriaDao(this);
        criarComponentes();

        if (getIntent().getExtras().getSerializable("Categoria") != null) {
            c = (Categoria) getIntent().getExtras().getSerializable("Categoria");
            edtDescricaoCat.setText(c.getDescricao());
        }


    }

    @Override
    public void onClick(View v) {
        if (v == btnSalvar) {
            c.setDescricao(edtDescricaoCat.getText().toString());
            if (acao.equals("Cadastrar")) {
                Long id = dao.Inserir(c);
                Intent voltar = new Intent(this, TelaCategoria.class);
                startActivity(voltar);
            } else {
                Long id = dao.alterar(c);
                Intent voltar = new Intent(this, TelaCategoria.class);
                startActivity(voltar);
            }
            finish();
        } else if (v == btnExcluir) {
            long id = dao.excluir(c);
            Intent voltar = new Intent(this, TelaCategoria.class);
            startActivity(voltar);
        }
        finish();
    }
}