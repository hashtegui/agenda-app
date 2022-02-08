package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 0;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void edita(Aluno aluno) {
        Aluno econtrado = null;
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                econtrado = a;
            }
        }
        if (econtrado != null) {
            int posicao = alunos.indexOf(econtrado);
            alunos.set(posicao, aluno);
        }
    }
}
