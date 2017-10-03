package com.example.renan.agenda2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.renan.agenda2.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renan on 28/08/2017.
 */

public class AlunoDAO extends SQLiteOpenHelper {
    public AlunoDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //vai chamar sempre q precisar criar o banco
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        db.execSQL(sql);
        onCreate(db);

    }

    public void insere(Aluno aluno) {
                            //queremos uma referencia do DB para que possamos escrever esse getWritableDatabase
                            //ja temos a referencia do DB
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        //quero colocar na coluna nome e o conteúdo é o que diz a instrução
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());
        //vai limpar os valores caso o cara queira quebrar a aplicação o insert vai tratar
                                    //passa o contentValues como parâmetros
        db.insert("Alunos", null, dados);
    }

    public List<Aluno> buscaAlunos() {

            String sql = "SELECT * from Alunos;";
            SQLiteDatabase db = getReadableDatabase();
                         //raw informa ao sql curso aponta e temos q mover ele para ler a próxima linha instanciamos e pedimos para ler oq foi pedido
           //damos um c.close para o cursor liberar e fechar
            //tivemos q fazer com o adpter q so chama o tostring e sobrescrever o tostring
            Cursor c = db.rawQuery(sql, null);

            List<Aluno> alunos = new ArrayList<>();
            while(c.moveToNext()){
                Aluno aluno = new Aluno();
                aluno.setId(c.getLong(c.getColumnIndex("id")));
                aluno.setNome(c.getString(c.getColumnIndex("nome")));
                aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
                aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
                aluno.setSite(c.getString(c.getColumnIndex("site")));
                aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
                alunos.add(aluno);
            }
            c.close();
            return alunos;
        }
    }

