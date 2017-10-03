package com.example.renan.agenda2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.renan.agenda2.dao.AlunoDAO;
import com.example.renan.agenda2.modelo.Aluno;

/**
 * Created by renan on 27/08/2017.
 * 1ºessa classe foi criada para evitar as repetições do findviewbyid não é bom em orientação a objetos.
 * 2º temos q passar um parametro uma referencia receber uma activity formulario para usar o finview
 * 3ºtemos que instancia a formulariohelper para usarmos ela em algum lugar ou seja dentro do onCreate assim q cria cria um FH new pede uma
 * referencia e botamos this para se referenciar a nossa activity
 */

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //esse inflate serve para transformar o xml em um menu de verdade
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.getAluno();
                                            //esse diz q dizer q vai passar uma referencia para nossa activity
                AlunoDAO dao = new AlunoDAO(this);
                dao.insere(aluno);
                dao.close();
                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
