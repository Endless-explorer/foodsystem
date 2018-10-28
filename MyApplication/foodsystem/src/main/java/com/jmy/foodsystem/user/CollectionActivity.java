package com.jmy.foodsystem.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jmy.dao.CollectionAdapter;
import com.jmy.foodsystem.R;
import com.jmy.model.Collection;
import com.jmy.model.RockData;

import java.util.List;

public class CollectionActivity extends Activity {
    private ImageView back;
    private ListView collectionlist;
    private Intent myintent;
    private List<Collection> collections;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initView();
        initEven();

    }

    public void initView() {
        back = (ImageView) findViewById(R.id.back_collection);
        collectionlist = (ListView) findViewById(R.id.listview_collection);
        myintent = getIntent();

    }

    public void initEven() {

        back.setOnClickListener(new onClickListerner());
        CollectionAdapter myadapter = new CollectionAdapter(this, getcollections());
        collectionlist.setAdapter(myadapter);
        collectionlist.setOnItemClickListener(new onItemClickListerner());
    }

    public class onClickListerner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            setResult(3, myintent);
            finish();
        }
    }

    public class onItemClickListerner implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(CollectionActivity.this, "暂不支持取消收藏功能哦", Toast.LENGTH_SHORT).show();
        }
    }

    public List<Collection> getcollections() {

             collections=new RockData().collections;
        if(collections==null)
        {
            Toast.makeText(CollectionActivity.this, "您还没有收藏任何店铺哦", Toast.LENGTH_SHORT).show();
        }


        return collections;
    }
}
