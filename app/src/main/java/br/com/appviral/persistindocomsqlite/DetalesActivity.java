package br.com.appviral.persistindocomsqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import br.com.appviral.persistindocomsqlite.Entidade.Pessoa;
import br.com.appviral.persistindocomsqlite.Persistencia.PessoaDAO;

public class DetalesActivity extends AppCompatActivity {
    String operacao;
    int posicao;
    Long id;
    EditText etNome, etFone, etEmail;
    PessoaDAO pessoaDAO;
    AdaptadorListViewDependentes adaptadorListViewDependentes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detales);


        operacao = getIntent().getStringExtra("OPERACAO");
        posicao = getIntent().getIntExtra("POSICAO", 0);
        id = getIntent().getLongExtra("ID", 0);


        etNome = (EditText) findViewById(R.id.etNome);
        etFone = (EditText) findViewById(R.id.etFone);
        etEmail = (EditText) findViewById(R.id.etEMail);

        if (operacao.equals("alterar")) {
            Pessoa pessoa = PessoaDAO.listaPessoas.get(posicao);
            etNome.setText(pessoa.nome);
            etFone.setText(pessoa.fone);
            etEmail.setText(pessoa.email);
        }
        pessoaDAO = new PessoaDAO(this);


    }

    @Override
    protected void onResume() {

        adaptadorListViewDependentes = new AdaptadorListViewDependentes(this, id);
        ListView listView = (ListView) findViewById(R.id.listViewDependentes);
        listView.setAdapter(adaptadorListViewDependentes);
        final Context context = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context,DependentesActivity.class);
                intent.putExtra("OPERACAO", "alterar");
                intent.putExtra("POSICAO", position);
                intent.putExtra("ID", id);

                startActivity(intent);
            }
        });
        super.onResume();
    }

    public void salvar(View view) {
        if (operacao.equals("inserir")) {
            Pessoa umaPessoa = new Pessoa();
            umaPessoa.nome = etNome.getText().toString();
            umaPessoa.fone = etFone.getText().toString();
            umaPessoa.email = etEmail.getText().toString();
            if (pessoaDAO.inserir(umaPessoa)) {
                Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
//                etNome.setText("");
//                etFone.setText("");
//                etEmail.setText("");
            } else {
                Toast.makeText(this, "Operação não realizada!", Toast.LENGTH_SHORT).show();
            }
        }


        if (operacao.equals("alterar")) {
            Pessoa umaPessoa = new Pessoa();
            umaPessoa.id = id;
            umaPessoa.nome = etNome.getText().toString();
            umaPessoa.fone = etFone.getText().toString();
            umaPessoa.email = etEmail.getText().toString();
            if (pessoaDAO.alterar(umaPessoa)) {
                Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                etNome.setText("");
                etFone.setText("");
                etEmail.setText("");
            } else {
                Toast.makeText(this, "Operação não realizada!", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    public void excluir(View view) {
        if (operacao.equals("alterar")) {
            if (pessoaDAO.excluir(id)) {
                Toast.makeText(this, "Excluido com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Operação não realizada!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void inserir(View view) {
        Intent intent = new Intent(this, DependentesActivity.class);
        intent.putExtra("OPERACAO", "inserir");
        intent.putExtra("ID_PESSOA", id);
        startActivity(intent);
    }

    public void fechar(View view) {
        finish();
    }
}
