package com.example.appbandochoi.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbandochoi.HTTPRequest.OrderAPI;
import com.example.appbandochoi.R;
import com.example.appbandochoi.doInBackGround.AsyncTaskDDH;
import com.example.appbandochoi.doInBackGround.AsyncTaskDSTAIKHOAN;
import com.example.appbandochoi.doInBackGround.AsyncTaskKHACHHANG;
import com.example.appbandochoi.doInBackGround.JavaMailAPI;
import com.example.appbandochoi.entity.CTDDH;
import com.example.appbandochoi.entity.DDH;
import com.example.appbandochoi.entity.DSTAIKHOAN;
import com.example.appbandochoi.entity.KHACHHANG;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DatHangNDActivity extends AppCompatActivity {
    EditText etHoTenDH;
    EditText etSDTDH;
    EditText etEmailDH;
    EditText etDiaChiDH;
    Button btnXacNhan;
    Button btnBack;

    List<CTDDH> ctddhList;
    KHACHHANG kh=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathang);
        setControl();
        setEvent();
    }

    private void setControl() {
        khoiTao();
        etHoTenDH= findViewById(R.id.etHoTenDH);
        etSDTDH= findViewById(R.id.etSDTDH);
        btnXacNhan= findViewById(R.id.btnXacNhanDH);
        etDiaChiDH = findViewById(R.id.etDiaChiDH);
        etEmailDH= findViewById(R.id.etEmailDH);
        btnBack=findViewById(R.id.btnQuayLaiDH);
    }
    private void khoiTao()
    {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kh = (KHACHHANG) extras.getSerializable("khachhang");
            ctddhList= (List<CTDDH>) extras.getSerializable("listctddh");
        }
    }
    private void setEvent() {
        if(kh!=null)
        {
            System.out.println("kh không null");
            etHoTenDH.setText(kh.getHoTen());
            etDiaChiDH.setText(kh.getDiaChi());
            etEmailDH.setText(kh.getEmail());
            etSDTDH.setText(kh.getSdt());
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen= etHoTenDH.getText().toString().trim();
                String diaChi=etDiaChiDH.getText().toString().trim();
                String email=etEmailDH.getText().toString().trim();
                String sdt=etSDTDH.getText().toString().trim();
                if(hoTen.equals(""))
                {
                    Toast toast = Toast.makeText(DatHangNDActivity.this,"Không được bỏ trống Họ Tên", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(diaChi.equals(""))
                {
                    Toast toast = Toast.makeText(DatHangNDActivity.this,"Không được bỏ trống Địa Chỉ", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(sdt.equals(""))
                {
                    Toast toast = Toast.makeText(DatHangNDActivity.this,"Không được bỏ trống Số Điện Thoại", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    DDH ddh = new DDH();
                    ddh.setMaKH(kh);
                    ddh.setTrangThai("NEW");
                    ddh.setCTDDH(ctddhList);
                    ddh.setHoTenKH(hoTen);
                    ddh.setDiaChi(diaChi);
                    ddh.setSdt(sdt);
                    ddh.setEmail(email);
                    for(CTDDH ctddh:ddh.getCTDDH())
                    {
                        ctddh.setDonDatHang(ddh);
                    }

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DatHangNDActivity.this);
                    alertDialogBuilder.setMessage("Bạn có chắc chắn muốn Đặt Hàng ?");
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.R)
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            new AsyncTaskDDH().execute("insertOrder",ddh);
                            Toast toast = Toast.makeText(DatHangNDActivity.this,"Đặt Hàng Thành Công", Toast.LENGTH_LONG);
                            toast.show();
                            if(!email.equals(""))
                            {
                                //Gửi mail
                                String body="MSDDH: "+ddh.getMSDDH()+"<br>Họ Tên: "+ddh.getHoTenKH()+"<br>Địa chỉ: "+ddh.getDiaChi()+"<br>SDT: "+ ddh.getSdt()+"<br>Chi tiết đơn Hàng<br>";
                                body+="<h2>THÔNG TIN ĐƠN HÀNG</h2>";
                                body+="<h4>HỌ TÊN: "+ddh.getHoTenKH()+"</h4>";
                                body+="<h4>ĐỊA CHỈ: "+ddh.getDiaChi()+"</h4>";
                                body+="<h4>SỐ ĐIỆN THOẠI: "+ddh.getSdt()+"</h4>";
                                body+="<h4>TỔNG GIÁ: $"+ddh.getTongGia()+"</h4>";
                                body+="<h3>CHI TIẾT ĐƠN HÀNG</h3>";
                                body+="<table style='border: 1px solid black; border-collapse: collapse;'>\r\n"
                                        + "  <tr  >\r\n"
                                        + "    <th style=' border: 1px solid black' >Product Name</th>\r\n"
                                        + "    <th  style=' border: 1px solid black' >Model</th>\r\n"
                                        + "    <th style=' border: 1px solid black'  >Quantity</th>\r\n"
                                        + "    <th style=' border: 1px solid black'  >Unit Price</th>\r\n"
                                        + "    <th style=' border: 1px solid black'  >Total</th>\r\n"
                                        + "  </tr>";
                                for(CTDDH x: ddh.getCTDDH())
                                {
                                    body+="<tr   >\r\n"
                                            + "    <td style=' border: 1px solid black' >"+x.getSanPham().getTenSP()+"</td>\r\n"
                                            + "    <td style=' border: 1px solid black' >"+x.getSanPham().getLoaiSP()+"</td>\r\n"
                                            + "    <td style=' border: 1px solid black'  >"+x.getSoLuong()+"</td>\r\n"
                                            + "    <td style=' border: 1px solid black'  >"+x.getSanPham().getDonGia()+"</td>\r\n"
                                            + "    <td style=' border: 1px solid black' >"+(x.getSanPham().getDonGia()*x.getSoLuong())+"</td>\r\n"
                                            + "  </tr>";
                                }
                                body+="</table>";
                                body+="<h3>VUI LÒNG ĐỢI NHÂN VIÊN XÁC NHẬN ĐƠN HÀNG!</h3>";
                                new JavaMailAPI(DatHangNDActivity.this,email,"THÔNG TIN ĐƠN ĐẶT HÀNG",body).execute();
                            }
                            finish();
                        }
                    });
                    alertDialogBuilder.setNeutralButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialogBuilder.create().show();

                }
            }
        });
    }

}
