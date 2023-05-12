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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbandochoi.Activity.MainActivity;
import com.example.appbandochoi.R;
import com.example.appbandochoi.doInBackGround.DbBitmapUtility;
import com.example.appbandochoi.entity.SANPHAM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterGioHang extends ArrayAdapter<SANPHAM> {
    Context context;
    int resource;
    List<SANPHAM> list;

    public AdapterGioHang(@NonNull Context context, int resource, @NonNull List<SANPHAM> list) {
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
        TextView tvTenSPGH =convertView.findViewById(R.id.tvTenSPGH);
        TextView tvLoaiSPGH =convertView.findViewById(R.id.tvLoaiSPGH);
        TextView tvGiaSPGH=convertView.findViewById(R.id.tvGiaSPGH);
        ImageView ivHinhAnhSPGH=convertView.findViewById(R.id.ivHinhAnhSPGH);
        ImageView ibDeleteSPGH=convertView.findViewById(R.id.ibDeleteSPGH);

        SANPHAM sp = list.get(position);
       // System.out.println(sp.getTenSP());
        tvTenSPGH.setText(sp.getTenSP());
        tvLoaiSPGH.setText(sp.getLoaiSP());
        tvGiaSPGH.setText(String.valueOf(sp.getDonGia())+" $");
        //Thiếu Set Hinh
        try {
            Resources resources = getContext().getResources();
            Uri uri = Uri.parse(MainActivity.drawablePath+sp.getHinhAnh().trim().toLowerCase().substring(0,sp.getHinhAnh().trim().length()-4));
            Bitmap bm=  MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            Bitmap newBM = DbBitmapUtility.scaleDownBitmap(bm,100,context);
            ivHinhAnhSPGH.setImageBitmap(newBM);
        }
        catch (Exception e)
        {
            Resources resources = getContext().getResources();
            Uri uri2 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(R.drawable.hinh1) + '/' + resources.getResourceTypeName(R.drawable.hinh1) + '/' + resources.getResourceEntryName(R.drawable.hinh1));
            System.out.println("URI:"+ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(R.drawable.hinh1) + '/' + resources.getResourceTypeName(R.drawable.hinh1) + '/' + resources.getResourceEntryName(R.drawable.hinh1));
            Bitmap bm= null;
            try {
                bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri2);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Bitmap newBM = DbBitmapUtility.scaleDownBitmap(bm,100,context);
            ivHinhAnhSPGH.setImageBitmap(newBM);
            e.printStackTrace();
        }


        ibDeleteSPGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.listGH.remove(sp);
                list.remove(sp);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
