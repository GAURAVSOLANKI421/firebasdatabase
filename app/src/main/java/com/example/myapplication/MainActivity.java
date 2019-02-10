package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db= FirebaseDatabase.getInstance();
    DatabaseReference myref=db.getReference();
    DatabaseReference userref=myref.child("users");
    EditText name,password,email;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.pass);
        email=(EditText)findViewById(R.id.mail);
        bt=(Button)findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm=name.getText().toString().trim();
                String ps=password.getText().toString().trim();
                String em=email.getText().toString().trim();

                HashMap<String,String> uesrs=new HashMap<String, String>();
                uesrs.put("name",nm);
                uesrs.put("password",ps);
                uesrs.put("email",em);

                 userref.push().setValue(uesrs).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if (task.isSuccessful()) {
                             Toast.makeText(MainActivity.this,"sucess",Toast.LENGTH_SHORT).show();
                         }
                     }});
            }
        });

    }
}
