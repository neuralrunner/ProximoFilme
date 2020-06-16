package codes.neuralkatana.proximofilme.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container,false);

        ListView list = view.findViewById(R.id.list_filmes);
        final ArrayList<ItemFilme> arrayList = returnArrayListItemFilmeMock();

        FilmesAdapter adapter = new FilmesAdapter(getContext(),arrayList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemFilme itemFilme = arrayList.get(position);
                Intent intent = new Intent(getContext(), FilmeDetalheActivity.class);
                intent.putExtra(MainActivity.KEY_FILME,itemFilme);
                startActivity(intent);
            }
        });


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
        arrayList.add(new ItemFilme("Inception", " The film stars Leonardo DiCaprio " +
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
}