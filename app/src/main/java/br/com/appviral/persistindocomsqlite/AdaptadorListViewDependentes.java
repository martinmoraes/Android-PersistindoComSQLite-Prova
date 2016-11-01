package br.com.appviral.persistindocomsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.com.appviral.persistindocomsqlite.Entidade.Dependente;
import br.com.appviral.persistindocomsqlite.Entidade.Pessoa;
import br.com.appviral.persistindocomsqlite.Persistencia.DependentesDAO;
import br.com.appviral.persistindocomsqlite.Persistencia.PessoaDAO;

/**
 * Created by Martin on 14/05/2016.
 */
public class AdaptadorListViewDependentes extends BaseAdapter {

    LayoutInflater layoutInflater;
    DependentesDAO dependentesDAO;

    public AdaptadorListViewDependentes(Context context, long id) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dependentesDAO = new DependentesDAO(context, id);
    }


    @Override
    public int getCount() {
        return DependentesDAO.listaDependentes.size();
    }

    @Override
    public Object getItem(int position) {
        return DependentesDAO.listaDependentes.get(position);
    }

    @Override
    public long getItemId(int position) {
        Dependente dependente = DependentesDAO.listaDependentes.get(position);
        return dependente.id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_item_listview_dependentes, null);
        }

        TextView nomeTextView = (TextView) convertView.findViewById(R.id.nomeTextView);
        TextView deMenorTextView = (TextView) convertView.findViewById(R.id.deMenorTextView);

        Dependente dependente = DependentesDAO.listaDependentes.get(position);

        nomeTextView.setText(dependente.nome);
        deMenorTextView.setText(dependente.getDeMaior());

        return convertView;
    }



}

