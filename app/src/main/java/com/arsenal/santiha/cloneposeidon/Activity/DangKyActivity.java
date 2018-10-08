package com.arsenal.santiha.cloneposeidon.Activity;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arsenal.santiha.cloneposeidon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DangKyActivity extends AppCompatActivity {

    Button btdangky;
    FirebaseAuth firebaseAuth;
    EditText txtemail;
    EditText txtpw;
    EditText txtrepw;
    TextView btback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        firebaseAuth = FirebaseAuth.getInstance();

        btdangky = findViewById(R.id.btdangky);
        txtemail = findViewById(R.id.txtemail);
        txtpw = findViewById(R.id.txtpw);
        txtrepw = findViewById(R.id.txtrepw);
        btback = findViewById(R.id.btback);

        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtemail.getText().toString();
                String pw = txtpw.getText().toString();
                String repw = txtrepw.getText().toString();

                if(email.trim().length() == 0) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng nhập email !", Toast.LENGTH_SHORT).show();
                    txtemail.requestFocus();
                } else if(pw.trim().length() == 0) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng nhập mật khẩu !", Toast.LENGTH_SHORT).show();
                    txtpw.requestFocus();
                } else if (pw.trim().length() < 6){
                    Toast.makeText(DangKyActivity.this, R.string.dodaimatkhau, Toast.LENGTH_SHORT).show();
                    txtpw.requestFocus();
                } else if(!repw.equals(pw)) {
                    Toast.makeText(DangKyActivity.this, getString(R.string.thongbaorepw), Toast.LENGTH_SHORT).show();
                    txtrepw.requestFocus();
                } else {
                    final ProgressDialog progressDialog = new ProgressDialog(DangKyActivity.this);
                    progressDialog.setMessage("Đang đăng kí....");
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                progressDialog.dismiss();
                                FirebaseDatabase.getInstance().getReference().child("diem_tich").child(firebaseAuth.getUid()).setValue(0);
                                Toast.makeText(DangKyActivity.this, getString(R.string.thongbaodangkythanhcong), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(DangKyActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
