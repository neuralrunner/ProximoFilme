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
    public FilmesAdapter(Context context, ArrayList<ItemFilme> filmes){
        super(context,0,filmes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView==null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_filme, parent,false);
        }
        ItemFilme filme = getItem(position);

        TextView titulo = itemView.findViewById(R.id.item_titulo);
        titulo.setText(filme.getTitulo());

        TextView desc =  itemView.findViewById(R.id.item_desc);
        desc.setText(filme.getDescricao());

        TextView filmeData = itemView.findViewById(R.id.item_data);
        filmeData.setText(filme.getDataLancamento());

        RatingBar filmeNota = itemView.findViewById(R.id.item_avaliacao);
        filmeNota.setRating(filme.getAvaliacao());

        return itemView;
    }
}
