package com.example.appbandochoi.doInBackGround;

import android.os.AsyncTask;

import com.example.appbandochoi.HTTPRequest.OrderAPI;
import com.example.appbandochoi.HTTPRequest.ProductAPI;
import com.example.appbandochoi.entity.DDH;
import com.example.appbandochoi.entity.KHACHHANG;
import com.example.appbandochoi.entity.SANPHAM;

import java.util.ArrayList;
import java.util.List;

public class AsyncTaskDDH extends AsyncTask<Object,  ArrayList<Object>, List<DDH>> {
    @Override
    protected List<DDH> doInBackground(Object... objects) {
        if(objects[0].toString().trim().equals("insertOrder")) {
            OrderAPI.insertOrder((DDH)objects[1]);
            return null;
        }
        else if(objects[0].toString().trim().equals("getHistoryOrder")){
            return OrderAPI.getHistoryOrder(((KHACHHANG) objects[1]).getMaKH());
        }
        else if(objects[0].toString().trim().equals("cancelOrder")){
             OrderAPI.cancelOrder(((DDH) objects[1]));
            return null;
        }
        return null;
    }
}
