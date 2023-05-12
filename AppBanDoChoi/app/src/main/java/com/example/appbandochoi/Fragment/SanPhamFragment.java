package com.example.appbandochoi.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;

import com.example.appbandochoi.R;
import com.example.appbandochoi.adapter.AdapterSanPham;
import com.example.appbandochoi.doInBackGround.AsyncTaskSANPHAM;
import com.example.appbandochoi.entity.KHACHHANG;
import com.example.appbandochoi.entity.SANPHAM;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SanPhamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SanPhamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView lvDSSanPham;
    List<SANPHAM> listSanPham=null;
    KHACHHANG kh;
    SearchView svDSSP;

    public SanPhamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BoTheCuaToiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SanPhamFragment newInstance(String param1, String param2) {
        SanPhamFragment fragment = new SanPhamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            kh = (KHACHHANG) getArguments().getSerializable("khachhang");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);
        lvDSSanPham = view.findViewById(R.id.lvSP);
        svDSSP =view.findViewById(R.id.svDSSP);
        try {
            listSanPham = new AsyncTaskSANPHAM().execute("findAllSP").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<SANPHAM> arrayListSP=new ArrayList<SANPHAM>(listSanPham);
        lvDSSanPham.setAdapter(new AdapterSanPham(view.getContext(), R.layout.adapter_sanpham, arrayListSP));
        lvDSSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //CLICK VÀO SP RA CHI TIẾT SPAdapterBoThe
            }
        });
        svDSSP.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if(s.trim().equals("")==false)
                {
                    try {
                        listSanPham = new AsyncTaskSANPHAM().execute("findByTenSPContaining",s).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ArrayList<SANPHAM> arrayListSP=new ArrayList<SANPHAM>(listSanPham);
                    lvDSSanPham.setAdapter(new AdapterSanPham(view.getContext(), R.layout.adapter_sanpham, arrayListSP));
                }
                else
                {
                    try {
                        listSanPham = new AsyncTaskSANPHAM().execute("findAllSP").get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ArrayList<SANPHAM> arrayListSP=new ArrayList<SANPHAM>(listSanPham);
                    lvDSSanPham.setAdapter(new AdapterSanPham(view.getContext(), R.layout.adapter_sanpham, arrayListSP));
                }

                return false;
            }
        });

        return  view;
    }



}