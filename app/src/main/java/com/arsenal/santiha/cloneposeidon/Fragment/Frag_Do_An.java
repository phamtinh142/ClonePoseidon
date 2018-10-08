package com.arsenal.santiha.cloneposeidon.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.arsenal.santiha.cloneposeidon.Activity.DangKyActivity;
import com.arsenal.santiha.cloneposeidon.Adapter.DoAnAdapter;
import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.DoAn;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Frag_Do_An extends Fragment {

    ListView lvw_thuc_don;
    DoAnAdapter doAnAdapter;
    List<DoAn> doAnList, timkiemList;
    DatabaseReference databaseThucDon;
    EditText edt_tim_kiem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_do_an, container, false);

        lvw_thuc_don = view.findViewById(R.id.lvw_thuc_don_do_an);
        doAnList = new ArrayList<>();
        timkiemList = new ArrayList<>();
        doAnAdapter = new DoAnAdapter(getActivity(), doAnList);
        lvw_thuc_don.setAdapter(doAnAdapter);
        edt_tim_kiem = view.findViewById(R.id.edt_tim_kiem);

        TimKiem();
        getThucDon();

        return view;
    }

    private void TimKiem() {
        edt_tim_kiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                s.toString().toLowerCase();
                timkiemList.clear();
                Log.d("timkiem", s.toString());
                for (int i=0; i<doAnList.size(); i++){
                    if (doAnList.get(i).getTen_mon().toLowerCase().contains(s.toString())){
                        timkiemList.add(doAnList.get(i));
                    }
                }
                doAnAdapter = new DoAnAdapter(getActivity(), timkiemList);
                lvw_thuc_don.setAdapter(doAnAdapter);
                if (s.toString().equals("")){
                    doAnAdapter = new DoAnAdapter(getActivity(), doAnList);
                    lvw_thuc_don.setAdapter(doAnAdapter);
                }
            }
        });
    }

    private void getThucDon() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Đang tải dữ liệu....");
        progressDialog.show();
        databaseThucDon = FirebaseDatabase.getInstance().getReference().child("thucdon").child("doan");
        databaseThucDon.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DoAn doAn = dataSnapshot.getValue(DoAn.class);
                doAnList.add(doAn);
                doAnAdapter.notifyDataSetChanged();
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
