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

import androidx.appcompat.app.AppCompatActivity;
import at.htl_villach.android_app.R;
import at.htl_villach.android_app.bll.Produkt;
import at.htl_villach.android_app.bll.Warenkorb;
import at.htl_villach.android_app.dal.DatabaseManager;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;


public class WarenkorbActivity extends AppCompatActivity {
    DatabaseManager db = DatabaseManager.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warenkorb);
        final ListView lv_Warenkorb = findViewById(R.id.lv_warenkorb);
        Button btnreturn = findViewById(R.id.btn_return);
        Button btnAbschicken = findViewById(R.id.btn_confirmCart);

        final ArrayAdapter<Produkt> arrayAdapter = new ArrayAdapter<Produkt>(this,android.R.layout.simple_list_item_1, Warenkorb.warenkorb);
        lv_Warenkorb.setAdapter(arrayAdapter);
        lv_Warenkorb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(WarenkorbActivity.this);
                dialogbuilder.setTitle("Von Warenkorb entfernen?");
                dialogbuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Warenkorb.warenkorb.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        makeText(WarenkorbActivity.this, "Deleted from Cart", LENGTH_SHORT).show();
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
                try {
                    id = db.getCurrentIdBestellung() + 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Bestellung bestellung = new Bestellung(id, Calendar.getInstance().getTime(),);
                //db.addBestellung()
                Intent return2Home = new Intent(WarenkorbActivity.this, MainActivity.class);
                startActivity(return2Home);
            }
        });
    }

}
