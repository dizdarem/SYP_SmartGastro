package at.htl_villach.android_app.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import at.htl_villach.android_app.R;
import at.htl_villach.android_app.bll.Produkt;
import at.htl_villach.android_app.bll.Warenkorb;
import at.htl_villach.android_app.dal.DatabaseManager;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;


public class BeilageActivity extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.showWarenkorb:
                Intent intent = new Intent(BeilageActivity.this, WarenkorbActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beilagen);

        final ListView lv_Beilagen = findViewById(R.id.lv_beilagen);
        DatabaseManager db = DatabaseManager.newInstance();
        Button btnreturn = findViewById(R.id.btn_return);

        ArrayList<Produkt> list = new ArrayList<Produkt>();
        try{
        list = db.getAllBeilagen();
        System.out.println(list.get(0));
        }
        catch (Exception ex){
            System.out.println("-----------------------------------error in GETAllBeilagen from db");
            ex.printStackTrace();
        }
        ArrayAdapter<Produkt> arrayAdapter = new ArrayAdapter<Produkt>(this,android.R.layout.simple_list_item_1,list);
        lv_Beilagen.setAdapter(arrayAdapter);

        lv_Beilagen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(BeilageActivity.this);
                dialogbuilder.setTitle("Zum Warenkorb hinzufügen?");
                dialogbuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Warenkorb.warenkorb.add((Produkt) lv_Beilagen.getItemAtPosition(position));
                        dialog.cancel();
                        makeText(BeilageActivity.this, "Zu Warenkorb hinzugefügt", LENGTH_SHORT).show();

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
                Intent return2Home = new Intent(BeilageActivity.this, MainActivity.class);
                startActivity(return2Home);
            }
        });
    }

}
