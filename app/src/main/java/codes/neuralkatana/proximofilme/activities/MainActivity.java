package codes.neuralkatana.proximofilme.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import codes.neuralkatana.proximofilme.R;
import codes.neuralkatana.proximofilme.fragments.FilmeDetalheFragment;
import codes.neuralkatana.proximofilme.fragments.MainFragment;
import codes.neuralkatana.proximofilme.pojos.ItemFilme;

public class MainActivity extends AppCompatActivity implements MainFragment.CallBack {
    public static final String KEY_FILME = "FILME";
    private boolean isTablet = false;
    public static final String KEY_DESTAQUE = "USE_DESTAQUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set o Fragment de FilmeDetalhe
        fragmentFilmeDetalhe(savedInstanceState);

        //Verifica se é celular ou Tablet e faz o Reload da MainFragment
        useFilmeDestaqueMainFragment(!isTablet);
    }


    @Override
    public void onItemSelected(ItemFilme itemFilme) {
        if(isTablet){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FilmeDetalheFragment detalheFragment = new FilmeDetalheFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(MainActivity.KEY_FILME,itemFilme);
            detalheFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_filme_detalhe, detalheFragment);
            fragmentTransaction.commit();
        }else{
            Intent intent = new Intent(this,FilmeDetalheActivity.class);
            intent.putExtra(MainActivity.KEY_FILME,itemFilme);
            startActivity(intent);
        }
    }

    public void useFilmeDestaqueMainFragment(boolean useFilmeDestaque){
        //Passando o Argumento se é ou não um Tablet para o MainFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(MainActivity.KEY_DESTAQUE,useFilmeDestaque);
        mainFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.main_fragment, mainFragment);
        fragmentTransaction.commit();
    }

    public void fragmentFilmeDetalhe(Bundle savedInstanceState){
        if (findViewById(R.id.fragment_filme_detalhe) != null) {
            if (savedInstanceState == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_filme_detalhe, new FilmeDetalheFragment())
                        .commit();
            }
            isTablet = true;
            //Log.i("isTablet","MainActivity: "+isTablet());
        } else {
            isTablet = false;
            //Log.i("isTablet","MainActivity: "+isTablet());
        }
    }
}
