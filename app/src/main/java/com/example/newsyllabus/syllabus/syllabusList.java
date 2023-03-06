package com.example.newsyllabus.syllabus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.newsyllabus.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.ErrorListener;

public class syllabusList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    Adapter adapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_list);

//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Fetching Data");
//        progressDialog.show();

        recyclerView =(RecyclerView) findViewById(R.id.recycleView);
        //  recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User>();
        adapter = new Adapter(userArrayList,this);
        recyclerView.setAdapter(adapter);

        db.collection("syllabus").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                              Log.d("Attension",d.toString());
                            User obj = d.toObject(User.class);
                            userArrayList.add(obj);
                        }

                        adapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(syllabusList.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        //  EventChangeListener();


    }

//    private void EventChangeListener() {
//        db.collection("syllabus")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                        if (error != null) {
//
//                            if (progressDialog!=null&& progressDialog.isShowing())
//                                progressDialog.dismiss();
//
//                            Log.e("Firestore error", error.getMessage());
//                            return;
//                        }
//                        for (DocumentChange dc : value.getDocumentChanges()) {
//
//                            if (dc.getType() == DocumentChange.Type.ADDED) {
//                                userArrayList.add(dc.getDocument().toObject(User.class));
//                            }
//                            adapter.notifyDataSetChanged();
//                            if (progressDialog.isShowing())
//                                progressDialog.dismiss();
//                        }
//                    }
//                });


  //  @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.searchmenu,menu);
//
//        MenuItem item = menu.findItem(R.id.search);
//
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                processsearch(s);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                processsearch(s);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    private void processsearch(String s){
//        FirebaseRecyclerOptions<User> options =
//                new FirebaseRecyclerOptions.Builder
//    }
}
