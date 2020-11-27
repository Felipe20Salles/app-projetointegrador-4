package com.example.projetointegrador.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetointegrador.DAO.UsuarioDao;
import com.example.projetointegrador.R;
import com.example.projetointegrador.classes.Usuario;

public class TelaCadLogin extends AppCompatActivity implements View.OnClickListener{
    EditText edtLogin, edtSenha, edtEmail, edtTelefone, edtRenda;
    Button btnCadastrar;

    Usuario u;
    UsuarioDao dao;

    private void criarComponentes(){
        btnCadastrar = findViewById(R.id.btnCadastro);
        btnCadastrar.setOnClickListener(this);

        edtLogin = findViewById(R.id.edtLogin);
        edtLogin.setOnClickListener(this);

        edtSenha = findViewById(R.id.edtSenha);
        edtSenha.setOnClickListener(this);

        edtEmail = findViewById(R.id.edtEmail);
        edtEmail.setOnClickListener(this);

        edtTelefone = findViewById(R.id.edtTelefone);
        edtTelefone.setOnClickListener(this);

        edtRenda = findViewById(R.id.edtRenda);
        edtRenda.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cad_login);

        dao = new UsuarioDao(this);
        criarComponentes();
        u.setLogin(edtLogin.getText().toString());
        u.setSenha(edtSenha.getText().toString());
        u.setEmail(edtEmail.getText().toString());
        u.setFone(edtTelefone.getText().toString());
        u.setRenda(Float.valueOf(edtRenda.getText().toString()));
    }

    @Override
    public void onClick(View v) {

        if(v== btnCadastrar){
            Long id = dao.inserir(u);
        }
    }
}