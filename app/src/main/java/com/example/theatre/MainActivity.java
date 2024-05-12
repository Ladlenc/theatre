package com.example.theatre;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    private static final  String LOG_TAG = MainActivity.class.getName();
    private FirebaseUser user;
    private RecyclerView recyclerView;
    private ArrayList<Concert> concertList;
    private ConcertAdapter cadapter;

    private FirebaseFirestore firestore;
    private CollectionReference concerts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = FirebaseAuth.getInstance().getCurrentUser();
        mauth= FirebaseAuth.getInstance();



        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        concertList = new ArrayList<>();

        cadapter = new ConcertAdapter(this, concertList);
        recyclerView.setAdapter(cadapter);

        firestore= FirebaseFirestore.getInstance();
        concerts = firestore.collection("Concerts");


        queryData();



    }

    private void queryData(){

        concerts.get().addOnSuccessListener(queryDocumentSnapshots -> {
           for(QueryDocumentSnapshot document : queryDocumentSnapshots){
               Concert concert = document.toObject(Concert.class);
               concert.setId(document.getId());
               concertList.add(concert);
           }

           cadapter.notifyDataSetChanged();
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (user == null){
            inflater.inflate(R.menu.menu_main, menu);
        }else{
            inflater.inflate(R.menu.logedin_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.register_menuitem) {
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);

        } else if (id == R.id.login_menuitem) {
            Intent intent1 = new Intent(this, LoginActivity.class);
            startActivity(intent1);

        }
            return super.onOptionsItemSelected(item);

    }





    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}