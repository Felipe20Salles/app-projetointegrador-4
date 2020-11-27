package com.example.projetointegrador.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetointegrador.DAO.FornecedorDao;
import com.example.projetointegrador.R;
import com.example.projetointegrador.classes.Fornecedor;

public class TelaCadFornecedor extends AppCompatActivity implements View.OnClickListener {
    EditText edtNome, edtTelefone, edtEmail, edtUf;
    Button btnSalvar, btnExcluir;

    String acao;
    Fornecedor f;
    FornecedorDao dao;

    private void criarComponentes() {
        edtNome = findViewById(R.id.edtNome);
        edtNome.setOnClickListener(this);

        edtTelefone = findViewById(R.id.edtTelefone);
        edtTelefone.setOnClickListener(this);

        edtEmail = findViewById(R.id.edtEmail);
        edtEmail.setOnClickListener(this);

        edtUf = findViewById(R.id.edtUf);
        edtUf.setOnClickListener(this);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);

        btnExcluir = findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(this);
        btnExcluir.setVisibility(acao.equals("Cadastrar") ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cad_fornecedor);

        acao = getIntent().getExtras().getString("acao");
        dao = new FornecedorDao(this);
        criarComponentes();

        if (getIntent().getExtras().getSerializable("For") != null) {
            f = (Fornecedor) getIntent().getExtras().getSerializable("For");
            edtNome.setText(f.getNome());
            edtTelefone.setText(f.getTelefone());
            edtEmail.setText(f.getEmail());
            edtUf.setText(f.getUf());
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnSalvar) {
            f.setNome(edtNome.getText().toString());
            f.setTelefone(edtTelefone.getText().toString());
            f.setEmail(edtEmail.getText().toString());
            f.setUf(edtUf.getText().toString());
            if (acao.equals("Cadastrar")) {
                long id = dao.inserir(f);
                Intent voltar = new Intent(this, TelaFornecedor.class);
                startActivity(voltar);
            } else {
                Long id = dao.alterar(f);
                Intent voltar = new Intent(this, TelaFornecedor.class);
                startActivity(voltar);
            }
            finish();
        } else if (v == btnExcluir) {
            long id = dao.deletar(f);
            Intent voltar = new Intent(this, TelaFornecedor.class);
            startActivity(voltar);
        }
        finish();
    }
}