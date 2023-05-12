package com.example.appbandochoi.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbandochoi.Activity.MainActivity;
import com.example.appbandochoi.Fragment.LichSuFragment;
import com.example.appbandochoi.R;
import com.example.appbandochoi.doInBackGround.AsyncTaskDDH;
import com.example.appbandochoi.doInBackGround.DbBitmapUtility;
import com.example.appbandochoi.entity.CTDDH;
import com.example.appbandochoi.entity.DDH;
import com.example.appbandochoi.entity.SANPHAM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterLSDH extends ArrayAdapter<DDH> {
    Context context;
    int resource;
    List<DDH> list;

    public AdapterLSDH(@NonNull Context context, int resource, @NonNull List<DDH> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView tvMSDDH = convertView.findViewById(R.id.tvMSDDH);
        TextView tvHoTenKH = convertView.findViewById(R.id.tvHoTenKH);
        TextView tvDiaChiKH= convertView.findViewById(R.id.tvDiaChiKH);
        TextView tvSDTKH = convertView.findViewById(R.id.tvSDTKH);
        TextView tvTongTien = convertView.findViewById(R.id.tvTongTienMuaSP);
        TextView tvTrangThai = convertView.findViewById(R.id.tvTrangThai);
        ImageView btnCancel = convertView.findViewById(R.id.ibCancel);

        DDH dh = list.get(position);
        System.out.println("khachhang:"+dh.getMaKH().getMaKH());
        System.out.println(dh.getHoTenKH());
        tvHoTenKH.setText(dh.getHoTenKH());
        tvMSDDH.setText(String.valueOf(dh.getMSDDH()));
        tvDiaChiKH.setText(dh.getDiaChi());
        tvSDTKH.setText(dh.getSdt());
        tvTrangThai.setText(dh.getTrangThai());
        int tongTien=0;
        for(CTDDH ct: dh.getCTDDH())
        {
            tongTien+=ct.getSoLuong()*ct.getSanPham().getDonGia();
        }
        tvTongTien.setText(tongTien +" $");
        if(dh.getTrangThai().trim().equals("CONFIRMED")||dh.getTrangThai().trim().equals("CANCELLED"))
        {
            btnCancel.setVisibility(View.INVISIBLE);
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dh.setTrangThai("CANCELLED");
                new AsyncTaskDDH().execute("cancelOrder",dh);
                Toast.makeText(getContext(),"Cancel Thành Công",Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
