package com.example.projetointegrador.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.projetointegrador.DAO.CategoriaDao;
import com.example.projetointegrador.R;
import com.example.projetointegrador.classes.Categoria;
import com.example.projetointegrador.classes.Lancamento;

import java.util.ArrayList;
import java.util.List;

public class TelaCategoria extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button btnCadastrar;
    ListView lvCategoria;
    List<Categoria> listaCategoria = new ArrayList<>();
    ListAdapter listAdapter;
    int indice;
    CategoriaDao categoriaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_categoria);

        btnCadastrar = findViewById(R.id.btnCadCategoria);
        btnCadastrar.setOnClickListener(this);

        lvCategoria = findViewById(R.id.lvCategoria);
        lvCategoria.setOnItemClickListener(this);

        categoriaDao = new CategoriaDao(this);
    }

    private void atualizarLista() {
        listaCategoria = categoriaDao.lista();
        listAdapter = new ArrayAdapter<Categoria>(this, android.R.layout.simple_list_item_1, listaCategoria);
        lvCategoria.setAdapter(listAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    @Override
    public void onClick(View v) {
        Categoria c = new Categoria();
        c.setId(0L);
        abrirRegistro("Cadastrar", c);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        indice = position;
        Categoria c = (Categoria) lvCategoria.getAdapter().getItem(indice);
        abrirRegistro("Alterar", c);
    }

    private void abrirRegistro(String acao, Categoria c) {
        Intent telaRegCat = new Intent(this, TelaCadCategoria.class);
        Bundle extras = new Bundle();
        extras.putString("acao", acao);
        extras.putSerializable("Categoria", c);
        telaRegCat.putExtras(extras);
        startActivity(telaRegCat);
    }
}