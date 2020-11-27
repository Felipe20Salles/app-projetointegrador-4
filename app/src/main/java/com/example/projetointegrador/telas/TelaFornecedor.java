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

import com.example.projetointegrador.DAO.FornecedorDao;
import com.example.projetointegrador.R;
import com.example.projetointegrador.classes.Categoria;
import com.example.projetointegrador.classes.Fornecedor;
import com.example.projetointegrador.classes.Lancamento;

import java.util.ArrayList;
import java.util.List;

public class TelaFornecedor extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button btnCadFornecedor;
    ListView lvFornecedor;
    List<Fornecedor> listaForc = new ArrayList<>();
    ListAdapter listAdapter;
    int indice;
    FornecedorDao fornecedorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_fornecedor);

        btnCadFornecedor = findViewById(R.id.btnCadFornecedor);
        btnCadFornecedor.setOnClickListener(this);

        lvFornecedor = findViewById(R.id.lvFornec);
        lvFornecedor.setOnItemClickListener(this);

        fornecedorDao = new FornecedorDao(this);
    }

    private void atualizarLista() {
        listaForc = fornecedorDao.lista();
        listAdapter = new ArrayAdapter<Fornecedor>(this, android.R.layout.simple_list_item_1, listaForc);
        lvFornecedor.setAdapter(listAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    @Override
    public void onClick(View v) {
        Fornecedor f = new Fornecedor();
        f.setId(0L);
        abrirRegistro("Cadastrar", f);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        indice = position;
        Fornecedor f = (Fornecedor) lvFornecedor.getAdapter().getItem(indice);
        abrirRegistro("Alterar", f);
    }

    private void abrirRegistro(String acao, Fornecedor f) {
        Intent telaRegFornec = new Intent(this, TelaCadFornecedor.class);
        Bundle extras = new Bundle();
        extras.putString("acao", acao);
        extras.putSerializable("For", f);
        telaRegFornec.putExtras(extras);
        startActivity(telaRegFornec);
    }

}