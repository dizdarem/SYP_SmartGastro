
package at.htl_villach.feedbackapp_with_firebase.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import at.htl_villach.feedbackapp_with_firebase.R;
import at.htl_villach.feedbackapp_with_firebase.bll.Feedback;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Feedback> list_feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        final ListView lv_feedback = findViewById(R.id.lv_feedback);
        list_feedback = new ArrayList<Feedback>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Feedback")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc : task.getResult()){
                        Feedback fb = doc.toObject(Feedback.class);
                        list_feedback.add(fb);
                    }
                    ArrayAdapter<Feedback> arrayAdapter = new ArrayAdapter<Feedback>(getApplicationContext(),android.R.layout.simple_list_item_1,list_feedback);
                    lv_feedback.setAdapter(arrayAdapter);
                }
                else {
                    System.out.println("------------------------------------------------error in getting documents: " + task.getException());
                }
            }
        });
    }
}
