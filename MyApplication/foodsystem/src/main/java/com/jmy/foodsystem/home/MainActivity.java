package com.jmy.foodsystem.home;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jmy.dao.MenuAdapter;
import com.jmy.dao.MyThread;
import com.jmy.dao.NoPayAdapter;
import com.jmy.dao.PullParser;
import com.jmy.foodsystem.R;
import com.jmy.foodsystem.order.AfterShoppingActivity;
import com.jmy.foodsystem.order.WaitCommentActivity;
import com.jmy.foodsystem.order.WaitUseActivity;
import com.jmy.foodsystem.user.AboutActivity;
import com.jmy.foodsystem.user.CollectionActivity;
import com.jmy.foodsystem.user.MyCommentActivity;
import com.jmy.model.Division;
import com.jmy.model.MyItem;
import com.jmy.model.NoPay;
import com.jmy.model.PersonInfo;
import com.jmy.model.RockData;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class MainActivity extends Activity  {

    /*MainActivity开头*/
    private boolean isloadorderview=false;
    private boolean isloadUserview=false;
    private boolean isloaddata=false;
    private ImageView index;
    private ImageView order;
    private ImageView user;
    private Spinner spinner;
    private ListView memulist;
    private TextView city;
    private View mainview;
    private List<MyItem> list=new ArrayList<MyItem>();
    List<Map<String, Object>> dataList = null;
    List<String> cityids=new ArrayList<String>();
    List<String> citynames=new ArrayList<String>();
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("all1","传入了消息1");


                    break;
                case 0:


                    break;
                default:
                    break;
            }
        }

    };
    /*MainActivity结尾*/


    /*MyOrderActivity开头*/
    private ImageView index2;
    private ImageView order2;
    private ImageView user2;
    private ImageView waitpay;
    private ImageView waituse;
    private ImageView waitcomment;
    private ImageView aftershoping;
    private LayoutInflater minflater;
    private ListView waitpayitem;
    private int[] imgs;
    private View orderview;
    /*MyOrderActivity结尾*/


     /*MyUserActivity开头*/
     private ImageView index3;
    private ImageView order3;
    private ImageView user3;
    private ImageView centerseximg;
    private TextView  centernametop;
    private TextView  centername;
    private TextView  centersex;
    private TextView  centerbirthday;
    private TextView  centeremail;
    private TextView  centerhobby;
    private LinearLayout personshou;
    private LinearLayout personping;
    private View userview;
    private LinearLayout about;

     /*MyUserActivity结尾*/


    NoPayAdapter noPayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(MainActivity.this,"正在加载网络数据请稍等",Toast.LENGTH_SHORT).show();
        if(isloaddata==false)
        {   Log.d("all1","执行了一次");
            new MyThread(handler, "menulist").start();
            isloaddata=true;
        }

        minflater=LayoutInflater.from(this);
        mainview=minflater.inflate(R.layout.activity_main,null);
        orderview=minflater.inflate(R.layout.activity_my_order,null);
        userview=minflater.inflate(R.layout.activity_user,null);
        setContentView(mainview);
        Log.d("all1","进来了");
        initView();
        initEvent();
        initSpinner();
        initMenulist();
        initImg();
       noPayAdapter=new NoPayAdapter(this,getnopays());


    }
    //初始化控件
    public void initView()
    {   /*MainActivity开头*/

        spinner= (Spinner) findViewById(R.id.spinner);
        memulist= (ListView) findViewById(R.id.menulist);
        city= (TextView) findViewById(R.id.city);
       index= (ImageView) findViewById( R.id.index1);
        order= (ImageView) findViewById(R.id.order1);
        user= (ImageView) findViewById(R.id.user1);
        /*MainActivity结尾*/


        /*MyOrderActivity开头*/


    /*MyOrderActivity结尾*/



    }
    //初始化事件
    public void initEvent()
    {
        index.setOnClickListener(new Onclick());
        order.setOnClickListener(new Onclick());
        user.setOnClickListener(new Onclick());
        index.setImageResource(R.mipmap.home_green);




    }
    //监听事件实现类
    public class Onclick  implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            switch(v.getId()) //v为view
            {


                case R.id.order1:
                    Log.d("all1","进了MyOrderActivity");
                    setContentView(orderview);
                    if(!isloadorderview)
                    {
                        index2= (ImageView) orderview.findViewById(R.id.index2);
                        user2= (ImageView) orderview.findViewById(R.id.user2);
                        order2= (ImageView) orderview.findViewById(R.id.order2);
                        order2.setImageResource(R.mipmap.shopping_green);
                        waitpay= (ImageView) orderview.findViewById(R.id.waitpay);
                        waitpay.setImageResource(R.mipmap.waitpay_greem);
                        waituse= (ImageView) orderview.findViewById(R.id.waituse);
                        waitcomment= (ImageView) orderview.findViewById(R.id.pingjia);
                        aftershoping= (ImageView) orderview.findViewById(R.id.aftershopping);
                        waituse.setOnClickListener(new Onclick());     //order内部的点击事件
                        waitcomment.setOnClickListener(new Onclick());   //order内部的点击事件
                        aftershoping.setOnClickListener(new Onclick());    //order内部的点击事件
                        waitpayitem= (ListView) orderview.findViewById(R.id.waitpaylist);
                        index2.setOnClickListener(new Onclick());
                        user2.setOnClickListener(new Onclick());
                        waitpay.setOnClickListener(new Onclick());
                        waituse.setOnClickListener(new Onclick());
                        waitcomment.setOnClickListener(new Onclick());
                        aftershoping.setOnClickListener(new Onclick());
                        waitpayitem.setAdapter(noPayAdapter);
                        isloadorderview=true;
                    }


                    Log.d("all1","初始化了控件");
                    break;

                case R.id.user1:
                    Log.d("all1","进了MyUserActivity");
                    setContentView(userview);
                        if(!isloadUserview)
                        {
                            index3= (ImageView) findViewById(R.id.index3);
                            order3= (ImageView) findViewById(R.id.order3);
                            user3= (ImageView) findViewById(R.id.user3);
                            user3.setImageResource(R.mipmap.user_green);
                            centernametop= (TextView) findViewById(R.id.centernametop);
                            centerseximg= (ImageView) findViewById(R.id.centerseximg);
                            centername= (TextView) findViewById(R.id.centername);
                            centersex= (TextView) findViewById(R.id.centersex);
                            centerbirthday= (TextView) findViewById(R.id.centerbirthday);
                            centeremail= (TextView) findViewById(R.id.centeremail);
                            centerhobby= (TextView) findViewById(R.id.centerhobby);
                            PersonInfo info;
                             if(new RockData().reginfo!=null)
                            {
                                info=new RockData().reginfo.get(0);
                                if(info.getSex()==1)
                                {
                                    centersex.setText("性别:男");
                                    centerseximg.setImageResource(R.mipmap.male);
                                }
                                else
                                {
                                    centersex.setText("性别:女");
                                    centerseximg.setImageResource(R.mipmap.female);
                                }
                                centernametop.setText(info.getName());
                                centername.setText("姓名:"+info.getName());
                                centerbirthday.setText("出生日期:"+info.getBirthdate());
                                centeremail.setText("电子邮箱:"+info.getEmail());
                                centerhobby.setText("兴趣爱好:"+info.getFavorite());
                            }
                            else
                            {
                                Log.d("all1","new RockData().reginfo有问题啊");
                            }

                            personshou= (LinearLayout) findViewById(R.id.myshou);
                            personping= (LinearLayout) findViewById(R.id.ping);
                            about= (LinearLayout) findViewById(R.id.guanyu);
                            index3.setOnClickListener(new Onclick());
                            order3.setOnClickListener(new Onclick());
                            personshou.setOnClickListener(new Onclick());
                            personping.setOnClickListener(new Onclick());
                            about.setOnClickListener(new Onclick());
                        }
                    break;

                default:

                    break;



                case R.id.index2:
                    Log.d("all1","进了mainActivity");
                    setContentView(mainview);


                    Log.d("all1","初始化了控件");
                    break;
                case R.id.user2:
                    Log.d("all1","进了MyUserActivity");
                    setContentView(userview);
                    if(!isloadUserview)
                    {
                        index3= (ImageView) findViewById(R.id.index3);
                        order3= (ImageView) findViewById(R.id.order3);
                        user3= (ImageView) findViewById(R.id.user3);
                        user3.setImageResource(R.mipmap.user_green);
                        centernametop= (TextView) findViewById(R.id.centernametop);
                        centerseximg= (ImageView) findViewById(R.id.centerseximg);
                        centername= (TextView) findViewById(R.id.centername);
                        centersex= (TextView) findViewById(R.id.centersex);
                        centerbirthday= (TextView) findViewById(R.id.centerbirthday);
                        centeremail= (TextView) findViewById(R.id.centeremail);
                        centerhobby= (TextView) findViewById(R.id.centerhobby);
                        PersonInfo info;
                        if(new RockData().reginfo!=null)
                        {
                            info=new RockData().reginfo.get(0);
                            if(info.getSex()==1)
                            {
                                centersex.setText("性别:男");
                                centerseximg.setImageResource(R.mipmap.male);
                            }
                            else
                            {
                                centersex.setText("性别:女");
                                centerseximg.setImageResource(R.mipmap.female);
                            }
                            Log.d("all1","infoname="+info.getName());
                            centernametop.setText(info.getName());
                            centername.setText("姓名:"+info.getName());
                            centerbirthday.setText("出生日期:"+info.getBirthdate());
                            centeremail.setText("电子邮箱:"+info.getEmail());
                            centerhobby.setText("兴趣爱好:"+info.getFavorite());
                        }
                        else
                        {
                            Log.d("all1","new RockData().reginfo有问题啊");
                        }
                        personshou= (LinearLayout) findViewById(R.id.myshou);
                        personping= (LinearLayout) findViewById(R.id.ping);
                        about= (LinearLayout) findViewById(R.id.guanyu);
                        index3.setOnClickListener(new Onclick());
                        order3.setOnClickListener(new Onclick());
                        personshou.setOnClickListener(new Onclick());
                        personping.setOnClickListener(new Onclick());
                        about.setOnClickListener(new Onclick());
                    }
                    break;
                case R.id.index3:
                    Log.d("all1","进了mainActivity");
                    setContentView(mainview);
                    break;


                case R.id.order3:
                    Log.d("all1","进了MyOrderActivity");
                    setContentView(orderview);
                    if(!isloadorderview)
                    {
                        index2= (ImageView) orderview.findViewById(R.id.index2);
                        user2= (ImageView) orderview.findViewById(R.id.user2);
                        order2= (ImageView) orderview.findViewById(R.id.order2);
                        order2.setImageResource(R.mipmap.shopping_green);
                        waitpay= (ImageView) orderview.findViewById(R.id.waitpay);
                        waituse= (ImageView) orderview.findViewById(R.id.waituse);
                        waitcomment= (ImageView) orderview.findViewById(R.id.pingjia);
                        aftershoping= (ImageView) orderview.findViewById(R.id.aftershopping);
                        waitpayitem= (ListView) orderview.findViewById(R.id.waitpaylist);
                        index2.setOnClickListener(new Onclick());
                        user2.setOnClickListener(new Onclick());
                        waitpay.setOnClickListener(new Onclick());
                        waituse.setOnClickListener(new Onclick());
                        waitcomment.setOnClickListener(new Onclick());
                        aftershoping.setOnClickListener(new Onclick());
                        waitpayitem.setAdapter(noPayAdapter);
                        isloadorderview=true;
                    }
                    break;
                case R.id.myshou:
                    Intent in3=new Intent(MainActivity.this,CollectionActivity.class);
                    startActivityForResult(in3,3);
                    break;
                case R.id.ping:
                    Intent in4=new Intent(MainActivity.this,MyCommentActivity.class);
                    startActivityForResult(in4,4);
                    break;
                case R.id.guanyu:
                    Intent in5=new Intent(MainActivity.this,AboutActivity.class);
                    startActivityForResult(in5,5);
                    break;
                case R.id.waituse:
                    Intent in1=new Intent(MainActivity.this, WaitUseActivity.class);
                    startActivityForResult(in1,1);
                    break;
                case R.id.pingjia:
                    Intent in2=new Intent(MainActivity.this, WaitCommentActivity.class);
                    startActivityForResult(in2,2);
                    break;
                case R.id.aftershopping:
                    Intent in6=new Intent(MainActivity.this, AfterShoppingActivity.class);
                    startActivityForResult(in6,6);





            }


        }
    }
    //加载listview
    public void initMenulist()
    {

        Log.d("all1","进来測試網絡請求");

        final MenuAdapter adapter=new MenuAdapter(new RockData().datalist,MainActivity.this);
        memulist.setAdapter(adapter);
        memulist.smoothScrollToPosition(0);
        memulist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("all1","position="+position);
                Bundle bundle=new Bundle();
                long price=new RockData().datalist.get(position).getPrice();
                String name=new RockData().datalist.get(position).getTitle();
                Bitmap img=new RockData().datalist.get(position).getImg();
                int mystar=new RockData().datalist.get(position).getStar();
                Log.d("all1","price="+price);
                bundle.putString("name",name);
                bundle.putLong("price",price);
                bundle.putParcelable("img",img);
                bundle.putInt("position",position);
                bundle.putInt("mystar",mystar);
                Intent in=new Intent(MainActivity.this,PersonStarActivity.class);
                in.putExtra("bundle",bundle);
                startActivityForResult(in,0);
            }
        });
    }

    //加载spiner
    public void initSpinner()
    {
        List<Map<String, Object>> dataList = null;
        PullParser pullParser=new PullParser();
        AssetManager manager=getAssets();
        Log.d("all1","进来了1");
        try {
            InputStream in=manager.open("citys.xml");
            Log.d("all1",in.available()+"");
            List<Division> divisions=pullParser.parserXml(in);
            Log.d("all1",""+divisions.size());
            for(Division division:divisions)
            {
                /*Log.d("all1",division.getId());
                Log.d("all1",division.getName());*/

                citynames.add(division.getName());
                cityids.add(division.getId());
            }
            Log.d("all1","进来了2s");
            final ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item ,citynames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    city.setText(adapter.getItem(position).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinner.setDropDownVerticalOffset(30);
            spinner.setAdapter(adapter);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<NoPay> getnopays()

    {

        List<NoPay> nopays =new ArrayList<NoPay>();
        for(int i=0;i<imgs.length;i++) {
            Random random=new Random();
            int a=random.nextInt(11)+1;
            int b=random.nextInt(28)+1;
            int c=random.nextInt(68)+15;
            NoPay noPay=new NoPay(imgs[i],"新疆大鸡排","2017-"+a+"-"+b,c+"");
            nopays.add(noPay);
        }

        return nopays;
    }

    public void initImg()
    {
        imgs=new int[]{R.drawable.rdd,R.drawable.rdd1,R.drawable.rdd2,R.drawable.rdd3,
                R.drawable.rdd4,R.drawable.rdd5,R.drawable.rdd6};
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 0:
                int love=data.getExtras().getInt("love");
                Log.d("all1","这是personstaractivity传过来的参数flag="+love);
                break;
            case 1:
                Log.d("all1","宝宝是waituse,宝宝回来了");
                break;

            case 2:
                Log.d("all1","宝宝是waitcomment,宝宝回来了");
                break;
            case 3:
                Log.d("all1","宝宝是收藏夹,宝宝回来了");
                break;
            case 4:
                Log.d("all1","宝宝是我的评论,宝宝回来了");
                break;
            case 5:
                Log.d("all1","宝宝是关于我们,宝宝回来了");
                break;
            case 6:
                Log.d("all1","宝宝是aftershopping,宝宝回来了");
                break;
        }
    }
}
