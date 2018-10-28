package com.jmy.foodsystem.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jmy.dao.NoUseAdapter;
import com.jmy.foodsystem.R;
import com.jmy.model.NoUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaitUseActivity extends Activity {

    private ImageView back;
    private ListView  nouselist;
    private Intent myintent;
    private int[] imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_use);
        initView();
        initEven();

    }

    public void initView()
    {
        back= (ImageView) findViewById(R.id.back_waituser);
        nouselist= (ListView) findViewById(R.id.listview_waituse);
        myintent=getIntent();

    }
    public void initEven()
    {
        back.setOnClickListener(new onClickListerner());
        NoUseAdapter myadapter=new NoUseAdapter(this,getnouses());
        nouselist.setAdapter(myadapter);
        nouselist.setOnItemClickListener(new onItemClickListerner());
    }
    public class onClickListerner implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
              setResult(1,myintent);
              finish();
        }
    }

    public class onItemClickListerner implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(WaitUseActivity.this,"别点我，没有内容了",Toast.LENGTH_SHORT).show();
        }
    }

    public List<NoUser> getnouses()
    {
        imgs=new int[]{R.drawable.rdd6,R.drawable.rdd5,R.drawable.rdd1,R.drawable.rdd3,R.drawable.rdd2,
                R.drawable.rdd4,R.drawable.rdd8,R.drawable.rdd10,R.drawable.rdd9,R.drawable.rdd};
        List<NoUser> noUsers=new ArrayList<NoUser>();
        for(int i=0;i<10;i++) {
            Random random=new Random();
            int a=random.nextInt(6)+6;
            int b=random.nextInt(28)+1;
            int c=random.nextInt(4)+1;
            int d=random.nextInt(45)+34;
            NoUser noUser = new NoUser(imgs[i],"黄炖鸡米饭","2017-"+a+"-"+b,c,d);
            noUsers.add(noUser);
        }

        return noUsers;
    }
}
