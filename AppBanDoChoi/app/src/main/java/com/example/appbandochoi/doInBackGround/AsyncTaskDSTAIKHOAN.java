package com.example.appbandochoi.doInBackGround;

import android.os.AsyncTask;

import com.example.appbandochoi.HTTPRequest.AccountAPI;
import com.example.appbandochoi.HTTPRequest.pubVar;
import com.example.appbandochoi.entity.DSTAIKHOAN;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskDSTAIKHOAN extends AsyncTask<Object, Object, Object> {

    @Override
    protected Object doInBackground(Object... obs) {
        if(obs[0].toString().trim().equals("getTaiKhoan")) {
            return AccountAPI.getTaiKhoan(obs[1].toString());
        }
        else if(obs[0].toString().trim().equals("saveTaiKhoan")){
             AccountAPI.saveTaiKhoan((DSTAIKHOAN) obs[1]);
            return null;
        }
        else if(obs[0].toString().trim().equals("updateMK")){
            AccountAPI.updateMK((DSTAIKHOAN) obs[1]);
            return null;
        }

        return null;
    }
}
