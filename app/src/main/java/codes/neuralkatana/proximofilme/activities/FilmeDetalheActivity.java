package codes.neuralkatana.proximofilme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import codes.neuralkatana.proximofilme.R;
import codes.neuralkatana.proximofilme.pojos.ItemFilme;

public class FilmeDetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme_detalhe);

        Intent intent = getIntent();
        ItemFilme itemFilme = (ItemFilme) intent.getSerializableExtra(MainActivity.KEY_FILME);

        TextView titulo = findViewById(R.id.item_titulo);
        TextView desc = findViewById(R.id.item_desc);
        TextView data = findViewById(R.id.item_data);
        RatingBar aval = findViewById(R.id.item_avaliacao);
        Button btnTrailer = findViewById(R.id.item_btn_trailer);

        titulo.setText(itemFilme.getTitulo());
        desc.setText(itemFilme.getDescricao());
        data.setText(itemFilme.getDataLancamento());
        aval.setRating(itemFilme.getAvaliacao());

    }
}