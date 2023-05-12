package com.example.appbandochoi.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.appbandochoi.HTTPRequest.AccountAPI;
import com.example.appbandochoi.HTTPRequest.CustomerAPI;
import com.example.appbandochoi.R;
import com.example.appbandochoi.doInBackGround.AsyncTaskDSTAIKHOAN;
import com.example.appbandochoi.doInBackGround.AsyncTaskKHACHHANG;
import com.example.appbandochoi.entity.DSTAIKHOAN;
import com.example.appbandochoi.entity.KHACHHANG;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DangKiActivity extends AppCompatActivity {
    EditText etTaiKhoan;
    EditText etMatKhau;
    EditText etHoTen;
    EditText etSDT;
    EditText etEmail;
    ImageButton btnUpload;
    EditText etDiaChiDK;
    Button btnDangKi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        setControl();
        setEvent();
    }

    private void setControl() {
        etTaiKhoan= findViewById(R.id.etTaiKhoanDK);
        etMatKhau= findViewById(R.id.etMKDK);
        etHoTen= findViewById(R.id.etHoTenDK);
        etSDT= findViewById(R.id.etSDTDK);
        btnDangKi= findViewById(R.id.btnDangKiDK);
        etDiaChiDK = findViewById(R.id.etDiaChiDK);
        etEmail= findViewById(R.id.etEmailDK);
    }
    private void setEvent() {
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etTaiKhoan.getText().toString().trim().equals(""))
                {
                    Toast toast = Toast.makeText(DangKiActivity.this,"Không được bỏ trống Tài Khoản", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(etMatKhau.getText().toString().trim().equals(""))
                {
                    Toast toast = Toast.makeText(DangKiActivity.this,"Không được bỏ trống Mật Khẩu", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(etHoTen.getText().toString().trim().equals(""))
                {
                    Toast toast = Toast.makeText(DangKiActivity.this,"Không được bỏ trống Họ Tên", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(etSDT.getText().toString().trim().equals(""))
                {
                    Toast toast = Toast.makeText(DangKiActivity.this,"Không được bỏ trống Số Điện Thoại", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    String taiKhoan = etTaiKhoan.getText().toString().trim();
                    String matKhau= etMatKhau.getText().toString().trim();
                    String hoTen= etHoTen.getText().toString().trim();
                    String SDT= etSDT.getText().toString().trim();

                    DSTAIKHOAN nd= new DSTAIKHOAN();
                    nd.setTaiKhoan(taiKhoan);
                    nd.setMatKhau(matKhau);
                    nd.setChucVu("KHACH");

                    KHACHHANG kh= new KHACHHANG();
                    kh.setHoTen(etHoTen.getText().toString());
                    kh.setDiaChi(etDiaChiDK.getText().toString());
                    kh.setSdt(etSDT.getText().toString());
                    kh.setEmail(etEmail.getText().toString());
                    kh.setTaiKhoan(nd);
                    new AsyncTaskDSTAIKHOAN().execute("saveTaiKhoan",nd);
                    new AsyncTaskKHACHHANG().execute("saveKH",kh);
                    KHACHHANG khs= null;
                    try {
                        khs = (KHACHHANG) new AsyncTaskKHACHHANG().execute("findKHByTK",taiKhoan).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //AccountAPI.saveTaiKhoan(nd);
                    Toast toast = Toast.makeText(DangKiActivity.this,"Tạo Tài Khoản Thành Công", Toast.LENGTH_LONG);
                    toast.show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("khachhang",khs);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

}
