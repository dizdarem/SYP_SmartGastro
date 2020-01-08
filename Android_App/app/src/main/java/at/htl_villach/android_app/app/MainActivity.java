package at.htl_villach.android_app.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import at.htl_villach.android_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("hello");
        Button btnGetraenke = findViewById(R.id.btn_Getraenke);
        Button btnSpeisen = findViewById(R.id.btn_Speisen);
        Button btnBeilagen = findViewById(R.id.btn_Beilagen);
        Button btnDesserts = findViewById(R.id.btn_Desserts);

        btnGetraenke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go2Getraenke = new Intent(MainActivity.this, GetraenkeActivity.class);
                startActivity(go2Getraenke);
            }
        });

        btnSpeisen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go2Speisen = new Intent(MainActivity.this, GetraenkeActivity.class);
                startActivity(go2Speisen);
            }
        });

        btnBeilagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go2Beilagen = new Intent(MainActivity.this, GetraenkeActivity.class);
                startActivity(go2Beilagen);
            }
        });

        btnDesserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go2Desserts = new Intent(MainActivity.this, GetraenkeActivity.class);
                startActivity(go2Desserts);
            }
        });

    }
}
