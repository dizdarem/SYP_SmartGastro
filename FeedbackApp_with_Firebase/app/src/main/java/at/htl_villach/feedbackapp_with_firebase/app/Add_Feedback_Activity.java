package at.htl_villach.feedbackapp_with_firebase.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import at.htl_villach.feedbackapp_with_firebase.R;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class Add_Feedback_Activity extends AppCompatActivity {
    public static final String OPINION_KEY = "opinion";
    public static final String RATE_KEY = "rate";

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
            case R.id.showFeedback:
                Intent intent = new Intent(Add_Feedback_Activity.this, MainActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);
        final Spinner spinner_rate = findViewById(R.id.spinner_rate);
        Button btn_sendFeedback = findViewById(R.id.btn_sendFeedback);
        final EditText input_opinion = findViewById(R.id.input_opinion);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        List<Integer> list_rate = Arrays.asList(1,2,3,4,5);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, list_rate);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_rate.setAdapter(dataAdapter);

        btn_sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opinion = input_opinion.getText().toString();
                int rate = (int) spinner_rate.getSelectedItem();
                if (opinion.isEmpty()) return;
                Map<String,Object> dataToSave = new HashMap<String,Object>();
                dataToSave.put(OPINION_KEY,opinion);
                dataToSave.put(RATE_KEY,rate);
                db.collection("Feedback")
                        .add(dataToSave)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            makeText(Add_Feedback_Activity.this, "Danke für Ihr Feedback!", LENGTH_SHORT).show();
                            Intent refresh = new Intent(Add_Feedback_Activity.this, Add_Feedback_Activity.class);
                            startActivity(refresh);
                        }
                        else{
                            System.out.println("----------------- error in send task:     " + task.getException());
                            makeText(Add_Feedback_Activity.this, "Fehler beim Senden von Feedback!", LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
