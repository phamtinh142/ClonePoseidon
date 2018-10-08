package com.arsenal.santiha.cloneposeidon.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.DatBan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookActivity extends AppCompatActivity {
    private EditText edtName, edtPhone, edtNote, edtNumberPeople;
    private Button btnDate, btnTime, btnBook, btnAgree, btnCancel;
    private TextView txtName, txtPhone, txtNumberPeople, txtDate, txtTime, txtNote;
    ProgressDialog progressDialog;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        mapper();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();

            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime();
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = edtName.getText().toString().trim();
                final String phone = edtPhone.getText().toString().trim();
                final String date = btnDate.getText().toString().trim();
                final String time = btnTime.getText().toString().trim();
                final String numberpeople = edtNumberPeople.getText().toString().trim();
                final String note = edtNote.getText().toString().trim();

                if(note.equals("")){
                    Toast.makeText(BookActivity.this, "Vui lòng nhập ghi chú !", Toast.LENGTH_SHORT).show();
                    edtNote.requestFocus();
                } else if(phone.equals("")){
                    Toast.makeText(BookActivity.this, "Vui lòng nhập số điện thoại !", Toast.LENGTH_SHORT).show();
                    edtPhone.requestFocus();
                } else if(numberpeople.equals("")){
                    Toast.makeText(BookActivity.this, "Vui lòng nhập số người !", Toast.LENGTH_SHORT).show();
                    edtNumberPeople.requestFocus();
                } else if(date.equals("")){
                    Toast.makeText(BookActivity.this, "Vui lòng chọn ngày !", Toast.LENGTH_SHORT).show();

                    setDate();
                } else if(time.equals("")){
                    Toast.makeText(BookActivity.this, "Vui lòng chọn giờ !", Toast.LENGTH_SHORT).show();

                    setTime();
                } else if(name.equals("")){
                    Toast.makeText(BookActivity.this, "Vui lòng nhập họ tên !", Toast.LENGTH_SHORT).show();
                    edtName.requestFocus();
                } else {
                    final Dialog dialog = new Dialog(BookActivity.this);
                    dialog.setTitle("Bạn muốn đặt bàn chứ ?");
                    dialog.setContentView(R.layout.custom_dialog_book);
                    dialog.show();

                    // ánh xạ
                    txtName = dialog.findViewById(R.id.txtNameBook);
                    txtPhone = dialog.findViewById(R.id.txtPhoneBook);
                    txtNumberPeople = dialog.findViewById(R.id.txtNumberPeople);
                    txtDate = dialog.findViewById(R.id.txtDateBook);
                    txtTime = dialog.findViewById(R.id.txtTimeBook);
                    txtNote = dialog.findViewById(R.id.txtNoteBook);
                    btnAgree = dialog.findViewById(R.id.btnAgreeBook);
                    btnCancel = dialog.findViewById(R.id.btnCancel);

                    txtName.setText(name);
                    txtPhone.setText(phone);
                    txtNumberPeople.setText(numberpeople);
                    txtDate.setText(date);
                    txtTime.setText(time);
                    txtNote.setText(note);

                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });

                    btnAgree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                            Date myDate = new Date();
                            String key = timeStampFormat.format(myDate);
                            dialog.cancel();
                            progressDialog = new ProgressDialog(BookActivity.this);
                            progressDialog.setMessage("Đang đặt bàn...");
                            progressDialog.show();
                            DatBan datBan = new DatBan(key,MainActivity.Uid, name, Integer.parseInt(phone), Integer.parseInt(numberpeople), date, time, note);
                            databaseReference.child("dat_ban").child(key).setValue(datBan, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                    if(databaseError == null){
                                        progressDialog.dismiss();
                                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("diem_tich").child(MainActivity.Uid);
                                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                long diem = dataSnapshot.getValue(Long.TYPE) + Long.parseLong(numberpeople);
                                                reference.setValue(diem);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                        Toast.makeText(BookActivity.this, "Đặt bàn thành công !", Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(BookActivity.this, "Đặt bàn thất bại !", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    });

                }
            }
        });
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
                btnTime.setText(simpleDateFormat.format(calendar.getTime()));
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
                btnDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();

    }

    private void mapper() {
        edtName = findViewById(R.id.edtNameBook);
        edtPhone = findViewById(R.id.edtPhoneBook);
        edtNote = findViewById(R.id.edtNoteBook);
        btnDate = findViewById(R.id.btnDateBook);
        btnTime = findViewById(R.id.btnTimeBook);
        btnBook = findViewById(R.id.btnBook);
        edtNumberPeople = findViewById(R.id.edtNumberPeopleBook);
    }
}
