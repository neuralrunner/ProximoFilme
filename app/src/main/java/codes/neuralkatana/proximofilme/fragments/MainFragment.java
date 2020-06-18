package codes.neuralkatana.proximofilme.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import codes.neuralkatana.proximofilme.R;
import codes.neuralkatana.proximofilme.activities.FilmeDetalheActivity;
import codes.neuralkatana.proximofilme.activities.MainActivity;
import codes.neuralkatana.proximofilme.adapters.FilmesAdapter;
import codes.neuralkatana.proximofilme.pojos.ItemFilme;

public class MainFragment extends Fragment {

    //Posição do item da lista, declarado inicialmente como invalido
    private int posicaoItem = ListView.INVALID_POSITION;

    //Chave para recuperação da posição com o savedInstanceState
    private static final String KEY_POSICAO = "SELECIONADO";

    //Listview declarada para facilitar a codificação dos métodos específicos
    private ListView list;

    private boolean useFilmeDestaque = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            //recupera se utilizará o filme destaque ou não
            //isso depende que estamos utilizando Celulares ou Tablets
            this.setUseFilmeDestaque(getArguments().getBoolean(MainActivity.KEY_DESTAQUE));
            //Log.i("isTablet","MainFragment(isUseFilmeDestaque): "+isUseFilmeDestaque());
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //EM CASO EXISTA UM FRAGMENT PRÉVIO
        //Remove todas as views
        if (container != null) {
            container.removeAllViews();
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container,false);

        list = view.findViewById(R.id.list_filmes);
        final ArrayList<ItemFilme> arrayList = returnArrayListItemFilmeMock();

        FilmesAdapter adapter = new FilmesAdapter(getContext(),arrayList);

        //Log.i("isTablet","MainFragment(isUseFilmeDestaque): "+isUseFilmeDestaque());
        adapter.setUseFilmeDestaque(isUseFilmeDestaque());

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemFilme itemFilme = arrayList.get(position);
                CallBack callBack = (CallBack) getActivity();
                callBack.onItemSelected(itemFilme);
                posicaoItem = position;
            }
        });

        if(savedInstanceState!= null && savedInstanceState.containsKey(KEY_POSICAO)){
            posicaoItem = savedInstanceState.getInt(KEY_POSICAO);
        }

        return view;
    }

    //Mockup de Filmes
    public ArrayList<ItemFilme> returnArrayListItemFilmeMock(){
        ArrayList<ItemFilme> arrayList = new ArrayList<>();

        arrayList.add(new ItemFilme("Predator", "Schwarzenegger as the leader of an " +
                "elite paramilitary rescue team on a mission to save hostages in guerrilla-held " +
                "territory in Central America, who encounter the deadly Predator (Kevin Peter Hall) " +
                "that is a technologically-advanced alien species that stalk and hunt the main characters.",
                "12/06/1987",5));
        arrayList.add(new ItemFilme("Alien", "Based on a story by O'Bannon and Ronald" +
                " Shusett, it follows the crew of the commercial space tug Nostromo, who encounter " +
                "the eponymous Alien, a deadly and aggressive extraterrestrial set loose on the ship.",
                "25/05/1979",5));
        arrayList.add(new ItemFilme("District 9", "When a population of sick and malnourished" +
                " insectoid aliens are discovered on the ship, the South African government confines" +
                " them to an internment camp called District 9.",
                "13/08/2009",4.8f));
        arrayList.add(new ItemFilme("Inception", "The film stars Leonardo DiCaprio " +
                "as a professional thief who steals information by infiltrating the subconscious of " +
                "his targets. He is offered a chance to have his criminal history erased as payment " +
                "for the implantation of another person's idea into a target's subconscious",
                "08/07/2010",4.5f));
        arrayList.add(new ItemFilme("Interstellar", "In 2067, crop blights and dust " +
                "storms threaten humanity's survival. Corn is the last viable crop. The world has " +
                "also regressed into a post-truth society where younger generations are taught " +
                "ideas such as the Apollo moon missions were faked. Widowed engineer and former " +
                "NASA pilot Joseph Cooper is now a farmer.",
                "26/10/2014",4));

        return arrayList;
    }

    //Salva o estado da Instância antes de uma Mudança de tela e etc.
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if(posicaoItem != ListView.INVALID_POSITION){
            outState.putInt(KEY_POSICAO,posicaoItem);
        }
        super.onSaveInstanceState(outState);
    }

    //Restaurando o estado da Instância trazendo a posição do listView previamente selecionado
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(posicaoItem!=ListView.INVALID_POSITION && list!=null){
            list.smoothScrollToPosition(posicaoItem);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_atualizar:
                Toast.makeText(getContext(),"Atualizando os Filmes...",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public interface CallBack{
        void onItemSelected(ItemFilme itemFilme);
    }

    public boolean isUseFilmeDestaque() {
        return useFilmeDestaque;
    }

    public void setUseFilmeDestaque(boolean useFilmeDestaque) {
        this.useFilmeDestaque = useFilmeDestaque;
    }
}