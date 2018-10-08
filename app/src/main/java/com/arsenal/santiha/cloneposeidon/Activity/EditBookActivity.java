package com.arsenal.santiha.cloneposeidon.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.DatBan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EditBookActivity extends AppCompatActivity {
    EditText edtTitleEditBook, edtNameEditBook, edtPhoneEditBook, edtNumberPeopleEditBook;
    Button btnDateEditBook, btnTimeEditBook, btnEditBook;
    TextView txtExitEditBook;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        mapper();


        Intent intent = getIntent();
        final String key = intent.getStringExtra("key");


        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DatBan datBan = dataSnapshot.child("dat_ban").child(key).getValue(DatBan.class);
                edtTitleEditBook.setText(datBan.getGhi_chu());
                edtNameEditBook.setText(datBan.getTen());
                edtPhoneEditBook.setText(String.valueOf(datBan.getSo_dien_thoai()));
                edtNumberPeopleEditBook.setText(String.valueOf(datBan.getSo_nguoi()));
                btnDateEditBook.setText(datBan.getNgay_dat_hang());
                btnTimeEditBook.setText(datBan.getGio_den());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnDateEditBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();

            }
        });

        btnTimeEditBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime();
            }
        });


        btnEditBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitleEditBook.getText().toString().trim();
                String name = edtNameEditBook.getText().toString().trim();
                String phone = edtPhoneEditBook.getText().toString().trim();
                String number = edtNumberPeopleEditBook.getText().toString().trim();
                String date = btnDateEditBook.getText().toString().trim();
                String time = btnTimeEditBook.getText().toString().trim();


                if(title.equals("")){
                    Toast.makeText(EditBookActivity.this, "Vui lòng nhập ghi chú !", Toast.LENGTH_SHORT).show();
                    edtTitleEditBook.requestFocus();
                } else if(name.equals("")){
                    Toast.makeText(EditBookActivity.this, "Vui lòng nhập họ tên !", Toast.LENGTH_SHORT).show();
                    edtNameEditBook.requestFocus();
                } else if(phone.equals("")){
                    Toast.makeText(EditBookActivity.this, "Vui lòng nhập số điện thoại !", Toast.LENGTH_SHORT).show();
                    edtPhoneEditBook.requestFocus();
                } else if(number.equals("")){
                    Toast.makeText(EditBookActivity.this, "Vui lòng nhập số người !", Toast.LENGTH_SHORT).show();
                    edtNumberPeopleEditBook.requestFocus();
                } else {
                    DatBan datBan = new DatBan(key,MainActivity.Uid, name, Integer.parseInt(phone), Integer.parseInt(number), date, time, title);
                    databaseReference.child("dat_ban").child(key).setValue(datBan, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if(databaseError == null){
                                Toast.makeText(EditBookActivity.this, "Chỉnh sửa thành công !", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(EditBookActivity.this, "Chỉnh sửa thất bại !", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }


            }
        });

        txtExitEditBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void mapper() {
        edtTitleEditBook = findViewById(R.id.edtTitleEditBook);
        edtNameEditBook = findViewById(R.id.edtNameEditBook);
        edtPhoneEditBook = findViewById(R.id.edtPhoneEditBook);
        edtNumberPeopleEditBook = findViewById(R.id.edtNumberPeopleEditBook);
        btnDateEditBook = findViewById(R.id.btnDateEditBook);
        btnTimeEditBook = findViewById(R.id.btnTimeEditBook);
        btnEditBook = findViewById(R.id.btnEditBook);
        txtExitEditBook = findViewById(R.id.txtExitEditBook);
    }


    private void setTime() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                calendar.set(0, 0, 0, i, i1);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                btnTimeEditBook.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private void setDate() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                btnDateEditBook.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();

    }
}
