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

import com.example.appbandochoi.Activity.DatHangSPActivity;
import com.example.appbandochoi.Activity.MainActivity;
import com.example.appbandochoi.R;
import com.example.appbandochoi.doInBackGround.DbBitmapUtility;
import com.example.appbandochoi.entity.CTDDH;
import com.example.appbandochoi.entity.SANPHAM;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import java.io.IOException;
import java.util.List;

public class AdapterXNDH extends ArrayAdapter<CTDDH> {
    Context context;
    int resource;
    List<CTDDH> listCTDDH;


    public AdapterXNDH(@NonNull Context context, int resource, @NonNull List<CTDDH> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.listCTDDH = list;
    }

    @Override
    public int getCount() {
        return listCTDDH.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView tvTenSPXNDH =convertView.findViewById(R.id.tvTenSPXNDH);
        TextView tvLoaiSPXNDH =convertView.findViewById(R.id.tvLoaiSPXNDH);
        TextView tvGiaSPXNDH=convertView.findViewById(R.id.tvGiaSPXNDH);
        ImageView ivHinhAnhSPXNDH=convertView.findViewById(R.id.ivHinhAnhSPXNDH);
        ImageView ibDeleteSPXNDH=convertView.findViewById(R.id.ibDeleteSPXNDH);
        NumberPicker npXNDH = (NumberPicker)  convertView.findViewById(R.id.npXNDH);

        CTDDH ct = listCTDDH.get(position);
        tvTenSPXNDH.setText(ct.getSanPham().getTenSP());
        tvLoaiSPXNDH.setText(ct.getSanPham().getLoaiSP());
        tvGiaSPXNDH.setText(String.valueOf(ct.getSanPham().getDonGia())+" $");
        npXNDH.setValue(ct.getSoLuong());
        //Thiếu Set Hinh
        try {
            Resources resources = getContext().getResources();
            Uri uri = Uri.parse(MainActivity.drawablePath+ct.getSanPham().getHinhAnh().trim().toLowerCase().substring(0,ct.getSanPham().getHinhAnh().trim().length()-4));
            Bitmap bm=  MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            Bitmap newBM = DbBitmapUtility.scaleDownBitmap(bm,100,context);
            ivHinhAnhSPXNDH.setImageBitmap(newBM);
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
            ivHinhAnhSPXNDH.setImageBitmap(newBM);
            e.printStackTrace();
        }


        ibDeleteSPXNDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listCTDDH.remove(ct);
                notifyDataSetChanged();
                ((DatHangSPActivity) context).setTongTien(((DatHangSPActivity) context).tinhTongTien());
            }
        });

        npXNDH.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                if(action.toString().equals("INCREMENT"))
                {
                    ct.setSoLuong(ct.getSoLuong()+1);
                    ((DatHangSPActivity) context).setTongTien(((DatHangSPActivity) context).tinhTongTien());
                }
                else {
                    ct.setSoLuong(ct.getSoLuong()-1);
                    ((DatHangSPActivity) context).setTongTien(((DatHangSPActivity) context).tinhTongTien());
                }

            }
        });
        return convertView;
    }
}
