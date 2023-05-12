package com.example.appbandochoi.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;


import com.example.appbandochoi.Activity.MainActivity;
import com.example.appbandochoi.R;
import com.example.appbandochoi.doInBackGround.DbBitmapUtility;
import com.example.appbandochoi.entity.SANPHAM;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterSanPham extends ArrayAdapter<SANPHAM> {
    Context context;
    int resource;
    ArrayList<SANPHAM> list;

    public AdapterSanPham(@NonNull Context context, int resource, @NonNull ArrayList<SANPHAM> list) {
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
        TextView tvTenSP =convertView.findViewById(R.id.tvTenSP);
        TextView tvLoaiSP =convertView.findViewById(R.id.tvLoaiSP);
        TextView tvGiaSP=convertView.findViewById(R.id.tvGiaSP);
        ImageView ivHinhAnhSP=convertView.findViewById(R.id.ivHinhAnhSP);
        ImageView btnMuaSP=convertView.findViewById(R.id.ibMuaSP);

        SANPHAM sp = list.get(position);
        tvTenSP.setText(sp.getTenSP());
        tvLoaiSP.setText(sp.getLoaiSP());
        tvGiaSP.setText(String.valueOf(sp.getDonGia())+" $");
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


        btnMuaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CLICK VÀO MUA SP
                if(testContains(sp,MainActivity.listGH))
                {
                    Toast.makeText(context, "Sản Phẩm Đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MainActivity.listGH.add(sp);
                    Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return convertView;
    }
    private boolean testContains(SANPHAM sp, List<SANPHAM> list)
    {
        for (SANPHAM s:list)
        {
            if (s.getMaSP().equals(sp.getMaSP()))
            {
                return true;
            }
        }
        return false;
    }
}
