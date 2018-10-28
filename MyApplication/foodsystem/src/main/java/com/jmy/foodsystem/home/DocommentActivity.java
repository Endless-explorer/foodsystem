package com.jmy.foodsystem.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jmy.foodsystem.R;
import com.jmy.foodsystem.order.WaitCommentActivity;
import com.jmy.model.Comment;
import com.jmy.model.RockData;

import java.text.SimpleDateFormat;

public class DocommentActivity extends Activity {
    private ImageView back;
    private EditText putcomment;
    private TextView punish;
    private RatingBar ratingBar;
    private Intent myintent;
    private Comment comment=new Comment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docomment);
        initView();
        initEven();

    }

    public void initView() {
        back = (ImageView) findViewById(R.id.back_docomment);
        punish = (TextView) findViewById(R.id.punish_docomment);
        putcomment = (EditText) findViewById(R.id.putcomment);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        myintent = getIntent();

    }

    public void initEven() {

        back.setOnClickListener(new onClickListerner());
        punish.setOnClickListener(new onClickListerner());
        ratingBar.setOnClickListener(new onClickListerner());

    }

    public class onClickListerner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back_docomment:
                    Intent in = new Intent(DocommentActivity.this, WaitCommentActivity.class);
                    startActivity(in);
                    break;
                case R.id.punish_docomment:
                    long time = System.currentTimeMillis();
                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateStr = dateformat.format(time);
                    Bundle bundle = myintent.getBundleExtra("bundle");
                    String str=putcomment.getText().toString();
                    Log.d("all1",str);
                    if(str.length()<5)
                    {
                        Toast.makeText(DocommentActivity.this,"评论不能少于5五个字！",Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                        comment.setContent(str);
                        comment.setStar((int) ratingBar.getRating());
                        comment.setImg(bundle.getInt("img"));
                        comment.setTime("时间：" +dateStr);
                        comment.setName(bundle.getString("name"));
                        new RockData().mycomments.add(comment);
                            Toast.makeText(DocommentActivity.this,"评论成功！",Toast.LENGTH_SHORT).show();
                            Intent in2 = new Intent(DocommentActivity.this, WaitCommentActivity.class);
                            in2.putExtra("myposition",bundle.getInt("myposition"));
                            startActivity(in2);
                        }
                    break;
            }
        }
    }
}




