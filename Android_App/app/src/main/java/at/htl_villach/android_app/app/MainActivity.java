package at.htl_villach.android_app.app;

import androidx.appcompat.app.AppCompatActivity;
import at.htl_villach.android_app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                Intent intent = new Intent(MainActivity.this, GetraenkeActivity.class);
                startActivity(intent);
            }
        });
    }
}
