package com.example.appbandochoi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbandochoi.R;
import com.example.appbandochoi.doInBackGround.AsyncTaskDSTAIKHOAN;
import com.example.appbandochoi.doInBackGround.AsyncTaskKHACHHANG;
import com.example.appbandochoi.entity.DSTAIKHOAN;
import com.example.appbandochoi.entity.KHACHHANG;

import java.util.concurrent.ExecutionException;


public class DangNhapActivity extends AppCompatActivity {
    EditText etTaiKhoan;
    EditText etMatKhau;
    TextView tvDangKi;
    TextView tvDangNhapKhach;
    Button btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //setContentView(R.layout.adapter_lsddh);
        //IgnoreAsyn.ignoreAsyn();
        setControl();
        setEvent();
    }
    private void setControl() {
        etTaiKhoan=findViewById(R.id.etTaiKhoanDN);
        etMatKhau=findViewById(R.id.etMatKhauDN);
        tvDangKi=findViewById(R.id.tvDangKiDN);
        tvDangNhapKhach=findViewById(R.id.tvDangNhapKhach);
        btnDangNhap=findViewById(R.id.btnDangNhap);
    }
    private void setEvent() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taiKhoan= etTaiKhoan.getText().toString().trim();
                String matKhau=etMatKhau.getText().toString().trim();
                System.out.println(taiKhoan);
                // READ NGƯỜI DÙNG

                //DSTAIKHOAN nd= AccountAPI.getTaiKhoan(taiKhoan);
                DSTAIKHOAN nd=null;
                try {
                    nd= (DSTAIKHOAN) new AsyncTaskDSTAIKHOAN().execute("getTaiKhoan",taiKhoan).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(nd==null)
                {
                    Toast toast = Toast.makeText(DangNhapActivity.this,"User Null", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (nd.getMatKhau().equals(matKhau))
                {
                    Toast toast = Toast.makeText(DangNhapActivity.this,"Đăng nhập thành công", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                    KHACHHANG kh= null;
                    try {
                        kh= (KHACHHANG) new AsyncTaskKHACHHANG().execute("findKHByTK",taiKhoan).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    intent.putExtra("khachhang",kh);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast toast = Toast.makeText(DangNhapActivity.this,"SAI TÀI KHOẢN HOẶC MẬT KHẨU", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        tvDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangKiActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tvDangNhapKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
