package com.example.appbandochoi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbandochoi.R;
import com.example.appbandochoi.doInBackGround.AsyncTaskDSTAIKHOAN;
import com.example.appbandochoi.entity.DSTAIKHOAN;
import com.example.appbandochoi.entity.KHACHHANG;

public class DoiMatKhauActivity extends AppCompatActivity {

    EditText edChangePw1, edChangePw2, edOldPw;
    Button btnUpdatePw;
    KHACHHANG kh;

    String realUsername, realPwd;
    String checkOldPwd, checkNewPwd, checkConfirmPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        setControl();
        setEvent();
    }

    private void setEvent() {
        khoiTao();
        btnUpdatePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkValidate(edOldPw.getText().toString()))
                {
                    Toast.makeText(DoiMatKhauActivity.this, "Không được bỏ trống Mật Khẩu Cũ.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!edOldPw.getText().toString().equals(kh.getTaiKhoan().getMatKhau()))
                {
                    Toast.makeText(DoiMatKhauActivity.this, "Sai Mật Khẩu Cũ.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!checkValidate(edChangePw1.getText().toString()))
                {
                    Toast.makeText(DoiMatKhauActivity.this, "Không được bỏ trống mật khẩu mới.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!checkValidate(edChangePw2.getText().toString()))
                {
                    Toast.makeText(DoiMatKhauActivity.this, "Không được bỏ trống nhập lại mật khẩu.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!edChangePw2.getText().toString().trim().equals(edChangePw1.getText().toString().trim()))
                {
                    Toast.makeText(DoiMatKhauActivity.this, "Mật Khẩu Mới không khớp.", Toast.LENGTH_SHORT).show();
                    return;
                }

                kh.getTaiKhoan().setMatKhau(edChangePw1.getText().toString());
                try {
                    DSTAIKHOAN tk= kh.getTaiKhoan();
                    new AsyncTaskDSTAIKHOAN().execute("updateMK",tk);
                    Toast.makeText(DoiMatKhauActivity.this, "Đổi mật khẩu thành công.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("khachhang",kh);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    System.out.println("Lỗi: "+ e.getMessage());
                    Toast.makeText(DoiMatKhauActivity.this, "Đổi mật khẩu thất bại.", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    private boolean checkValidate(String s) {
        if(s.isEmpty()) return false;
        return true;
    }

    private void khoiTao() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kh=(KHACHHANG) extras.getSerializable("khachhang");
        }
    }
    private void setControl() {

        edChangePw1= findViewById(R.id.etChangePw1);
        edChangePw2= findViewById(R.id.etChangePw2);
        edOldPw = findViewById(R.id.etOldPw);
        btnUpdatePw = findViewById(R.id.btn_update_pw);



    }
}