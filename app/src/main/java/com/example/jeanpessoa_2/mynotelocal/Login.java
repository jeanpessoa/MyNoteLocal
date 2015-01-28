package com.example.jeanpessoa_2.mynotelocal;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import com.example.jeanpessoa_2.mynotelocal.PersistenciaBD;

//
public class Login extends ActionBarActivity {
    static SQLiteDatabase BancoDados = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edLoginUser = (EditText) findViewById(R.id.edLoginUser);
        EditText edLoginPassword = (EditText) findViewById(R.id.edLoginPassword);
        Button btSignIn = (Button) findViewById(R.id.btSignIn);
        PersistenciaBD persistenciaBD = new PersistenciaBD();


/*
        //teste
        Log.i("Sistema", "Iniciando o teste de banco");
        Log.i("Sistema", "Criando banco");
        BancoDados = openOrCreateDatabase("Teste", MODE_WORLD_READABLE, null);
        Log.i("Sistema", "Inserindo tabelas");
        String SQL = "CREATE TABLE IF NOT EXISTS tabCadastroPessoa(_id INTEGER PRIMARY KEY, nomePessoa TEXT, senhaPessoa TEXT)";
        BancoDados.execSQL(SQL);
        Log.i("Sistema", "teste de criação de banco finalizado");
        BancoDados.close();
*/
        //fim do teste
/*
        //teste 2
        Log.i("Sistema", "Iniciar criação do banco ou abertura");
        BancoDados = openOrCreateDatabase("Teste2", MODE_WORLD_READABLE, null);
        Log.i("Sistema", "SQL para inserir tabelas");
        SQL = "CREATE TABLE IF NOT EXISTS Pessoa(pessoa_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, pessoa_nome TEXT, pessoa_idade INTEGER, pessoa_telefone INTEGER, pessoa_rua TEXT, pessoa_end_numero INTEGER, pessoa_end_bairro TEXT, pessoa_end_cidade TEXT, pessoa_usuario TEXT, pessoa_senha TEXT, pessoa_email TEXT) ";
        Log.i("Sistema", "Criando tabela Pessoa");
        BancoDados.execSQL(SQL);
        SQL = "CREATE TABLE IF NOT EXISTS Notas(pessoa_id INTEGER PRIMARY KEY, nomePessoa TEXT, senhaPessoa TEXT) ";
        Log.i("Sistema", "Criando tabela Notas");
        BancoDados.execSQL(SQL);
        SQL = "CREATE TABLE IF NOT EXISTS Disciplina(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nomePessoa TEXT, senhaPessoa TEXT) ";
        Log.i("Sistema", "Criando tabela Disciplina");
        BancoDados.execSQL(SQL);
        SQL = "CREATE TABLE IF NOT EXISTS Curso(_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nomePessoa TEXT, senhaPessoa TEXT) ";
        Log.i("Sistema", "Criando tabela Curso");
        BancoDados.execSQL(SQL);
        SQL = "CREATE TABLE IF NOT EXISTS Disciplina2(_id INTEGER PRIMARY KEY, nomePessoa TEXT, senhaPessoa TEXT) ";
        Log.i("Sistema", "Criando tabela disciplina2");
        BancoDados.execSQL(SQL);

        Log.i("Sistema", "Fim da execução SQL");
        //fim do teste2
*/
        Log.i("Sistema", "iniciar SetAbrirBanco em persistencia");
        persistenciaBD.setAbrirBanco();
        //BancoDados.close();


    }

//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
