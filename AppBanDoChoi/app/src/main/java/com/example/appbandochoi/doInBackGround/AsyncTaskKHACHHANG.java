package com.example.appbandochoi.doInBackGround;

import android.os.AsyncTask;

import com.example.appbandochoi.HTTPRequest.CustomerAPI;
import com.example.appbandochoi.entity.KHACHHANG;

public class AsyncTaskKHACHHANG extends AsyncTask<Object, Object, Object> {

    @Override
    protected Object doInBackground(Object... objects) {
        if(objects[0].toString().trim().equals("findKHByTK"))
        {
            return CustomerAPI.findKHByTK(objects[1].toString());
        }
        else if(objects[0].toString().trim().equals("findKHByID"))
        {
            return CustomerAPI.findKHByID(Integer.valueOf(objects[1].toString()));
        }
        else if(objects[0].toString().trim().equals("saveKH"))
        {
           CustomerAPI.saveKhachHang((KHACHHANG) objects[1]);
            return null;
        }
        else if(objects[0].toString().trim().equals("updateCus"))
        {
            CustomerAPI.updateCus((KHACHHANG) objects[1]);
            return null;
        }
        return null;
    }
}
