package com.jmy.foodsystem.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jmy.dao.CommentAdaper;
import com.jmy.foodsystem.R;
import com.jmy.model.Collection;
import com.jmy.model.Comment;
import com.jmy.model.RockData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonStarActivity extends Activity {
    private TextView personprice;
    private ImageView backindex;
    private ImageView keep;
    private ImageView buynow;
    private int[] imgs;
    private LayoutInflater minflater;
    private ListView commentlist;
    private Intent in;
    private int position;
    private int statu=0;
    private Bundle bundle;
    List<Collection> collections;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_star);
        Log.d("all1","PersonStarActivity的测试begin");
        initView();
        initImg();
        Log.d("all1","PersonStarActivity的测试inview没问题");
        initEven();
        initData();


    }

    public void initView()
    {
        personprice= (TextView) findViewById(R.id.personprice);
        backindex= (ImageView) findViewById(R.id.backindex);
        keep= (ImageView) findViewById(R.id.keep);
        buynow= (ImageView) findViewById(R.id.buynow);
        commentlist= (ListView) findViewById(R.id.commentlist);

    }
    public void initEven()
    {
        in=getIntent();
        bundle=in.getBundleExtra("bundle");
        Long price=bundle.getLong("price");
        personprice.setText("¥"+price);
        backindex.setOnClickListener(new Onclick());
        keep.setOnClickListener(new Onclick());
        buynow.setOnClickListener(new Onclick());
        minflater=LayoutInflater.from(this);
        CommentAdaper commentAdaper=new CommentAdaper(this,getcomments());
        commentlist.setAdapter(commentAdaper);
        commentlist.setOnItemClickListener(new Onlistclick());
    }

   public void initData()
    {
        LinearLayout inside;
        inside= (LinearLayout) findViewById(R.id.inside);
        ImageView insideimage;

            for (int i = 0; i < imgs.length; i++) {
                View view = minflater.inflate(R.layout.horscrollitem, inside, false);
                insideimage = (ImageView) view.findViewById(R.id.insideimage);
                insideimage.setImageResource(imgs[i]);
                inside.addView(view);
        }
    }
       public void initImg()
       {
           imgs=new int[]{R.drawable.rdd,R.drawable.rdd1,R.drawable.rdd2,R.drawable.rdd3,
               R.drawable.rdd4,R.drawable.rdd5,R.drawable.rdd6};
       }
       public List<Comment> getcomments()

       {

           List<Comment> comments=new ArrayList<Comment>();
           for(int i=1;i<10;i++) {
               Random random=new Random();
               int a=random.nextInt(11)+1;
               int b=random.nextInt(28)+1;
               int c=random.nextInt(4)+1;
               int d=random.nextInt(1);
               Comment comment = new Comment(R.mipmap.man,"小王","2017-"+a+"-"+b,c,"这次总体上感觉不错就是味道有点辣，吃的不是很习惯，但是还过得去呢，加油",d);
               comments.add(comment);
           }

           return comments;
       }

    public class Onclick implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
              switch(v.getId()) //v为view
              {
                  case R.id.backindex:
                      backindex.setImageResource(R.mipmap.back_gray);
                      Log.d("all1","这边的statu="+statu);
                      in.putExtra("love",statu);
                      setResult(0,in);
                      finish();


                  break;

                  case R.id.keep:
                      if(statu==0)
                      {
                          collections=new RockData().collections;
                          Random random=new Random();
                          int dist=random.nextInt(500)+50;
                          Collection collection=new Collection();
                          collection.setName(bundle.getString("name"));
                          collection.setStar(bundle.getInt("mystar"));
                          collection.setImg((Bitmap) bundle.getParcelable("img"));
                          Log.d("all1","这边的name="+bundle.getString("name"));
                          Log.d("all1","这边的star="+bundle.getInt("mystar"));
                          Log.d("all1","这边的img.height="+ ((Bitmap) bundle.getParcelable("img")).getHeight());
                          collection.setDistance(dist);
                          collection.setLocation("萍乡学院");
                          collections.add(collection);
                          Toast.makeText(PersonStarActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                          keep.setImageResource(R.mipmap.love_red);
                          statu = 1;

                      }
                      else
                      {
                          position=bundle.getInt("position");
                          collections.remove(position);
                          Toast.makeText(PersonStarActivity.this,"取消收藏",Toast.LENGTH_SHORT).show();
                          keep.setImageResource(R.mipmap.love);
                          statu = 0;

                      }

                      break;

                  case R.id.buynow:

                      break;

                default:

                break;

              }
        }
    }
    public class Onlistclick implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
}
