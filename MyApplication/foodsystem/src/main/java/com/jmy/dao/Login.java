package com.jmy.dao;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by johnseg on 2017/5/10.
 */

public class Login {
    private  String name;
    private  String password;
    private static  Boolean flag;
    public Login(String name,String password)
    {
        this.name=name;
        this.password=password;
    }
    public Boolean isLogin()
    {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //begin now
                String path="http://192.168.1.102:8080/EX10/servlet/WebServlet";
                try {
                    URL url=new URL(path);
                    try {
                        Log.d("all1","我进来了1");
                        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                        Log.d("all1","我进来了2");
                        conn.setRequestMethod("POST");
                        Log.d("all1","我进来了3");
                        conn.setReadTimeout(5*1000);
                        conn.setConnectTimeout(5*1000);
                        conn.setDoOutput(true);

                        Log.d("all1","我进来了4");
                        conn.connect();
                        Log.d("all1","我进来了");
                        String subdata = "username=" + name
                                + "&userpass=" + password;
                        Log.d("all1",subdata);
                        OutputStream out=conn.getOutputStream();
                        out.write(subdata.getBytes());
                        out.flush();
                        out.close();
                        Log.d("all1",conn.getResponseMessage());
                        if(conn.getResponseCode()==200)
                        {
                            BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            Log.d("all1",String.valueOf(conn.getResponseCode()));

                            if(reader.readLine().equals("success"))
                            {
                                Log.d("all1","成功了！");
                                flag=true;

                            }
                            else
                            {
                                Log.d("all1","error");
                                flag=false;
                            }




                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
                //ending now

        }).start();
        return flag;

    }
}
