package com.arsenal.santiha.cloneposeidon.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.arsenal.santiha.cloneposeidon.Activity.MainActivity;
import com.arsenal.santiha.cloneposeidon.Adapter.NotificationAdapter;
import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.ThongBao;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    ListView listView;
    NotificationAdapter adapter;
    List<ThongBao> thongBaoList;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        listView = view.findViewById(R.id.lsvNotification);
        thongBaoList = new ArrayList<>();
        adapter = new NotificationAdapter(getActivity(), thongBaoList);
        listView.setAdapter(adapter);
        reference = FirebaseDatabase.getInstance().getReference();

            reference.child("thong_bao").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    ThongBao thongBao = dataSnapshot.getValue(ThongBao.class);
                    if (thongBao.getUid().equals(MainActivity.Uid)){
                        thongBaoList.add(thongBao);
                        adapter.notifyDataSetChanged();
                    }
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




        return view;
    }
}
