package com.arsenal.santiha.cloneposeidon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arsenal.santiha.cloneposeidon.Activity.ThucDonActivity;
import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.DoAn;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class DoAnAdapter extends BaseAdapter {

    Context context;
    List<DoAn> doAnList;

    public DoAnAdapter(Context context, List<DoAn> doAnList) {
        this.context = context;
        this.doAnList = doAnList;
    }

    @Override
    public int getCount() {
        return doAnList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView txt_ten;
        ImageView img_hinh;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_thuc_don, null);
            holder.txt_ten = convertView.findViewById(R.id.txt_ten_mon_an);
            holder.img_hinh = convertView.findViewById(R.id.img_mon_an);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DoAn doAn = doAnList.get(position);
        holder.txt_ten.setText(doAn.getTen_mon());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        Picasso.get().load(doAn.getHinh_anh()).into(holder.img_hinh);

        return convertView;
    }
}
