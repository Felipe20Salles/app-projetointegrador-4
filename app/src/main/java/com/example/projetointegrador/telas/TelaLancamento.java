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

import com.example.projetointegrador.DAO.LancamentoDao;
import com.example.projetointegrador.R;
import com.example.projetointegrador.classes.Categoria;
import com.example.projetointegrador.classes.Lancamento;

import java.util.ArrayList;
import java.util.List;

public class TelaLancamento extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button btnCadLancamento;
    ListView lstLancamentos;
    List<Lancamento> listaLac = new ArrayList<>();
    ListAdapter listAdapter;
    int indice;
    LancamentoDao lancamentoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lancamento);

        btnCadLancamento = findViewById(R.id.btnCadLancamento);
        btnCadLancamento.setOnClickListener(this);

        lstLancamentos = findViewById(R.id.lstLancamentos);
        lstLancamentos.setOnItemClickListener(this);

        lancamentoDao = new LancamentoDao(this);
    }

    private void atualizarLista() {
        listaLac = lancamentoDao.lista();
        listAdapter = new ArrayAdapter<Lancamento>(this, android.R.layout.simple_list_item_1, listaLac);
        lstLancamentos.setAdapter(listAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    @Override
    public void onClick(View v) {
        Lancamento l = new Lancamento();
        l.setId(0L);
        abrirRegistro("Cadastrar",l);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        indice = position;
        Lancamento l = (Lancamento) lstLancamentos.getAdapter().getItem(indice);
        abrirRegistro("Alterar", l);
    }

    private void abrirRegistro(String acao, Lancamento l){
        Intent telaRegLanc = new Intent(this,TelaCadLancamento.class);
        Bundle extras = new Bundle();
        extras.putString("acao",acao);
        extras.putSerializable("Lan",l);
        telaRegLanc.putExtras(extras);
        startActivity(telaRegLanc);
    }
}