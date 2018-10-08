package com.arsenal.santiha.cloneposeidon.Activity;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.arsenal.santiha.cloneposeidon.Adapter.MonAnAdapter;
import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.DoiDiem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class XemDiemActivity extends AppCompatActivity {

    ListView lvMonAn;
    ArrayList<DoiDiem> mangMonAn;
    DatabaseReference reference;
    TextView txt_point;
    MonAnAdapter monAnAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_diem);

        toolbar = findViewById(R.id.toolbar_diem_tich);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_point = findViewById(R.id.txtNumberPoint);
        lvMonAn = findViewById(R.id.listViewMonAn);
        mangMonAn = new ArrayList<>();
        monAnAdapter = new MonAnAdapter(
                this,
                R.layout.dong_mon_an,
                mangMonAn

        );
        lvMonAn.setAdapter(monAnAdapter);
        lvMonAn.setFooterDividersEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang tải ...");
        progressDialog.show();
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("diem_tich").child(MainActivity.Uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long diem = dataSnapshot.getValue(Long.TYPE);
                txt_point.setText(diem + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference.child("danh_sach_doi_diem").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DoiDiem diem = dataSnapshot.getValue(DoiDiem.class);
                mangMonAn.add(diem);
                monAnAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
