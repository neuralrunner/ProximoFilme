package codes.neuralkatana.proximofilme.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import codes.neuralkatana.proximofilme.R;
import codes.neuralkatana.proximofilme.adapter.FilmesAdapter;
import codes.neuralkatana.proximofilme.pojo.ItemFilme;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_FILME = "FILME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
