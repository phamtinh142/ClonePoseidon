package com.arsenal.santiha.cloneposeidon.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThongTinUserActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    Button btncapnhat, btndong;
    EditText edthoten, edtDayProfile, edtMonthProfile, edtYearProfile, edtSodienthoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_user);
        anhxa();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        final String idusername = FirebaseAuth.getInstance().getCurrentUser().getUid();






        btncapnhat.setOnClickListener(new View.OnClickListener() {
        @Override
         public void onClick(View v) {
            String hoten = edthoten.getText().toString();
            String ngaysinh = edtDayProfile.getText().toString();
            String thangsinh = edtMonthProfile.getText().toString();
            String namsinh = edtYearProfile.getText().toString();
            String sodienthoai = edtSodienthoai.getText().toString();



            if (hoten.equals("")){
                Toast.makeText(ThongTinUserActivity.this, "Nhập họ tên !", Toast.LENGTH_SHORT).show();
                edthoten.requestFocus();
            } else if(ngaysinh.equals("")){
                Toast.makeText(ThongTinUserActivity.this, "Nhập ngày sinh !", Toast.LENGTH_SHORT).show();
                edtDayProfile.requestFocus();
            } else if(thangsinh.equals("")){
                Toast.makeText(ThongTinUserActivity.this, "Nhập tháng sinh !", Toast.LENGTH_SHORT).show();
                edtMonthProfile.requestFocus();
            } else if(namsinh.equals("")){
                Toast.makeText(ThongTinUserActivity.this, "Nhập năm sinh !", Toast.LENGTH_SHORT).show();
                edtYearProfile.requestFocus();
            } else if(sodienthoai.equals("")){
                Toast.makeText(ThongTinUserActivity.this, "Nhập Số điện thoại !", Toast.LENGTH_SHORT).show();
                edtSodienthoai.requestFocus();
            } else {
                String date = ngaysinh + "-" + thangsinh + "-" + namsinh;

                User user = new User(hoten, date, sodienthoai);



                databaseReference.child("thong_tin_user").child(idusername).setValue(user);

                Toast.makeText(ThongTinUserActivity.this, "Cập nhật thông tin thành công !", Toast.LENGTH_LONG).show();
                finish();
            }
         }
        });

        btndong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhxa() {
        edthoten = findViewById(R.id.edthoten);
        edtDayProfile = findViewById(R.id.edtDayProfile);
        edtMonthProfile = findViewById(R.id.edtMonthProfile);
        edtYearProfile = findViewById(R.id.edtYearProfire);
        edtSodienthoai = findViewById(R.id.edtsodienthoai);
        btncapnhat = findViewById(R.id.btncapnhat);
        btndong = findViewById(R.id.btndong);
    }
}
