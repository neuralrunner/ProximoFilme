package codes.neuralkatana.proximofilme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import codes.neuralkatana.proximofilme.R;
import codes.neuralkatana.proximofilme.pojo.ItemFilme;

public class FilmesAdapter extends ArrayAdapter<ItemFilme> {
    private static final int VIEW_TYPE_DESTAQUE = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    public FilmesAdapter(Context context, ArrayList<ItemFilme> filmes){
        super(context,0,filmes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int viewType = getItemViewType(position);

        //pega um item em uma posição específica
        ItemFilme filme = getItem(position);
        View itemView = convertView;

        //Após pegar a ViewType, realiza um switch para construir o layout de forma dinâmica
        switch (viewType){
            //Caso seja a view de destaque(ou seja o primeiro item)
            case VIEW_TYPE_DESTAQUE:{
                itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_filme_destaque, parent,false);
                TextView desc =  itemView.findViewById(R.id.item_desc);
                desc.setText(filme.getDescricao());

                RatingBar filmeNota = itemView.findViewById(R.id.item_avaliacao);
                filmeNota.setRating(filme.getAvaliacao());
                break;
            }
            //Qualquer outro item é exibido como um item comum
            case VIEW_TYPE_ITEM: {
                itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_filme, parent,false);
                /*Settando os elementos da ListView(utilização de ViewHolder é mais adequeada
                 * devido ao alto custo de encontrar cada elemento e criar o mesmo. Utilizando
                 * o padrão VH, as chamadas são reduzidas através de um elemento estático apenas.*/
                TextView titulo = itemView.findViewById(R.id.item_titulo);
                titulo.setText(filme.getTitulo());

                TextView desc =  itemView.findViewById(R.id.item_desc);
                desc.setText(filme.getDescricao());

                TextView filmeData = itemView.findViewById(R.id.item_data);
                filmeData.setText(filme.getDataLancamento());

                RatingBar filmeNota = itemView.findViewById(R.id.item_avaliacao);
                filmeNota.setRating(filme.getAvaliacao());
                break;
            }
        }
        return itemView;
    }

    @Override
    public int getItemViewType(int position) {
        //Operação Ternária, caso seja a view de destaque
        //retornará o valor 0, se não todos as outras view são
        //Itens comuns e retornará o valor 1.
        return (position == 0 ? VIEW_TYPE_DESTAQUE : VIEW_TYPE_ITEM);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
