package com.example.appbandochoi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appbandochoi.Activity.DangKiActivity;
import com.example.appbandochoi.Activity.DatHangSPActivity;
import com.example.appbandochoi.Activity.MainActivity;
import com.example.appbandochoi.R;
import com.example.appbandochoi.adapter.AdapterGioHang;
import com.example.appbandochoi.adapter.AdapterSanPham;
import com.example.appbandochoi.entity.KHACHHANG;
import com.example.appbandochoi.entity.SANPHAM;

import java.util.ArrayList;
import java.util.List;


public class GioHangFragment extends Fragment {
    View view = null;
    ListView lvGH;
    Button btnThanhToan;
    KHACHHANG kh=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           kh = (KHACHHANG) getArguments().getSerializable("khachhang");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_giohang, container, false);
        lvGH = view.findViewById(R.id.lvGH);
        btnThanhToan=view.findViewById(R.id.btnThanhToanGH);
        lvGH.setAdapter(new AdapterGioHang(view.getContext(), R.layout.adapter_giohang, MainActivity.listGH));
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.listGH.size()<1)
                {
                    Toast.makeText(getContext(),"Chưa có Sản Phẩm trong giỏ hàng",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(view.getContext(), DatHangSPActivity.class);
                intent.putExtra("khachhang",kh);
                startActivity(intent);
            }
        });
        return view;
    }
}