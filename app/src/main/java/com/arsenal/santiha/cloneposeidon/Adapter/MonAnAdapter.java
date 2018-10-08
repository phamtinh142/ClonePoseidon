package com.arsenal.santiha.cloneposeidon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.DoiDiem;
import com.bumptech.glide.Glide;

import java.util.List;

public class MonAnAdapter extends BaseAdapter {

    Context myContext;
    int myLayout;
    List<DoiDiem> arrayMonAn;

    public MonAnAdapter(Context Context, int Layout, List<DoiDiem> arrayMonAn) {
        this.myContext = Context;
        this.myLayout = Layout;
        this.arrayMonAn = arrayMonAn;
    }

    @Override
    public int getCount() {
        return arrayMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout,null);

        TextView txtTen =  convertView.findViewById(R.id.textViewTen);
        txtTen.setText(arrayMonAn.get(position).getTen_mon());

        TextView txtGia =  convertView.findViewById(R.id.textViewGia);
        txtGia.setText(arrayMonAn.get(position).getSo_diem() + "");

        ImageView imgHinh =  convertView.findViewById(R.id.imageViewHinh);
        Glide.with(myContext).load(arrayMonAn.get(position).getHinh_anh()).into(imgHinh);

        return convertView;
    }
}
