package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        AlunoDAO alunoDAO = new AlunoDAO();

        setTitle("Lista de Alunos");


        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_list_view);
        listaDeAlunos.setAdapter(new ArrayAdapter<Aluno>(
                this,
                android.R.layout.simple_list_item_1,
                alunoDAO.todos()) {
        });
        FloatingActionButton botaoAdd = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);

        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
            }
        });
    }
}
