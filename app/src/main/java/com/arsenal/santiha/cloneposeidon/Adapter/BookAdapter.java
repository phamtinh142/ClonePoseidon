package com.arsenal.santiha.cloneposeidon.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arsenal.santiha.cloneposeidon.Activity.EditBookActivity;
import com.arsenal.santiha.cloneposeidon.Activity.MainActivity;
import com.arsenal.santiha.cloneposeidon.R;
import com.arsenal.santiha.cloneposeidon.model.DatBan;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    private DatabaseReference databaseReference;
    private Context context;
    private int layout;
    private List<DatBan> datBanList;

    public BookAdapter(Context context, int layout, List<DatBan> datBanList) {
        this.context = context;
        this.layout = layout;
        this.datBanList = datBanList;
    }

    @Override
    public int getCount() {
        return datBanList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    private class ViewHolder{
        TextView txtTitleRowBook, txtNameRowBook, txtNumberPeopleRowBook, txtTimeRowBook;
        Button btnEditRowBook, btnCancelRowBook;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txtTitleRowBook = view.findViewById(R.id.txtTitleRowBook);
            holder.txtNameRowBook = view.findViewById(R.id.txtNameRowBook);
            holder.txtNumberPeopleRowBook = view.findViewById(R.id.txtNumberPeopleRowBook);
            holder.txtTimeRowBook = view.findViewById(R.id.txtTimeRowBook);
            holder.btnEditRowBook = view.findViewById(R.id.btnEditRowBook);
            holder.btnCancelRowBook = view.findViewById(R.id.btnCancelRowBook);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final DatBan datBan = datBanList.get(i);

        holder.txtTitleRowBook.setText(datBan.getGhi_chu());
        holder.txtNameRowBook.setText(datBan.getTen());
        holder.txtNumberPeopleRowBook.setText(String.valueOf(datBan.getSo_nguoi()));
        holder.txtTimeRowBook.setText(datBan.getGio_den() + " " + datBan.getNgay_dat_hang());

        holder.btnCancelRowBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setTitle("Bạn muốn xóa chứ ?");
                dialog.setContentView(R.layout.custom_dialog_delete_book);
                dialog.show();

                TextView txtTitleDeleteBook = dialog.findViewById(R.id.txtTitleDeleteBook);
                TextView txtNameDeleteBook = dialog.findViewById(R.id.txtNameDeleteBook);
                TextView txtNumberPeopleDeleteBook = dialog.findViewById(R.id.txtNumberPeopleDeleteBook);
                TextView txtDateDeleteBook = dialog.findViewById(R.id.txtDateDeleteBook);
                Button btnAgreeDeleteBook = dialog.findViewById(R.id.btnAgreeDeleteBook);
                Button btnCancelDeleteBook = dialog.findViewById(R.id.btnCancelDeleteBook);

                txtTitleDeleteBook.setText(datBan.getGhi_chu());
                txtNameDeleteBook.setText(datBan.getTen());
                txtNumberPeopleDeleteBook.setText(String.valueOf(datBan.getSo_nguoi()));
                txtDateDeleteBook.setText(datBan.getGio_den() + " " + datBan.getNgay_dat_hang());

                btnAgreeDeleteBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removeBook(datBan.getMa_dat_ban());
                        datBanList.remove(i);
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, "Xóa thành công !",Toast.LENGTH_SHORT).show();
                    }
                });

                btnCancelDeleteBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        holder.btnEditRowBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra("key", datBan.getMa_dat_ban());
                context.startActivity(intent);
            }
        });




        return view;
    }
    private void removeBook(String key){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("dat_ban").child(key).removeValue();

    }
}
