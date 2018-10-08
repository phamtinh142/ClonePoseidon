package com.arsenal.santiha.cloneposeidon.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DangNhapActivity extends AppCompatActivity {

    Button btlogin;
    FirebaseAuth firebaseAuth;
    EditText txtemail;
    EditText txtpw;
    TextView dangkytk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        firebaseAuth = FirebaseAuth.getInstance();

        btlogin = findViewById(R.id.btlogin);

        txtemail = findViewById(R.id.txtemail);
        txtpw = findViewById(R.id.txtpw);
        dangkytk = findViewById(R.id.dangkytk);

        dangkytk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dangnhap(txtemail.getText().toString(), txtpw.getText().toString());
            }
        });
    }

    public void Dangnhap(String email, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(DangNhapActivity.this);
        progressDialog.setMessage("Đăng nhập....");
        if(email.equals("")){
            Toast.makeText(DangNhapActivity.this, "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
            txtemail.requestFocus();
        } else if(password.equals("")){
            Toast.makeText(DangNhapActivity.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            txtpw.requestFocus();
        } else {
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        progressDialog.dismiss();
                        startActivity(intent);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(DangNhapActivity.this, "Sai tài khoản hoặc mật khẩu !", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
