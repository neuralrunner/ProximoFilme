package codes.neuralkatana.proximofilme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        //Retorna o Tipo da View
        int viewType = getItemViewType(position);
        //pega um item em uma posição específica
        ItemFilme filme = getItem(position);
        View itemView = convertView;

        //Após pegar a ViewType, realiza um switch para construir o layout de forma dinâmica
        switch (viewType){
            //Caso seja a view de destaque(ou seja o primeiro item)
            case VIEW_TYPE_DESTAQUE:{
                /*Não é necessário a implementação do View Holder pois o item de Destaque é chamado
                 *apenas uma vez.*/
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
                //Implementação do View Holder para Chamada de Diversos Items.
                ItemFilmeHolder holder;
                //Verifica se existe um ViewHolder, caso não cria um
                if(itemView.getTag() == null){
                    holder = new ItemFilmeHolder(itemView);
                    itemView.setTag(holder);
                } else {
                    //caso o ViewHolder já esteja criado, utiliza o mesmo.
                    holder = (ItemFilmeHolder) itemView.getTag();
                }
                holder.titulo.setText(filme.getTitulo());
                holder.desc.setText(filme.getDescricao());
                holder.filmeData.setText(filme.getDataLancamento());
                holder.filmeNota.setRating(filme.getAvaliacao());
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
    //Classe Aninhada para implementação do DP: ViewHolder
    public static class ItemFilmeHolder{
        TextView titulo;
        TextView desc;
        TextView filmeData;
        RatingBar filmeNota;
        ImageView poster;

        public ItemFilmeHolder(View view){
            titulo = view.findViewById(R.id.item_titulo);
            desc = view.findViewById(R.id.item_desc);
            filmeData = view.findViewById(R.id.item_data);
            filmeNota = view.findViewById(R.id.item_avaliacao);
            poster = view.findViewById(R.id.item_poster);
        }
    }
}
