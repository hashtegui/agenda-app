package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    public static final String TITLE_APPBAR = "Lista de Alunos";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITLE_APPBAR);
        configuraFabNovoAluno();
        alunoDAO.salva(new Aluno("Alex","15489", "mail@mail.com"));
        alunoDAO.salva(new Aluno("Gui","15489", "mail@mail.com"));
        alunoDAO.salva(new Aluno("Jeh","15489", "mail@mail.com"));
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoAdd = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioAlunoActivity();
            }
        });
    }

    private void abreFormularioAlunoActivity() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_list_view);

        final List<Aluno> alunos = alunoDAO.todos();

        listaDeAlunos.setAdapter(new ArrayAdapter<Aluno>(
                this,
                android.R.layout.simple_list_item_1,
                alunos) {
        });
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override //o terceiro e quarto argumentos renomeados
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                //classe log permite ver no LOGcat a acao desejada

                Aluno aluno = alunos.get(posicao);
                Log.i("Aluno", "" + aluno);
                Intent vaiParaFormulario = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);//mandar para formulario
                vaiParaFormulario.putExtra("aluno", aluno); // transformar objeto em serializable
                startActivity(vaiParaFormulario);

            }
        });
    }
}
