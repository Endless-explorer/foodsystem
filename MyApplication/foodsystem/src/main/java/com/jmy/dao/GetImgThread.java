package com.jmy.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;

import com.jmy.model.MyItem;
import com.jmy.model.RockData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetImgThread extends Thread
    {
        private String downurl;
        private static String state="downimg";
        private int position;
        private MyItem item;



        public GetImgThread(String downurl,int position,MyItem item){
              this.downurl=downurl;
              this.position=position;
              this.item=item;



        }


        @Override
        public void run() {
            Message msg=new Message();
            String path="http://"+new RockData().ip+":8080/EX10/servlet/WebServlet";
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
                    conn.setDoInput(true);

                    Log.d("all1","我进来了4");
                    conn.connect();
                    Log.d("all1","我进来了");
                    String subdata = "state=" + state
                            +"&url="+downurl
                            +"&position="+position;
                    Log.d("all1",subdata);
                    OutputStream out=conn.getOutputStream();
                    out.write(subdata.getBytes());
                    out.flush();
                    out.close();
                    Log.d("all1","ResponseCode1"+conn.getResponseMessage());
                    if(conn.getResponseCode()==200)
                    {
                        Log.d("all1","GetImgThread返回码确实为200");
                        InputStream in=conn.getInputStream();

                        Log.d("all1","输入流的大小:"+in.available());



                        Bitmap bitmap = BitmapFactory.decodeStream(new PatchInputStream(in));



                        //Log.d("all1",bitmap.toString());
                        Log.d("all1","ResponseCode2"+String.valueOf(conn.getResponseCode()));
                        if(conn!=null)
                        {
                            conn.disconnect();
                        }

                        if(bitmap!=null)
                        {
                           item.setImg(bitmap);

                            Log.d("all1","bitmap赋值成功");
                            Log.d("all1","bitmap高度:"+bitmap.getHeight());

                        }
                        else
                        {
                            Log.d("all1","bitmap是空的");

                        }




                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }