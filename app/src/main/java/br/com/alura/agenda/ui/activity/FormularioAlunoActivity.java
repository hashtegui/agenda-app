package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity implements ContantesActivities {

    private static final String TITULO_APP_BAR_NOVO_ALUNO = "Novo Aluno";
    private static final String TITULO_APP_BAR_EDITA_ALUNO = "Editar Aluno";
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoTelefone;
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Passou", "onCreate: passou no oncreate");
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaDosCampos();
        configuraBotaoSalvar();
        carregaAluno();

    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) { // verificando se existe o intent
            setTitle(TITULO_APP_BAR_EDITA_ALUNO);
            Log.i("Verificar: ", " passou no hash, aluno encontrado");
            aluno = (Aluno) dados.getSerializableExtra("aluno"); //pegando dados da lista de alunos
            Log.i("Valor aluno:", aluno.toString() + " " +aluno.getId());

            preencheCampos();
        } else{
            setTitle(TITULO_APP_BAR_NOVO_ALUNO);
            Log.i("Verificar: ", " não passou no hash, aluno nao encontrado");
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {

        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        campoTelefone.setText(aluno.getTelefone());
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        Log.i("Passou", "Configura botao salvar");
        preencheAluno();
        if(aluno.temIdValido()){
            Log.i("IF", "onClick: Tem id valido metodo editar");
            alunoDAO.edita(aluno);
        }else {
            Log.i("if", "onClick: nao tem id valido metodo editar");
            alunoDAO.salva(aluno);
        }
        Log.i("finish form", "onClick: ");
        finish();
    }

    private void inicializacaDosCampos() {
        Log.i("Passou", "Inicializando campos: passou no inicializando campos");
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
    }


    private void preencheAluno() {
        Log.i("Passou", "preenchendo o aluno");
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
    }
}