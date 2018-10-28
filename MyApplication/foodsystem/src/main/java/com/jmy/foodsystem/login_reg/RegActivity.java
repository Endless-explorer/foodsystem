package com.jmy.foodsystem.login_reg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jmy.foodsystem.R;
import com.jmy.model.PersonInfo;
import com.jmy.model.RockData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RegActivity extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {
    EditText username,password1,password2,email, birthday;
    RadioGroup rg;
    CheckBox singing,basketball,swiming,football,running,food,sleep,movie;
    Button subreg;
    private Intent myintent;
    private static final String state="reg";
    PersonInfo info=new PersonInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);//解除不能在主线程访问网络
        setContentView(R.layout.activity_reg);
        initView();
        initEven();

    }

    @Override//Button监听器
    public void onClick(View v) {
        addCheckbox();
        switch(v.getId()) //v为view
        {
           case R.id.subreg:
               String name=username.getText().toString();
               String pass1=password1.getText().toString();
               String pass2=password2.getText().toString();
               String birth=birthday.getText().toString();
               String emails=email.getText().toString();
               info.setName(name);
               info.setBirthdate(birth);
               info.setEmail(emails);
               if(pass1.equals(pass2)&&pass2.equals(pass1))
               {
                   if(info.getEmail().contains("@")||info.getEmail().equals("")) {
                       info.setPassword(pass1);
                       if(isReg(state,info.getName(),info.getPassword(),info.getSex(),info.getBirthdate(),info.getEmail(),info.getFavorite())) {
                           Toast.makeText(RegActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                           setResult(15,myintent);
                           finish();
                       }
                       Log.d("all1", "这是info输出信息" + info.getName() + info.getPassword() + info.getBirthdate() + info.getSex() + info.getFavorite());
                   }
                   else
                   {

                       Toast.makeText(RegActivity.this,"请输入正确的邮箱格式",Toast.LENGTH_SHORT).show();
                   }

               }
               else
               {
                   Toast.makeText(RegActivity.this,"密码不一致，请重新核对",Toast.LENGTH_SHORT).show();
               }


           break;


        }


    }

    @Override//Radiogroup监听器
    public void onCheckedChanged(RadioGroup group, int checkedId) {
       if(R.id.boy==checkedId)
       {
          info.setSex(1);
       }else if(R.id.girl==checkedId)
       {
          info.setSex(0);
       }



    }

    public void addCheckbox()
    {   StringBuffer sb=new StringBuffer();
        List<CheckBox> checkBoxList=new ArrayList<CheckBox>();
        checkBoxList.add(singing);
        checkBoxList.add(basketball);
        checkBoxList.add(swiming);
        checkBoxList.add(football);
        checkBoxList.add(running);
        checkBoxList.add(food);
        checkBoxList.add(sleep);
        checkBoxList.add(movie);

            for (CheckBox checkBox : checkBoxList) {
                if (checkBox.isChecked()) {
                    sb.append(checkBox.getText().toString() + " ");
                }
            }
            if(sb.equals("")&&"".equals(sb))
            {
                Toast.makeText(RegActivity.this,"请至少选择一个",Toast.LENGTH_SHORT).show();
            }
            else {
                info.setFavorite(sb.toString());
                Log.d("all1", "测试中" + info.getFavorite());

            }




    }
    public void initView()
    {
        //EditText
        myintent=getIntent();
        username= (EditText) findViewById(R.id.username);
        password1= (EditText) findViewById(R.id.password1);
        password2= (EditText) findViewById(R.id.password2);
        email= (EditText) findViewById(R.id.email);
        birthday= (EditText) findViewById(R.id.birthday);
        //EditText
        /********************/
        //RadioGroup
         rg=(RadioGroup)findViewById(R.id.radioGroup);
        //RadioGroup
        /********************/
        //CheckBox
        singing= (CheckBox) findViewById(R.id.singing);
        basketball= (CheckBox) findViewById(R.id.basketball);
        swiming= (CheckBox) findViewById(R.id.swiming);
        football= (CheckBox) findViewById(R.id.football);
        running= (CheckBox) findViewById(R.id.running);
        food= (CheckBox) findViewById(R.id.food);
        sleep= (CheckBox) findViewById(R.id.sleep);
        movie= (CheckBox) findViewById(R.id.movie);
        //CheckBox

        subreg= (Button) findViewById(R.id.subreg);
    }
    public void initEven()
    {
        rg.setOnCheckedChangeListener(this);
        singing.setOnClickListener(this);
        basketball.setOnClickListener(this);
        swiming.setOnClickListener(this);
        football.setOnClickListener(this);
        running.setOnClickListener(this);
        food.setOnClickListener(this);
        sleep.setOnClickListener(this);
        movie.setOnClickListener(this);
        subreg.setOnClickListener(this);
        new RockData().reginfo.add(info);
    }
    public Boolean isReg(String state,String name,String password,int sex,String birthday,String email,String favorite )
    {
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

                Log.d("all1","我进来了4");
                conn.connect();
                Log.d("all1","我进来了");
                String subdata = "state="+state+"&username=" + name
                        + "&userpass=" + password
                        +"&sex="+sex+"&birthday="+birthday
                        +"&email="+email+"&favorite="+favorite;
                Log.d("all1","这是要发送的数据:"+subdata);
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
                        return  true;

                    }
                    else
                    {
                        Log.d("all1","error");
                        return false;
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
