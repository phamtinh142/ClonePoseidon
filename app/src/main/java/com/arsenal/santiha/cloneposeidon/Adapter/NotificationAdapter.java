package com.arsenal.santiha.cloneposeidon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.ThongBao;

import java.util.List;

public class NotificationAdapter extends BaseAdapter {

    Context context;
    List<ThongBao> thongBaoList;

    public NotificationAdapter(Context context, List<ThongBao> thongBaoList) {
        this.context = context;
        this.thongBaoList = thongBaoList;
    }

    @Override
    public int getCount() {
        return thongBaoList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dong_thong_bao, null);
        TextView txt_noi_dung = view.findViewById(R.id.txt_thong_bao_noi_dung);
        TextView txt_ngay = view.findViewById(R.id.txt_thong_bao_ngay);
        TextView txt_gio = view.findViewById(R.id.txt_thong_bao_gio);

        ThongBao thongBao = thongBaoList.get(i);
        txt_noi_dung.setText(thongBao.getNoi_dung());
        txt_ngay.setText(thongBao.getNgay());
        txt_gio.setText(thongBao.getGio());

        return view;
    }
}
