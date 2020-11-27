package com.example.projetointegrador.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetointegrador.DAO.UsuarioDao;
import com.example.projetointegrador.R;
import com.example.projetointegrador.classes.Post;
import com.example.projetointegrador.classes.Usuario;
import com.example.projetointegrador.retrofit.PostService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TelaLogin extends AppCompatActivity implements View.OnClickListener {

    private PostService postsService;
    TextView lblresponse;
    Button btnLogin, btnCadastrar;
    EditText edtUsuario, edtSenha;
    Usuario user;
    UsuarioDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(this);
        btnLogin = findViewById(R.id.btnEntrar);
        btnLogin.setOnClickListener(this);
        edtUsuario = findViewById(R.id.edtUsusario);
        edtSenha = findViewById(R.id.edtSenha);

        dao = new UsuarioDao(this);

        try {
            lblresponse = findViewById(R.id.lblResponse);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://104849d65717.ngrok.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postsService = retrofit.create(PostService.class);
            sendPost();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            admin();
            if (edtUsuario.equals("") || edtSenha.equals("")) {
                Toast.makeText(getApplicationContext(), "Campos vazios", Toast.LENGTH_SHORT).show();
            } else {
                user = new Usuario();
                user.setLogin(edtUsuario.getText().toString());
                user.setSenha(edtSenha.getText().toString());
                user = dao.consultaLogin(user.getLogin(), user.getSenha());
                if (user != null) {
                    Intent telaPrincipal = new Intent(this, TelaPrincipal.class);
                    startActivity(telaPrincipal);
                }
            }
        } else if (v == btnCadastrar) {
            abrirCadastro();
        }
    }

    private void abrirCadastro() {
        Intent telaCadUser = new Intent(this, TelaCadLogin.class);
        startActivity(telaCadUser);
    }

    private void admin() {
        Usuario adm = new Usuario();
        adm.setLogin("admin");
        adm.setSenha("admin");
        adm.setEmail("admin@gmail.com");
        adm.setFone("62912345678");
        adm.setRenda(1000);
        dao.inserir(adm);
    }

    private void sendPost() {
        Post post = new Post();
        post.setEmail("admin@gmail.com");
        post.setLogin("admin");
        post.setRenda(9999);
        post.setSenha("admin");
        post.setTelefone("6299999999");
        Call<Post> call = postsService.sendPosts(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                    lblresponse.setText(response.body().toString());

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }

        });
    }
}