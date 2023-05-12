package com.example.appbandochoi.doInBackGround;

import android.os.StrictMode;

public class IgnoreAsyn {
    public static void ignoreAsyn()
    {
        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);
    }
}
