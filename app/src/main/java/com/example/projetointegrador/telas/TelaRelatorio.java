package com.example.projetointegrador.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projetointegrador.DAO.LancamentoDao;
import com.example.projetointegrador.R;
import com.example.projetointegrador.classes.Relatorio;
import com.example.projetointegrador.classes.Lancamento;

import java.util.ArrayList;
import java.util.List;

public class TelaRelatorio extends AppCompatActivity {
    ListView lvRelatorio;
    TextView tvRelatorio;
    List<Lancamento> listaLanc = new ArrayList<>();
    LancamentoDao lancamentoDao;
    ListAdapter listAdapter;
    Relatorio r;
    String gasto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_relatorio);
        gasto = String.valueOf(r.totalGasto());
        tvRelatorio.setText(String.valueOf(r));
        lancamentoDao = new LancamentoDao(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista() {
        listaLanc = lancamentoDao.lista();
        listAdapter = new ArrayAdapter<Lancamento>(this, android.R.layout.simple_list_item_1, listaLanc);
        lvRelatorio.setAdapter(listAdapter);
    }
}