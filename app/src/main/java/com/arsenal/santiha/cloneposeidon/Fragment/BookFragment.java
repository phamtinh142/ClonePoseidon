package com.arsenal.santiha.cloneposeidon.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.arsenal.santiha.cloneposeidon.Activity.MainActivity;
import com.arsenal.santiha.cloneposeidon.Adapter.BookAdapter;
import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.DatBan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookFragment extends Fragment {
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    ListView lsvBook;
    ArrayList<DatBan> datBanArrayList;
    BookAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Đang tải dữ liệu....");
        progressDialog.show();
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        lsvBook = view.findViewById(R.id.lsvBook);
        datBanArrayList = new ArrayList<>();
        adapter = new BookAdapter(getContext(), R.layout.row_book, datBanArrayList);
        lsvBook.setAdapter(adapter);

        getDataBook();
        return view;
    }



    private void getDataBook() {

        databaseReference.child("dat_ban").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    DatBan datBan = dataSnapshot.getValue(DatBan.class);
                    if (datBan.getUid().equals(MainActivity.Uid)){
                        datBanArrayList.add(datBan);
                    }
                    progressDialog.dismiss();
                    adapter.notifyDataSetChanged();
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
