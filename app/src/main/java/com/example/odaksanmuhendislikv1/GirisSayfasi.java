package com.example.odaksanmuhendislikv1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;

public class GirisSayfasi extends AppCompatActivity {





    EditText emailTxt,sifreTxt;
    Button girisButon;
    TextView altText;
    FirebaseAuth firebaseAuth;
    ProgressDialog pd;
//
    //

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_sayfasi);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Giriş Sağlama");

        final FirebaseStorage storage = FirebaseStorage.getInstance();

        pd=new ProgressDialog(this);



        firebaseAuth = FirebaseAuth.getInstance();
        emailTxt = findViewById(R.id.emailTxt);
        sifreTxt = findViewById(R.id.sifreTxt);
        girisButon = findViewById(R.id.girisButon);
        altText = findViewById(R.id.altTxt);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {

            FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();


                if (firebaseUser!=null )
                {
                    String email = emailTxt.getText().toString();
                    // String character = "s@user.com";

                    if (email.endsWith("odaksan.user.com")){

                       Toast.makeText(GirisSayfasi.this, "Kullanıcı Girişi sağlandı!", Toast.LENGTH_SHORT).show();


                       Intent i = new Intent(GirisSayfasi.this, KullaniciAnaSayfa.class);
                       startActivity(i);


                   }else if(email.endsWith("odaksan.act.com")){

                        Toast.makeText(GirisSayfasi.this, " Muhasebe Girişi sağlandı!", Toast.LENGTH_SHORT).show();


                        Intent i = new Intent(GirisSayfasi.this, MuhasebeAnaSayfa.class);
                        startActivity(i);

                }else if (firebaseUser!=null){

                       Toast.makeText(GirisSayfasi.this, "Yönetici Girişi sağlandı!", Toast.LENGTH_SHORT).show();


                       Intent i = new Intent(GirisSayfasi.this, AnaSayfa.class);
                       startActivity(i);
                       showNotification();

                   }

                } else{
                    Toast.makeText(GirisSayfasi.this, "Giriş sağlanamadı! Lütfen giriş yapınız.", Toast.LENGTH_SHORT).show();

                }

            }
        };






        girisButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                pd.setTitle("Giriş Sağlanıyor...");
                pd.show();



                String email = emailTxt.getText().toString();
                String sifre = sifreTxt.getText().toString();





                if (email.isEmpty()) {
                    emailTxt.setError("Lütfen girdiğiniz email değerini kontrol ediniz.");
                    emailTxt.requestFocus();
                }
                else if (sifre.isEmpty()) {
                    sifreTxt.setError("Lütfen girdiğiniz şifre değerini kontrol ediniz.");
                    sifreTxt.requestFocus();
                }
                else if (email.isEmpty() && sifre.isEmpty()) {
                    Toast.makeText(GirisSayfasi.this, "Lütfen girdiğiniz değerleri kontrol ediniz.", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && sifre.isEmpty()) ) {
                   firebaseAuth.signInWithEmailAndPassword(email,sifre).addOnCompleteListener(GirisSayfasi.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (!task.isSuccessful())
                           {


                               Toast.makeText(GirisSayfasi.this, "Giriş hatası gerçekleşti.", Toast.LENGTH_SHORT).show();

                           }
                           else if (!task.isSuccessful())
                           {
                               Intent anasayfayagit=new Intent(GirisSayfasi.this,AnaSayfa.class);
                               startActivity(anasayfayagit);
                                showNotification();

                           }

                           else if(!task.isSuccessful())
                           {


                           }

                       }
                   });
                } else {
                    Toast.makeText(GirisSayfasi.this, "Hata oluştu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*altText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent girisyap=new Intent(GirisSayfasi.this,AnaSayfa.class);
                startActivity(girisyap);
            }
        });*/


    }



    private void showNotification() {



        int NOTIFICATION_ID = 234;
        NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);

            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), "my_channel_01")
                .setSmallIcon(R.mipmap.ic_launcher)


                .setContentTitle("Odaksan Mühendislik Bilgi Sistemi").setColor(Color.GREEN)
                .setContentText("Değerli kullancımız hoş geldiniz.").setColor(Color.RED);

        Intent resultIntent = new Intent(getBaseContext(), GirisSayfasi.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getBaseContext());
        stackBuilder.addParentStack(GirisSayfasi.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }




    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthStateListener);
    }


}


