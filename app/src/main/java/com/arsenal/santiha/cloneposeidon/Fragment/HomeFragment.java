package com.arsenal.santiha.cloneposeidon.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.arsenal.santiha.cloneposeidon.Activity.BookActivity;
import com.arsenal.santiha.cloneposeidon.Activity.GioiThieuActivity;
import com.arsenal.santiha.cloneposeidon.Activity.GoogleMapActivity;
import com.arsenal.santiha.cloneposeidon.Activity.ThongTinUserActivity;
import com.arsenal.santiha.cloneposeidon.Activity.ThucDonActivity;
import com.arsenal.santiha.cloneposeidon.Adapter.RcyTrangChuAdapter;
import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.Danhgia;
import com.arsenal.santiha.cloneposeidon.model.MonDacSac;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ViewFlipper viewFlipperTrangChinh;
    ImageButton ibtn_menu, ibtn_dat_ban, ibtn_map, ibtn_gioi_thieu;
    private View view;
    DatabaseReference reference;
    TextView txt_dien_danh_gia;
    double diem = 0, kq = 0;
    int i = 0;
    RecyclerView recyclerDacSac;
    RcyTrangChuAdapter rcyTrangChuAdapter;
    List<MonDacSac> monDacSacList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        AnhXa();
        ActionViewFlipper();
        getDiemDanhGia();

        ibtn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ThucDonActivity.class));
            }
        });
        ibtn_dat_ban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    final String idusername = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Log.d("ktid", idusername);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("thong_tin_user").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child(idusername).exists()){

                                startActivity(new Intent(getActivity(), BookActivity.class));
                            } else {
                                Toast.makeText(getActivity(), "Bạn cần cung cấp thông tin cá nhân !", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getActivity(), ThongTinUserActivity.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }else {
                    Toast.makeText(getActivity(), "Vui lòng đăng nhập.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ibtn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GoogleMapActivity.class));
            }
        });
        ibtn_gioi_thieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GioiThieuActivity.class));
            }
        });

        return view;
    }

    private void getDiemDanhGia() {
        reference = FirebaseDatabase.getInstance().getReference().child("danh_gia");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                i++;
                Danhgia danhgia = dataSnapshot.getValue(Danhgia.class);
                diem += danhgia.getSo_diem();
                kq = diem/i;
                Log.d("kiemtra", kq + "");
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                txt_dien_danh_gia.setText(decimalFormat.format(kq) + "");
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

    private void AnhXa() {
        viewFlipperTrangChinh = view.findViewById(R.id.viewflipperTrangChinh);
        ibtn_menu = view.findViewById(R.id.ibtn_menu);
        ibtn_dat_ban = view.findViewById(R.id.ibtn_dat_ban);
        ibtn_map = view.findViewById(R.id.ibtn_google_map);
        ibtn_gioi_thieu = view.findViewById(R.id.ibtn_gioi_thieu);
        txt_dien_danh_gia =view.findViewById(R.id.txt_diem_danh_gia);
        recyclerDacSac = view.findViewById(R.id.ryc_mon_an_dac_sac);
        monDacSacList = new ArrayList<>();
        rcyTrangChuAdapter = new RcyTrangChuAdapter(getActivity(), monDacSacList);
        monDacSacList.add(new MonDacSac("Ghẹ bơi hấp", "https://buffetposeidon.com/r/files/images/tin-tuc/LQHIMG_6395.jpg"));
        monDacSacList.add(new MonDacSac("Sò dương nướng mỡ hành", "https://buffetposeidon.com/r/files/images/thuvien/b.jpg"));
        monDacSacList.add(new MonDacSac("Bề bề đang bơi", "https://buffetposeidon.com/r/files/images/thuvien/a.jpg"));
        monDacSacList.add(new MonDacSac("Ốc gai nướng", "https://buffetposeidon.com/r/files/images/thuvien/IMG_3448.JPG"));
        monDacSacList.add(new MonDacSac("Sò chén nướng mỡ hành", "https://buffetposeidon.com/r/files/images/thuvien/c.jpg"));
        monDacSacList.add(new MonDacSac("Ba chỉ bò Mỹ Nướng Bulgogi", "https://buffetposeidon.com/r/files/images/thuvien/d.jpg"));
        monDacSacList.add(new MonDacSac("Cá hồi Sashimi", "https://buffetposeidon.com/r/files/images/tin-tuc/LQHIMG_6390.jpg"));

        recyclerDacSac.setHasFixedSize(true);
        recyclerDacSac.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerDacSac.setAdapter(rcyTrangChuAdapter);
        recyclerDacSac.setNestedScrollingEnabled(true);

    }

    private void ActionViewFlipper() {
        ArrayList<String> arrFlipper = new ArrayList<>();
        arrFlipper.add("https://buffetposeidon.com/r/files/slide_home/bannerthang9.png");
        arrFlipper.add("https://buffetposeidon.com/r/files/slide_home/2017-06-02-2.jpg");
        arrFlipper.add("https://cdn.pasgo.vn/Upload/anh-chi-tiet/nha-hang-buffet-poseidon-le-van-luong-slide-1up-125244911552.jpg");
        arrFlipper.add("https://cdn.pasgo.vn/Upload/anh-chi-tiet/nha-hang-buffet-poseidon-le-van-luong-slide-2up-125245011553.jpg");
        arrFlipper.add("https://cdn.pasgo.vn/Upload/anh-chi-tiet/nha-hang-buffet-poseidon-le-van-luong-slide-3up-125245111554.jpg");

        for (int i=0; i<arrFlipper.size(); i++){
            ImageView imageView = new ImageView(getActivity());
            Glide.with(getActivity()).load(arrFlipper.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperTrangChinh.addView(imageView);
        }

        viewFlipperTrangChinh.setFlipInterval(3000);
        viewFlipperTrangChinh.setAutoStart(true);

        Animation animation_slide_in = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out);
        viewFlipperTrangChinh.setInAnimation(animation_slide_in);
        viewFlipperTrangChinh.setOutAnimation(animation_slide_out);
    }


}
