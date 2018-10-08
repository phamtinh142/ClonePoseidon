package com.arsenal.santiha.cloneposeidon.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.arsenal.santiha.cloneposeidon.Adapter.DoAnAdapter;
import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.DoAn;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DrinkFragment extends Fragment {

    ListView lvw_thuc_don;
    DoAnAdapter doAnAdapter;
    List<DoAn> doUongList;
    DatabaseReference databaseThucDon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_do_uong, container, false);

        lvw_thuc_don = view.findViewById(R.id.lvw_thuc_don_do_uong);
        doUongList = new ArrayList<>();
        doAnAdapter = new DoAnAdapter(getActivity(), doUongList);
        lvw_thuc_don.setAdapter(doAnAdapter);

        getThucDon();

        return view;
    }

    private void getThucDon() {
        databaseThucDon = FirebaseDatabase.getInstance().getReference().child("thucdon").child("douong");
        databaseThucDon.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DoAn doAn = dataSnapshot.getValue(DoAn.class);
                doUongList.add(doAn);
                doAnAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                doAnAdapter.notifyDataSetChanged();
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
