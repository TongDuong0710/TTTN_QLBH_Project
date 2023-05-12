package com.example.appbandochoi.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbandochoi.R;
import com.example.appbandochoi.doInBackGround.AsyncTaskKHACHHANG;
import com.example.appbandochoi.entity.KHACHHANG;

public class QuanLiNguoiDungActivity extends AppCompatActivity {

    EditText edtHoTen, edtSdt,edtDiaChi,edtEmail;
    Button btnUpLoadImgUser, btnUpdateUser;
    KHACHHANG kh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_nguoi_dung);
        setControl();
        setEvent();

    }

    private void setEvent() {
        khoiTao();
        edtHoTen.setText(kh.getHoTen());
        edtSdt.setText(kh.getSdt());
        edtEmail.setText(kh.getEmail());
        edtDiaChi.setText(kh.getDiaChi());
        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen = edtHoTen.getText().toString();
                String sdt = edtSdt.getText().toString();
                String email= edtEmail.getText().toString();
                String diaChi = edtDiaChi.getText().toString();
                if(!checkValidate(hoTen)){
                    Toast.makeText(QuanLiNguoiDungActivity.this, "Không được bỏ trống họ tên", Toast.LENGTH_SHORT).show();
                }
                if(!checkValidate(sdt)){
                    Toast.makeText(QuanLiNguoiDungActivity.this, "Không dược bỏ trống số điện thoại", Toast.LENGTH_SHORT).show();
                }
                kh.setHoTen(hoTen);
                kh.setSdt(sdt);
                kh.setEmail(email);
                kh.setDiaChi(diaChi);
                new AsyncTaskKHACHHANG().execute("updateCus",kh);
                Toast.makeText(QuanLiNguoiDungActivity.this, "Cập Nhật Thông Tin Thành Công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QuanLiNguoiDungActivity.this, MainActivity.class);
                intent.putExtra("khachhang",kh);
                startActivity(intent);
            }
        });
    }

    private void khoiTao() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kh = (KHACHHANG) extras.getSerializable("khachhang");
        }

    }

    private boolean checkValidate(String s) {
        if(s.isEmpty()) return false;
        return true;
    }

    private void setControl() {
        edtHoTen = findViewById(R.id.edtHoTen);
        edtSdt = findViewById(R.id.edtSdt);
        btnUpdateUser = findViewById(R.id.btnUpdateUser);
        edtDiaChi=findViewById(R.id.edtDiaChi);
        edtEmail=findViewById(R.id.edtEmail);
    }
}