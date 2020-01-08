package at.htl_villach.android_app.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import android.app.AlertDialog;

import at.htl_villach.android_app.R;
import at.htl_villach.android_app.bll.Produkt;
import at.htl_villach.android_app.bll.typ;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;
import androidx.appcompat.app.AppCompatActivity;


public class GetraenkeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getraenke);
        ListView lv_Getraenke = findViewById(R.id.lv_getraenke);

        ArrayList<Produkt> list = new ArrayList<Produkt>();
        list.add(new Produkt(1,"Coca Cola", 2.50, typ.GETRAENK));
        list.add(new Produkt(2,"Fanta", 2.50, typ.GETRAENK));
        list.add(new Produkt(3,"Sprite", 2.50, typ.GETRAENK));
        ArrayAdapter<Produkt> arrayAdapter = new ArrayAdapter<Produkt>(this,android.R.layout.simple_list_item_1,list);
        lv_Getraenke.setAdapter(arrayAdapter);

        lv_Getraenke.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(GetraenkeActivity.this);
                dialogbuilder.setTitle("Zum Warenkorb hinzufügen?");
                dialogbuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        makeText(GetraenkeActivity.this, "Added to Cart", LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = dialogbuilder.create();
                alertDialog.show();
            }
        });
    }

}
