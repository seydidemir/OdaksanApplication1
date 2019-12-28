package com.example.odaksanmuhendislikv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText emailTxt,sifreTxt;
    Button kayıtButon;
    TextView altText;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        emailTxt = findViewById(R.id.emailTxt);
        sifreTxt = findViewById(R.id.sifreTxt);
        kayıtButon = findViewById(R.id.girisButon);
        altText = findViewById(R.id.altTxt);

        kayıtButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTxt.getText().toString();
                String sifre = sifreTxt.getText().toString();

                if (email.isEmpty()) {
                    emailTxt.setError("Lütfen girdiğiniz email değerini kontrol ediniz.");
                    emailTxt.requestFocus();
                } else if (sifre.isEmpty()) {
                    sifreTxt.setError("Lütfen girdiğiniz şifre değerini kontrol ediniz.");
                    sifreTxt.requestFocus();
                } else if (email.isEmpty() && sifre.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Lütfen girdiğiniz değerleri kontrol ediniz.", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && sifre.isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(email, sifre).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Kayıt başarılı değil!", Toast.LENGTH_SHORT).show();

                            } else {
                                startActivity(new Intent(MainActivity.this, AnaSayfa.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Hata oluştu!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        altText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, GirisSayfasi.class);
                startActivity(i);
            }
        });


    }
}
