package codes.neuralkatana.proximofilme.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import codes.neuralkatana.proximofilme.R;
import codes.neuralkatana.proximofilme.activities.MainActivity;
import codes.neuralkatana.proximofilme.pojos.ItemFilme;

public class FilmeDetalheFragment extends Fragment {

    private ItemFilme itemFilme;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            itemFilme = (ItemFilme) getArguments().getSerializable(MainActivity.KEY_FILME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filme_detalhe, container, false);

        if(itemFilme == null){
            return view;
        }

        TextView titulo = view.findViewById(R.id.item_titulo);
        TextView desc = view.findViewById(R.id.item_desc);
        TextView data = view.findViewById(R.id.item_data);
        RatingBar aval = view.findViewById(R.id.item_avaliacao);
        //Button btnTrailer = view.findViewById(R.id.item_btn_trailer);

        titulo.setText(itemFilme.getTitulo());
        desc.setText(itemFilme.getDescricao());
        data.setText(itemFilme.getDataLancamento());
        aval.setRating(itemFilme.getAvaliacao());

        return view;
    }
}