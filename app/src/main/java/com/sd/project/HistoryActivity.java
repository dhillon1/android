package com.sd.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<HistoryDoc> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.recyclerView);
        firebaseFirestore = FirebaseFirestore.getInstance();
        list =  new ArrayList<>();


        firebaseFirestore.collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            if(task.getResult().size()==0){

                            }
                            else{
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    HistoryDoc historyDoc = new HistoryDoc();
                                    historyDoc.setIp(document.getString("ip"));
                                    historyDoc.setCity(document.getString("city"));
                                    historyDoc.setCountry(document.getString("country"));
                                    historyDoc.setLoc(document.getString("loc"));
                                    historyDoc.setTimestamp(document.getString("timestamp"));
                                    historyDoc.setRegion(document.getString("region"));
                                    historyDoc.setTimezone(document.getString("timezone"));
                                    list.add(historyDoc);

                                }

                                recyclerView.setHasFixedSize(true);

                                layoutManager = new LinearLayoutManager(HistoryActivity.this);
                                recyclerView.setLayoutManager(layoutManager);

                                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(HistoryActivity.this,list);
                                recyclerView.setAdapter(recyclerAdapter);

                            }

                        }
                    }
                });


    }
}
