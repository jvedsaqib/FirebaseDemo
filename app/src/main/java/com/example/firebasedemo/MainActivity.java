package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText addName, addEmail;
    Button btn;

    FirebaseDatabase db;    // Firebase Database
    DatabaseReference dbRef; // Database Reference

    Person ob;  // Object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addName = findViewById(R.id.addName);
        addEmail = findViewById(R.id.addEmail);

        btn = findViewById(R.id.btn);

        db = FirebaseDatabase.getInstance();

        dbRef = db.getInstance().getReference();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addName.getText().toString();
                String email = addEmail.getText().toString();

                Log.d("Name&Email", name+" "+email);
                addPerson(name, email);
            }
        });
    }

    public void addPerson(String name, String email){
        Log.d("Name&Email", name+"&"+email);
        ob = new Person();
        ob.setName(name);
        ob.setEmail(email);
        dbRef.child("Person2").push().setValue(ob);
//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("Name&Email Added", ob.getName()+" "+ob.getEmail());
//                Toast.makeText(MainActivity.this, "Done" ,Toast.LENGTH_LONG).show();
//                dbRef.child("Person").setValue(ob);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.d("Cancel", "Cancelled");
//            }
//        });
    }
}