package at.htl_villach.android_app.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
    Spinner spinner_tablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);

        db = DatabaseManager.newInstance();
        lv_Warenkorb = findViewById(R.id.lv_warenkorb);
        btnreturn = findViewById(R.id.btn_return);
        btnAbschicken = findViewById(R.id.btn_confirmCart);
        spinner_tisch = findViewById(R.id.spinner_tisch);
        spinner_tablet = findViewById(R.id.spinner_tablet);

        // TISCH
        ArrayList<Integer> listTisch = null;
        try {
            listTisch = db.getTische();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, listTisch);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tisch.setAdapter(dataAdapter);


        // TABLET
        ArrayList<Integer> listTablet = null;
        try {
            listTablet = db.getTablets();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        ArrayAdapter<Integer> dataAdapterTablet = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, listTablet);
        dataAdapterTablet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tablet.setAdapter(dataAdapterTablet);


        // adapter für Listview
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
            int idTisch, idTablet;
            @Override
            public void onClick(View v) {
                if (Warenkorb.warenkorb.isEmpty()) {
                    AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(WarenkorbActivity.this);
                    dialogbuilder.setTitle("Warenkorb ist leer!");
                    dialogbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent return2Home = new Intent(WarenkorbActivity.this, MainActivity.class);
                            startActivity(return2Home);

                        }
                    }).setNegativeButton("", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = dialogbuilder.create();
                    alertDialog.show();
                } else {
                    idTisch = (int) spinner_tisch.getSelectedItem();
                    idTablet = (int) spinner_tablet.getSelectedItem();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
                    String date = sdf.format(new Date());
                    Bestellung bestellung = new Bestellung(0, date, idTisch, idTablet, 0, false, Warenkorb.warenkorb);
                    try {
                        db.addBestellung(bestellung);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Warenkorb.warenkorb = new ArrayList<>();
                    Intent return2Home = new Intent(WarenkorbActivity.this, MainActivity.class);
                    startActivity(return2Home);
                }
            }
        });
    }

}
