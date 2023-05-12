package com.example.appbandochoi.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.appbandochoi.Activity.DatHangSPActivity;
import com.example.appbandochoi.Activity.MainActivity;
import com.example.appbandochoi.R;
import com.example.appbandochoi.adapter.AdapterCTDDH;
import com.example.appbandochoi.adapter.AdapterGioHang;
import com.example.appbandochoi.adapter.AdapterLSDH;
import com.example.appbandochoi.adapter.AdapterSanPham;
import com.example.appbandochoi.doInBackGround.AsyncTaskDDH;
import com.example.appbandochoi.entity.CTDDH;
import com.example.appbandochoi.entity.DDH;
import com.example.appbandochoi.entity.KHACHHANG;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class LichSuFragment extends Fragment {
    View view = null;
    ListView lvLSDDH;
    KHACHHANG kh=null;
    List<DDH> ddhList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           kh = (KHACHHANG) getArguments().getSerializable("khachhang");
            System.out.println("mkh:"+kh.getMaKH());
            try {
                ddhList= (List<DDH>) new AsyncTaskDDH().execute("getHistoryOrder",kh).get();
                for(DDH dh:ddhList)
                {
                    dh.setMaKH(kh);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lichsu, container, false);
        lvLSDDH= view.findViewById(R.id.lvLSDDH);
        lvLSDDH.setAdapter(new AdapterLSDH(view.getContext(), R.layout.adapter_lsddh, ddhList));
        lvLSDDH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_ctddh, null);
                ListView lvCTDDH = customLayout.findViewById(R.id.lvCTDDH);
                lvCTDDH.setAdapter(new AdapterCTDDH(view.getContext(), R.layout.adapter_ctddh, ddhList.get(i).getCTDDH()));
                TextView tvTongTienCTDDH= customLayout.findViewById(R.id.tvTongTienCTDDH);
                int tongTien=0;
                for(CTDDH ct:ddhList.get(i).getCTDDH())
                {
                    tongTien+=ct.getSoLuong()*ct.getSanPham().getDonGia();
                }
                tvTongTienCTDDH.setText(tongTien+ " $");
                builder.setView(customLayout);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        return view;
    }

}