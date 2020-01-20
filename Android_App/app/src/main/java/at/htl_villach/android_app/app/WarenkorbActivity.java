package at.htl_villach.android_app.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import at.htl_villach.android_app.R;
import at.htl_villach.android_app.bll.Bestellung;
import at.htl_villach.android_app.bll.Produkt;
import at.htl_villach.android_app.bll.Warenkorb;
import at.htl_villach.android_app.dal.DatabaseManager;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;


public class WarenkorbActivity extends AppCompatActivity {
    DatabaseManager db = DatabaseManager.newInstance();
    ListView lv_Warenkorb;
    Button btnreturn;
    Button btnAbschicken;
    Spinner spinner_tisch;
    int currentTisch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);

        db = DatabaseManager.newInstance();
        lv_Warenkorb = findViewById(R.id.lv_warenkorb);
        btnreturn = findViewById(R.id.btn_return);
        btnAbschicken = findViewById(R.id.btn_confirmCart);
        spinner_tisch = findViewById(R.id.spinner_tisch);
        currentTisch = 0;

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tisch.setAdapter(dataAdapter);

        final ArrayAdapter<Produkt> arrayAdapter = new ArrayAdapter<Produkt>(this, android.R.layout.simple_list_item_1, Warenkorb.warenkorb);
        lv_Warenkorb.setAdapter(arrayAdapter);

        lv_Warenkorb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(WarenkorbActivity.this);
                dialogbuilder.setTitle("Vom Warenkorb entfernen?");
                dialogbuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Warenkorb.warenkorb.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        makeText(WarenkorbActivity.this, "von Warenkorb entfernt", LENGTH_SHORT).show();
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

        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent return2Home = new Intent(WarenkorbActivity.this, MainActivity.class);
                startActivity(return2Home);
            }
        });
        btnAbschicken.setOnClickListener(new View.OnClickListener() {
            int id;
            @Override
            public void onClick(View v) {
                    String subscriberId = Settings.Secure.getString(getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                makeText(WarenkorbActivity.this, id, LENGTH_SHORT).show();
                Bestellung bestellung = new Bestellung(0, Calendar.getInstance().getTime(),0,153,0,false);
                try {
                    db.addBestellung(bestellung);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent return2Home = new Intent(WarenkorbActivity.this, MainActivity.class);
                startActivity(return2Home);
            }
        });
    }

}
