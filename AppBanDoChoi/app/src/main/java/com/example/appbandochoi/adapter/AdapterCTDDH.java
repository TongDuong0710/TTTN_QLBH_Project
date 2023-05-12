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
import com.example.appbandochoi.R;
import com.example.appbandochoi.doInBackGround.DbBitmapUtility;
import com.example.appbandochoi.entity.CTDDH;
import com.example.appbandochoi.entity.SANPHAM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterCTDDH extends ArrayAdapter<CTDDH> {
    Context context;
    int resource;
    List<CTDDH> list;

    public AdapterCTDDH(@NonNull Context context, int resource, @NonNull List<CTDDH> list) {
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
        TextView tvTenSP =convertView.findViewById(R.id.tvTenSPCTDDH);
        TextView tvLoaiSP =convertView.findViewById(R.id.tvLoaiSPCTDDH);
        TextView tvGiaSP=convertView.findViewById(R.id.tvGiaSPCTDDH);
        TextView tvSLSP=convertView.findViewById(R.id.tvSLSPCTDDH);
        ImageView ivHinhAnhSP=convertView.findViewById(R.id.ivHinhAnhSPCTDDH);

        SANPHAM sp = list.get(position).getSanPham();
        tvTenSP.setText(sp.getTenSP());
        tvLoaiSP.setText(sp.getLoaiSP());
        tvGiaSP.setText(String.valueOf(sp.getDonGia())+" $");
        tvSLSP.setText(String.valueOf(list.get(position).getSoLuong()));
        //Thiếu Set Hinh
        try {
            Resources resources = getContext().getResources();
            Uri uri = Uri.parse(MainActivity.drawablePath+sp.getHinhAnh().trim().toLowerCase().substring(0,sp.getHinhAnh().trim().length()-4));
            Bitmap bm=  MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            Bitmap newBM = DbBitmapUtility.scaleDownBitmap(bm,100,context);
            ivHinhAnhSP.setImageBitmap(newBM);
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
            ivHinhAnhSP.setImageBitmap(newBM);
            e.printStackTrace();
        }
        return convertView;
    }
}
