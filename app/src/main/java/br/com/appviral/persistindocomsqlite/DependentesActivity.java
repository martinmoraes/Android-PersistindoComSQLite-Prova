package br.com.appviral.persistindocomsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import br.com.appviral.persistindocomsqlite.Entidade.Dependente;
import br.com.appviral.persistindocomsqlite.Entidade.Pessoa;
import br.com.appviral.persistindocomsqlite.Persistencia.DependentesDAO;
import br.com.appviral.persistindocomsqlite.Persistencia.PessoaDAO;

public class DependentesActivity extends AppCompatActivity {
    Long id_pessoa = 0l;
    DependentesDAO dependentesDAO;
    String operacao;
    int posicao;
    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependentes);

        operacao = getIntent().getStringExtra("OPERACAO");
        posicao = getIntent().getIntExtra("POSICAO", 0);
        id_pessoa = getIntent().getLongExtra("ID_PESSOA", 0);
        id = getIntent().getLongExtra("ID", 0);


        EditText nomeEditText = (EditText) findViewById(R.id.etNome);
        RadioButton simRadioButton = (RadioButton) findViewById(R.id.simRadioButton);
        RadioButton naoRadioButton = (RadioButton) findViewById(R.id.naoRadioButton);

        if (operacao.equals("alterar")) {
            Dependente dependente = DependentesDAO.listaDependentes.get(posicao);
            assert nomeEditText != null;
            nomeEditText.setText(dependente.nome);
            if (dependente.deMaior) {
                assert simRadioButton != null;
                simRadioButton.setActivated(true);
            }
            else {
                assert naoRadioButton != null;
                naoRadioButton.setActivated(true);
            }
        }


        dependentesDAO = new DependentesDAO(this, id_pessoa);
    }


    public void fechar(View view) {
        finish();
    }


    public void salvar(View view) {
        Dependente umDependente = new Dependente();
        umDependente.id = id;
        umDependente.nome = ((EditText) findViewById(R.id.etNome)).getText().toString();
        RadioButton sim = (RadioButton) findViewById(R.id.simRadioButton);
        if (sim.isChecked())
            umDependente.deMaior = true;
        else umDependente.deMaior = false;


        if(operacao.equals("alterar")){
            dependentesDAO.alterar(umDependente);
        }else {
            dependentesDAO.inserir(umDependente, id_pessoa);
        }
        finish();

    }


    public void excluir(View view) {
        if (operacao.equals("alterar")) {
            if (dependentesDAO.excluir(id)) {
                Toast.makeText(this, "Excluido com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Operação não realizada!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
