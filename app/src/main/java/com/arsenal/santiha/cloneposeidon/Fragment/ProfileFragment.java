package com.arsenal.santiha.cloneposeidon.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.arsenal.santiha.cloneposeidon.Activity.DangNhapActivity;
import com.arsenal.santiha.cloneposeidon.Activity.DanhGiaActivity;
import com.arsenal.santiha.cloneposeidon.Activity.HistoryActivity;
import com.arsenal.santiha.cloneposeidon.Activity.MainActivity;
import com.arsenal.santiha.cloneposeidon.Activity.ThongTinUserActivity;
import com.arsenal.santiha.cloneposeidon.Activity.XemDiemActivity;
import com.arsenal.santiha.cloneposeidon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {
    private View view;

//    Button btn_login_profile, btn_phan_hoi, btn_diem_tich_luy, btn_ho_tro, btn_dang_xuat, btn_thong_tin, btn_lich_su;
//    FirebaseUser user = null;
//
//    String email = "";
//    String uid = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

//        AnhXa();
//        getUserProfile();
//        DangNhap();
//        DangXuat();
//        PhanHoi();
//        ThongTinCaNhan();
//        HoTro();
//        XemLichSu();
//        XemDiem();

        return view;
    }


//    private void XemDiem() {
//        btn_diem_tich_luy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (user != null) {
//                startActivity(new Intent(getActivity(), XemDiemActivity.class));
//                } else {
//                    Toast.makeText(getActivity(), "Vui lòng đăng nhập.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void XemLichSu() {
//        btn_lich_su.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (user != null) {
//                    Intent intent = new Intent(getActivity(), HistoryActivity.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(getActivity(), "Vui lòng đăng nhập.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void HoTro() {
//        btn_ho_tro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog dialog = new Dialog(getActivity());
//                dialog.setCancelable(true);
//                View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_ho_tro, null);
//                dialog.setContentView(view);
//                TextView txt_so_1 = view.findViewById(R.id.txt_phan_hoi_1);
//                TextView txt_so_2 = view.findViewById(R.id.txt_phan_hoi_2);
//                txt_so_1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
//                        phoneIntent.setData(Uri.parse("tel:01683418972"));
//                        startActivity(phoneIntent);
//                    }
//                });
//                txt_so_2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
//                        phoneIntent.setData(Uri.parse("tel:0909900909"));
//                        startActivity(phoneIntent);
//                    }
//                });
//                dialog.show();
//            }
//        });
//    }
//
//    private void ThongTinCaNhan() {
//        btn_thong_tin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (user != null) {
//                Intent intent = new Intent(getActivity(), ThongTinUserActivity.class);
//                intent.putExtra("uid", uid);
//                startActivity(intent);
//                } else {
//                    Toast.makeText(getActivity(), "Vui lòng đăng nhập.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void PhanHoi() {
//        btn_phan_hoi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (user != null) {
//                    Intent intent = new Intent(getActivity(), DanhGiaActivity.class);
//                    intent.putExtra("uid", uid);
//                    intent.putExtra("email", email);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(getActivity(), "Vui lòng đăng nhập.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void DangXuat() {
//        btn_dang_xuat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btn_login_profile.setText(R.string.dangnhapdangkynhanuudai);
//                Toast.makeText(getActivity(), "Đăng xuất thành công !", Toast.LENGTH_SHORT).show();
//                FirebaseAuth.getInstance().signOut();
//
//                user = FirebaseAuth.getInstance().getCurrentUser();
//                if (user != null) {
//                    btn_login_profile.setText(R.string.dangnhapdangkynhanuudai);
//                    MainActivity.Uid = null;
//                }
//            }
//        });
//    }
//
//    private void DangNhap() {
//            btn_login_profile.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (user == null) {
//                        startActivity(new Intent(getActivity(), DangNhapActivity.class));
//                    }
//                }
//            });
//    }
//
//    private void getUserProfile() {
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            email = user.getEmail();
//            uid = user.getUid();
//            MainActivity.Uid = uid;
//            btn_login_profile.setText(email);
//        } else {
//            btn_login_profile.setText(R.string.dangnhapdangkynhanuudai);
//        }
//    }
//
//    private void AnhXa() {
//        btn_login_profile = view.findViewById(R.id.btn_login);
//        btn_dang_xuat = view.findViewById(R.id.btn_dang_xuat);
//        btn_phan_hoi =view.findViewById(R.id.btn_phan_hoi);
//        btn_thong_tin = view.findViewById(R.id.btn_thong_tin_ca_nhan);
//        btn_diem_tich_luy = view.findViewById(R.id.btn_diem_tich_luy);
//        btn_ho_tro = view.findViewById(R.id.btn_ho_tro);
//        btn_lich_su = view.findViewById(R.id.btn_lich_su_dat_ban);
//    }
}
