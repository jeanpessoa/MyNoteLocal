

package com.example.jeanpessoa_2.mynotelocal;


/**
 * Created by Jean-NOTE on 27/01/2015.
 */

        import com.example.jeanpessoa_2.mynotelocal.R.string;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.LauncherActivity.ListItem;
        import android.support.v4.widget.SimpleCursorAdapter;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.database.Cursor;
        import android.content.Context;

        //import static android.database.sqlite.SQLiteDatabase.*;

public class PersistenciaBD extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //EditText EditNome, EditSenha;
    //Button Salvar;
    //Button Buscar;
    //Button Apagar;

    //usado pelo criabanco
    private String nomeBanco = "CadastroBD";
    private String SQL;
    private SQLiteDatabase BancoDados = null;
    Cursor cursor;
    //ListView MostraDados;
    //SimpleCursorAdapter AdaptaLista;
    //public static final String KEY_NOME_PESSOA = "nomePessoa";
    int id = -1;
    //String Aid;


    private void Inicializar(){

        //EditNome = (EditText) findViewById(R.id.idnome);
        //EditSenha = (EditText) findViewById(R.id.idsenha);

        //Salvar = (Button) findViewById(R.id.BtnSalvar);
        //Apagar = (Button) findViewById(R.id.BtnApagar);
        //Apagar.setVisibility(View.INVISIBLE);
        //Buscar = (Button) findViewById(R.id.BtnMostrar);
        //AbrirBanco();
        //MostraDados = (ListView) findViewById(R.id.MostraDados1);
        //GravaBanco();
//			CarregaDado();
//			btnSalvarDados();
//			btnApagarDados();
        FecharBanco();

    }

    private void AbrirBanco(){
        //
        //String nomeBanco = "CadastroBD";
        //SQLiteDatabase BancoDados = null;
        try{

            //SQL INACABADO
            Log.i("Sistema", "Iniciar criação do banco ou abertura");
            BancoDados = openOrCreateDatabase(nomeBanco, Context.MODE_WORLD_READABLE, null);
            Log.i("Sistema", "SQL para inserir tabelas");
            //INSERINDO PESSOA
            SQL = "CREATE TABLE IF NOT EXISTS Pessoa(pessoa_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, pessoa_nome TEXT, pessoa_idade INTEGER, pessoa_telefone INTEGER, pessoa_rua TEXT, pessoa_end_numero INTEGER, pessoa_end_bairro TEXT, pessoa_end_cidade TEXT, pessoa_usuario TEXT, pessoa_senha TEXT, pessoa_email TEXT)";
            Log.i("Sistema", "Criando tabela Pessoa");
            BancoDados.execSQL(SQL);
            //INSERINDO NOTAS
            SQL = "CREATE TABLE IF NOT EXISTS Notas(pessoa_id INTEGER FOREIGN KEY REFERENCES Pessoa(pessoa_id), disciplina_id INTEGER FOREIGN KEY REFERENCES Disciplina(disciplina_id), nota_n1 FLOAT, nota_n2 FLOAT, nota_n3 FLOAT, nota_n4 FLOAT)";
            Log.i("Sistema", "Criando tabela Notas");
            BancoDados.execSQL(SQL);
            //INSERINDO DISCIPLINA
            SQL = "CREATE TABLE IF NOT EXISTS Disciplina(disciplina_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, disciplina_cod INTEGER, disciplina_nome TEXT, disciplina_obs TEXT)";
            Log.i("Sistema", "Criando tabela Disciplina");
            BancoDados.execSQL(SQL);
            //INSERINDO CURSO
            SQL = "CREATE TABLE IF NOT EXISTS Curso(curso_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, curso_cod INTEGER, curso_nome TEXT)";
            Log.i("Sistema", "Criando tabela Curso");
            BancoDados.execSQL(SQL);
            //INSERINDO DISCIPLINA 2
            SQL = "CREATE TABLE IF NOT EXISTS Disciplina2(curso_id INTEGER FOREIGN KEY REFERENCES Curso(curso_id), disciplina_id INTEGER FOREIGN KEY REFERENCES Disciplina(disciplina_id)";
            Log.i("Sistema", "Criando tabela disciplina2");
            BancoDados.execSQL(SQL);

            BancoDados.close();

            Log.i("Sistema", "Fim da execução SQL");
            MensagemAlerta.alerta("Banco de Dados", "Banco criado com sucesso!", this);
        }catch(Exception erro){

            //a mensagem deve ser chamada pela activity
            MensagemAlerta.alerta("Banco de Dados", "Não foi possível criar ou abrir o banco!"+erro, this);
        }

    }
    private void FecharBanco(){
        //
        try{
            BancoDados.close();
        }catch(Exception erro){

            //a mensagem deve ser chamada pela activity
            MensagemAlerta.alerta("Banco de Dados", "Não foi possível fechar o banco!"+erro, this);
        }

    }

    private void Gravar(int posicao, String usuario, String senha) {
        AbrirBanco();
        //
        if(posicao == -1){
            try{
                BancoDados = openOrCreateDatabase(nomeBanco, MODE_WORLD_READABLE, null);
                //String SQL = "INSERT INTO tabCadastroPessoa (nomePessoa, senhaPessoa) VALUES ('"+EditNome.getText().toString()+"', '"+EditSenha.getText().toString()+"')";
                String SQL = "INSERT INTO tabCadastroPessoa (nomePessoa, senhaPessoa) VALUES ('"+usuario+"', '"+senha+"')";
                BancoDados.execSQL(SQL);

                //a mensagem deve ser chamada pela activity
                MensagemAlerta.alerta("Banco de Dados", "Registro inserido!",this);
            }catch(Exception erro){

                //a mensagem deve ser chamada pela activity
                MensagemAlerta.alerta("Banco de Dados", "Não foi possível inserir o registro!"+erro,this);
            }
            finally{
                FecharBanco();
            }
        }
        else{
            Alterar(posicao, usuario, senha);
        }

        FecharBanco();
    }
    private boolean Alterar(int p, String usuario, String senha){
        AbrirBanco();
        //
        try{
            String sql = "UPDATE tabCadastroPessoa SET nomePessoa = '"+usuario+"', "
                    + "senhaPessoa = '"+senha+"' WHERE _id = '"+id+"'";
            BancoDados.execSQL(sql);

            //a mensagem deve ser chamada pela activity
            MensagemAlerta.alerta("Exito!", "Dados alterados com sucesso!",this);
            return true;
        }catch (Exception erro) {
            //a mensagem deve ser chamada pela activity
            MensagemAlerta.alerta("Erro!!!", "Não foi possivel alterar a seleção!!!"+erro,this);
            return false;}

        finally{
            FecharBanco();
        }
        //FecharBanco();
    }
    public void Apagar(){
        AbrirBanco();
        //

        FecharBanco();
    }


    //get and set


        /*public static void getBancoDados() {
            return BancoDados;
        }*/

    public  void setAbrirBanco() {
        AbrirBanco();
    }

    public void setFecharBanco(){
        FecharBanco();
    }


    public void setGravar(int posicao, String usuario, String senha) {
        Gravar(posicao, usuario, senha);
    }

    public void setAlterar(int p, String usuario, String senha){
        Alterar(p,usuario,senha);
    }


}