package com.example.appbandochoi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.CursorWindow;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbandochoi.Fragment.GioHangFragment;
import com.example.appbandochoi.Fragment.LichSuFragment;
import com.example.appbandochoi.Fragment.SanPhamFragment;
import com.example.appbandochoi.R;
import com.example.appbandochoi.entity.KHACHHANG;
import com.example.appbandochoi.entity.SANPHAM;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String drawablePath = "android.resource://com.example.appbandochoi/drawable/";
    public static List<SANPHAM> listGH = new ArrayList<>();
    private ActionBar toolbar;
    BottomNavigationView navigation;
    private static final int ACTIVITY_CHOOSE_FILE = 3;
    TextToSpeech audio;
    ImageView ivHinhAnhMain;
    String str = "hello into school";
    ImageView ivHinhAnhUser;
    KHACHHANG kh;
    private DrawerLayout mDrawer;
    private Toolbar toolbarNav;
    private NavigationView nvDrawer;
    TextView tvHoTenUser;
    TextView tvSDTUser;
    TextView tvEmailUser;
    TextView tvDiaChiUser;
    Button btnDangXuat;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }
        setControl();
        setEvent();
    }

    private void setControl() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            kh = (KHACHHANG) extras.getSerializable("khachhang");
            System.out.println("khachHang:" + kh.getHoTen());
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        View headerView = navigationView.getHeaderView(0);
        tvHoTenUser = (TextView) headerView.findViewById(R.id.tvHoTenUser);
        tvSDTUser = (TextView) headerView.findViewById(R.id.tvSDTUser);
        tvEmailUser = (TextView) headerView.findViewById(R.id.tvEmailUser);
        tvDiaChiUser = (TextView) headerView.findViewById(R.id.tvDiaChiUser);
        ivHinhAnhUser = (ImageView) headerView.findViewById(R.id.ivHinhAnhUser);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        toolbarNav = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbarNav);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);

    }


    private void setEvent() {
        if (kh != null) {
            tvHoTenUser.setText(kh.getHoTen());
            tvSDTUser.setText(kh.getSdt());
            tvDiaChiUser.setText(kh.getDiaChi());
            tvEmailUser.setText(kh.getEmail());
        }
        toolbar = getSupportActionBar();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("Shop");
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Fragment fragment = new SanPhamFragment();
        loadFragment(fragment);
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.page_1:
                    toolbar.setTitle("Shop");
                    fragment = new SanPhamFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.page_2:
                    toolbar.setTitle("Giỏ Hàng");
                    fragment = new GioHangFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.page_3:
                    if (kh != null) {
                        toolbar.setTitle("Lịch Sử Đơn Hàng");
                        fragment = new LichSuFragment();
                        loadFragment(fragment);
                    }
                    else{
                        Toast toast = Toast.makeText(MainActivity.this, "Bạn Chưa Có Tài Khoản, Vui Lòng Đăng Nhập", Toast.LENGTH_LONG);
                        toast.show();                    }
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        Bundle bundle = new Bundle();
        bundle.putSerializable("khachhang", kh);
        fragment.setArguments(bundle);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbarNav, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.navDoiThongTin:
                                if (kh == null) {
                                    Toast toast = Toast.makeText(MainActivity.this, "Bạn Chưa Có Tài Khoản, Vui Lòng Đăng Nhập", Toast.LENGTH_LONG);
                                    toast.show();
                                } else {
                                    Intent intent = new Intent(MainActivity.this, QuanLiNguoiDungActivity.class);
                                    intent.putExtra("khachhang", kh);
                                    startActivity(intent);
                                }
                                break;
                            case R.id.navDoiMK:
                                if (kh == null) {
                                    Toast toast = Toast.makeText(MainActivity.this, "Bạn Chưa Có Tài Khoản, Vui Lòng Đăng Nhập", Toast.LENGTH_LONG);
                                    toast.show();
                                } else {
                                    Intent intent1 = new Intent(MainActivity.this, DoiMatKhauActivity.class);
                                    intent1.putExtra("khachhang", kh);
                                    startActivity(intent1);
                                }
                                break;
                            case R.id.navAboutUs:
                                Intent intent2 = new Intent(MainActivity.this, GGMapActivity.class);
                                intent2.putExtra("khachhang", kh);
                                startActivity(intent2);
                                break;
                        }
                        mDrawer.closeDrawers();
                        menuItem.setChecked(false);
                        return false;
                    }
                });
    }
}