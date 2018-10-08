package com.arsenal.santiha.cloneposeidon.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.MonDacSac;
import com.bumptech.glide.Glide;

import java.util.List;

public class RcyTrangChuAdapter extends RecyclerView.Adapter<RcyTrangChuAdapter.ItemHolder> {
    Context context;
    List<MonDacSac> monDacSacList;

    public RcyTrangChuAdapter(Context context, List<MonDacSac> monDacSacList) {
        this.context = context;
        this.monDacSacList = monDacSacList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_mon_dac_sac, null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        MonDacSac dacSac = monDacSacList.get(position);
        holder.txtTen.setText(dacSac.getTen());
        Glide.with(context).load(dacSac.getHinh()).into(holder.imgHinh);
    }

    @Override
    public int getItemCount() {
        return monDacSacList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        ImageView imgHinh;
        TextView txtTen;

        public ItemHolder(View item) {
            super(item);
            imgHinh = item.findViewById(R.id.img_mon_dac_sac);
            txtTen = item.findViewById(R.id.txt_ten_mon_dac_sac);
        }
    }
}
