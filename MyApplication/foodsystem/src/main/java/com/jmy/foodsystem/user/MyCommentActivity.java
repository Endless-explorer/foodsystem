package com.jmy.foodsystem.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jmy.dao.CommentAdaper;
import com.jmy.foodsystem.R;
import com.jmy.model.Comment;
import com.jmy.model.RockData;

import java.util.List;

public class MyCommentActivity extends Activity {
    private ImageView back;
    private ListView mycommentlist;
    private Intent myintent;
    private List<Comment> mycomments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomment);
        initView();
        initEven();

    }

    public void initView() {
        back = (ImageView) findViewById(R.id.back_mycomment);
        mycommentlist = (ListView) findViewById(R.id.listview_mycomment);
        myintent = getIntent();

    }

    public void initEven() {

        back.setOnClickListener(new onClickListerner());
        CommentAdaper myadapter = new CommentAdaper(this, getmycomments());
        mycommentlist.setAdapter(myadapter);
        //mycommentlist.setOnItemClickListener(new onItemClickListerner());
    }

    public class onClickListerner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            setResult(4, myintent);
            finish();
        }
    }



    public List<Comment> getmycomments() {

        mycomments=new RockData().mycomments;
        if(mycomments.size()<1)
        {
            Toast.makeText(MyCommentActivity.this, "您还没有任何评论记录", Toast.LENGTH_SHORT).show();
        }


        return mycomments;
    }
}
