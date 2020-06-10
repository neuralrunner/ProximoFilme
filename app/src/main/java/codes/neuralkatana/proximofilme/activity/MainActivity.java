package codes.neuralkatana.proximofilme.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import codes.neuralkatana.proximofilme.R;
import codes.neuralkatana.proximofilme.adapter.FilmesAdapter;
import codes.neuralkatana.proximofilme.pojo.ItemFilme;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = findViewById(R.id.list_filmes);
        ArrayList<ItemFilme> arrayList = returnArrayListItemFilmeMock();

        FilmesAdapter adapter = new FilmesAdapter(this,arrayList);
        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_atualizar:
                Toast.makeText(this,"Atualizando os Filmes...",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
}
