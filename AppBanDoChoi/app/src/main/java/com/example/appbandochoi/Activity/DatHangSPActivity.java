package com.example.appbandochoi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbandochoi.R;
import com.example.appbandochoi.adapter.AdapterGioHang;
import com.example.appbandochoi.adapter.AdapterXNDH;
import com.example.appbandochoi.doInBackGround.AsyncTaskDSTAIKHOAN;
import com.example.appbandochoi.doInBackGround.AsyncTaskKHACHHANG;
import com.example.appbandochoi.entity.CTDDH;
import com.example.appbandochoi.entity.DSTAIKHOAN;
import com.example.appbandochoi.entity.KHACHHANG;
import com.example.appbandochoi.entity.SANPHAM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class DatHangSPActivity extends AppCompatActivity {
    ListView lvXNDHSP;
    Button btnTiepTheo;
    Button btnQuayLai;
    TextView tvTongTien;
    List<SANPHAM> listSP;
    List<CTDDH> listCTDDH;
    KHACHHANG kh=null;
    int tongTien=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathang_sp);
        setControl();
        setEvent();
    }
    private void khoiTao(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kh = (KHACHHANG) extras.getSerializable("khachhang");
        }
    }
    private void setControl() {
        khoiTao();
        lvXNDHSP=findViewById(R.id.lvXNDHSP);
        btnTiepTheo=findViewById(R.id.btnTiepTheo);
        tvTongTien=findViewById(R.id.tvTongTien);
        btnQuayLai=findViewById(R.id.btnQuayLai);
    }
    private void setEvent() {
        listSP=new ArrayList<>(MainActivity.listGH);
        listCTDDH=taoCTDDH();
        tongTien=tinhTongTien();
        setTongTien(tongTien);
        lvXNDHSP.setAdapter(new AdapterXNDH(this, R.layout.adapter_xacnhandh_sp, listCTDDH));
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnTiepTheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Gửi khách hàng qua bên kia
                //Tạo 1 list CTDDH -> gửi qua bên kia
                Intent intent = new Intent(DatHangSPActivity.this, DatHangNDActivity.class);
                intent.putExtra("listctddh",(Serializable)listCTDDH);
                intent.putExtra("khachhang",kh);
                startActivity(intent);
                finish();
            }
        });
    }
    private List<CTDDH>taoCTDDH()
    {
        List<CTDDH> ctddhList=new ArrayList<>();
        for(SANPHAM sp:listSP)
        {
            ctddhList.add(new CTDDH(sp,1));
        }
        return ctddhList;
    }
    public int tinhTongTien()
    {
        int tong=0;
        for (CTDDH ct:listCTDDH)
        {
            tong+=ct.getSanPham().getDonGia()*ct.getSoLuong();
        }
        return tong;
    }
    public void setTongTien(int tongTien)
    {
        tvTongTien.setText(tongTien+" $");
    }



}
