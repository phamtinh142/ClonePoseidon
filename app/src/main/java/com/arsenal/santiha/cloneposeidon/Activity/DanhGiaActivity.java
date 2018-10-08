package com.arsenal.santiha.cloneposeidon.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.Danhgia;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

public class DanhGiaActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button btdanhgia;
    EditText txttieude, txtnoidung;
    TextView txtRateDisplay;
    SmileRating smileRating;
    String uid, email;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);

        btdanhgia = findViewById(R.id.btdanhgia);
        txttieude = findViewById(R.id.txttieude);
        txtnoidung = findViewById(R.id.txtnoidung);
        txtRateDisplay = findViewById(R.id.txtRateDisplay);
        smileRating = findViewById(R.id.smile_rating);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("danh_gia");
        toolbar = findViewById(R.id.toolbar_phan_hoi);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        email = intent.getStringExtra("email");

        btdanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieude = txttieude.getText().toString();
                String noidung = txtnoidung.getText().toString();
                long rate = (long) smileRating.getRating();

                Danhgia danhgia = new Danhgia();
                danhgia.setNoi_dung(noidung);
                danhgia.setSo_diem(rate);
                danhgia.setTieu_de(tieude);

                databaseReference.child(uid).setValue(danhgia);

                finish();

            }
        });



    }
}
