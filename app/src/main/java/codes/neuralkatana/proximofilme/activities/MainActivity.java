package codes.neuralkatana.proximofilme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import codes.neuralkatana.proximofilme.R;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_FILME = "FILME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
