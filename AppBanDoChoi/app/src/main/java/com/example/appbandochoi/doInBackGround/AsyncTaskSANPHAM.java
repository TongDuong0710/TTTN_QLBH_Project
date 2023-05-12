package com.example.appbandochoi.doInBackGround;

import android.os.AsyncTask;

import com.example.appbandochoi.HTTPRequest.ProductAPI;
import com.example.appbandochoi.entity.SANPHAM;

import java.util.ArrayList;
import java.util.List;

public class AsyncTaskSANPHAM extends AsyncTask<Object, ArrayList<Object>, List<SANPHAM>> {
    @Override
    protected List<SANPHAM> doInBackground(Object... objects) {
        if(objects[0].toString().trim().equals("findAllSP")) {
            return ProductAPI.findAllSP();
        }
        else if(objects[0].toString().trim().equals("findByTenSPContaining"))
        {
            return ProductAPI.findByTenSPContaining(objects[1].toString());
        }
        return null;
    }
}
