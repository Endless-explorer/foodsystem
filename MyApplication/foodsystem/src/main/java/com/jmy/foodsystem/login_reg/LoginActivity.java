package com.jmy.foodsystem.login_reg;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jmy.foodsystem.R;
import com.jmy.foodsystem.home.MainActivity;
import com.jmy.model.PersonInfo;
import com.jmy.model.RockData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LoginActivity extends Activity implements View.OnClickListener{
    private Button submit,regist;
    private EditText username,password;
    private static final String state="login";
    private  EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);//解除不能在主线程访问网络
        initView();
        submit.setOnClickListener(this);
        regist.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.submit:    //这是我的登录部分代码，要连接服务器进行验证


                if(!checkNetwork())
                {
                    Toast toast = Toast.makeText(LoginActivity.this,"网络未连接", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }
                else {
                    Log.d("all1", "我进来了");
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    if (user.equals("") && "".equals(user)) {
                        Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    } else if (pass.equals("") && "".equals(pass)) {
                        Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                       /* MyThread myThread=new MyThread(user,pass,handler);
                        myThread.start();
                        while (status==null)
                        {
                            statusview.setText(status);  //无法从子线程获得返回数据，故放弃用子线程访问网络.
                        }*/
                       if(isLogin(state,user,pass))
                       {
                           Toast.makeText(LoginActivity.this, "正在登陆，请稍等。。。。。。", Toast.LENGTH_SHORT).show();
                           Intent in=new Intent(LoginActivity.this,MainActivity.class);
                           startActivity(in);
                       }
                       else
                       {
                           Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                       }


                    }
                }

                break;

            case R.id.regist:  //跳转到注册页面

                Intent in=new Intent(LoginActivity.this,RegActivity.class);
                startActivityForResult(in,15);
            break;

          default:

          break;

        }

    }
    public void initView()
    {
        submit= (Button) findViewById(R.id.submit);
        regist= (Button) findViewById(R.id.regist);
        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        final EditText edit=new EditText(LoginActivity.this);
        AlertDialog.Builder mydialog=new AlertDialog.Builder(LoginActivity.this);
        mydialog.setTitle("请输入服务器IP").setView(edit);
        mydialog.setPositiveButton("确定",new DialogInterface.OnClickListener()
                 {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {

                         new RockData().ip= edit.getText().toString();
                         Toast.makeText(LoginActivity.this,"设置的IP为:"+new RockData().ip,Toast.LENGTH_SHORT).show();


                     }
                 }

                );
                mydialog.show();

            }


    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
    private  boolean isLogin(String state,String name,String password)
    {
        String path="http://"+new RockData().ip+":8080/EX10/servlet/WebServlet";
        try {
            URL url=new URL(path);
            try {
                Log.d("all1","我进来了1");
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                Log.d("all1","服务器的连接"+conn.toString());
                Log.d("all1","我进来了2");
                conn.setRequestMethod("POST");
                Log.d("all1","我进来了3");
                conn.setReadTimeout(5*1000);
                conn.setConnectTimeout(5*1000);
                conn.setDoOutput(true);

                Log.d("all1","我进来了4");
                conn.connect();
                Log.d("all1","我进来了");
                String subdata ="state="+state+"&username=" + name
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
                    String str=reader.readLine().toString();

                    if(conn!=null)
                    {
                        conn.disconnect();
                    }

                    if(str!=null)
                    {

                        Gson gson = new Gson();
                        List<PersonInfo> lists = gson.fromJson(str, new TypeToken<List<PersonInfo>>(){}.getType());
                        PersonInfo info=new PersonInfo();

                        info.setName(lists.get(0).getName());
                        Log.d("all1","info.getName()="+info.getName());
                        info.setPassword(lists.get(0).getPassword());
                        Log.d("all1","info.getPassword()="+info.getPassword());
                        info.setSex(lists.get(0).getSex());
                        info.setBirthdate(lists.get(0).getBirthdate());
                        info.setEmail(lists.get(0).getEmail());
                        info.setFavorite(lists.get(0).getFavorite());
                        new RockData().reginfo.add(info);

                        if(password.equals(info.getPassword()))
                        {
                            return true;
                        }
                        else
                        {
                            Log.d("all1","error");
                            return false;

                        }

                    }



                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }

    }




