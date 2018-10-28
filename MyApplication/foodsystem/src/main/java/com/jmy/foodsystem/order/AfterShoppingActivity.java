package com.jmy.foodsystem.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jmy.dao.AfterShoppingAdapter;
import com.jmy.foodsystem.R;
import com.jmy.model.AfterShopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AfterShoppingActivity extends Activity {
    private ImageView back;
    private ListView aftershoppinglist;
    private Intent myintent;
    private int[] imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_shopping);
        initView();
        initEven();

    }

    public void initView() {
        back = (ImageView) findViewById(R.id.back_aftershopping);
        aftershoppinglist = (ListView) findViewById(R.id.listview_aftershopping);
        myintent = getIntent();

    }

    public void initEven() {
        back.setOnClickListener(new onClickListerner());
        AfterShoppingAdapter  myadapter = new AfterShoppingAdapter(this, getaftershopppings());
        aftershoppinglist.setAdapter(myadapter);
        aftershoppinglist.setOnItemClickListener(new onItemClickListerner());
    }

    public class onClickListerner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            setResult(6, myintent);
            finish();
        }
    }

    public class onItemClickListerner implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(AfterShoppingActivity.this, "都给你退款了,还想咋样？", Toast.LENGTH_SHORT).show();
        }
    }

    public List<AfterShopping> getaftershopppings() {
        imgs = new int[]{R.drawable.rdd6, R.drawable.rdd5, R.drawable.rdd1, R.drawable.rdd3, R.drawable.rdd2,
                R.drawable.rdd4, R.drawable.rdd8, R.drawable.rdd10, R.drawable.rdd9, R.drawable.rdd};
        List<AfterShopping> afterShoppings = new ArrayList<AfterShopping>();
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int a = random.nextInt(9) + 1;
            int b = random.nextInt(65) + 5;
            AfterShopping afterShopping = new AfterShopping(imgs[i], "朵朵砂锅",a,b);
            afterShoppings.add(afterShopping);
        }

        return afterShoppings;
    }
}
