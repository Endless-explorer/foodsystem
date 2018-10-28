package com.jmy.dao;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jmy.model.MenuItem;
import com.jmy.model.MyItem;
import com.jmy.model.RockData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MyThread extends Thread
    {
        private String state;
       private Handler handler;
        private boolean flag;


        public MyThread(Handler handler,String state){
              this.handler=handler;
              this.state=state;

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
                    String subdata = "state=" + state;
                    Log.d("all1",subdata);
                    OutputStream out=conn.getOutputStream();
                    out.write(subdata.getBytes());
                    out.flush();
                    out.close();
                    Log.d("all1","ResponseCode1"+conn.getResponseMessage());
                    if(conn.getResponseCode()==200)
                    {
                        Log.d("all1","返回码确实为200");
                        BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String str=reader.readLine().toString();
                        Log.d("all1",str);
                        Log.d("all1","ResponseCode2"+String.valueOf(conn.getResponseCode()));
                        if(conn!=null)
                    {
                        conn.disconnect();
                    }

                        if(str!=null)
                        {
                            Log.d("all1","hahaha");
                            Gson gson = new Gson();
                            List<MenuItem> lists = gson.fromJson(str, new TypeToken<List<MenuItem>>(){}.getType());
                            RockData data=new RockData();
                            for(int i=0;i<lists.size();i++)
                            {
                                /*Log.d("all1",lists.get(i).getId()+"");
                                Log.d("all1",lists.get(i).getTitle()+"");
                                Log.d("all1",lists.get(i).getAveragestart()+"");
                                Log.d("all1",lists.get(i).getPrice()+"");
                                Log.d("all1",lists.get(i).getDownurl()+"");*/
                                MyItem item=new MyItem();
                                GetImgThread picthread=new GetImgThread(lists.get(i).getDownurl(),i,item);
                                picthread.start();
                                Log.d("all1","老子进来了");

                                Log.d("all1","MyThread中bitmap高度:");

                                item.setTitle(encode(lists.get(i).getTitle()));
                                item.setStar(lists.get(i).getAveragestart());
                                item.setPrice(lists.get(i).getPrice());
                                data.datalist.add(item);
                                /*data.datalist.get(i).setTitle(lists.get(i).getTitle());
                                data.datalist.get(i).setStar(lists.get(i).getAveragestart());
                                data.datalist.get(i).setPrice(lists.get(i).getPrice());*/

                                Log.d("all1","Title"+i+"=="+data.datalist.get(i).getTitle());
                                Log.d("all1","Star"+i+"=="+data.datalist.get(i).getStar());
                                Log.d("all1","Price"+i+"=="+data.datalist.get(i).getPrice());
                                Log.d("all1","img"+i+"=="+data.datalist.get(i).getImg());


                            }
                        }
                        else
                        {
                            Log.d("all1","error");

                        }




                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }


        public String encode(String str)  {

            try {
                str=new String(str.getBytes("gbk"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return  str;
        }
    }

