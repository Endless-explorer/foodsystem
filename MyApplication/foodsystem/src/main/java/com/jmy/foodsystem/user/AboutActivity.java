package com.jmy.foodsystem.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jmy.foodsystem.R;

public class AboutActivity extends Activity {
    ImageView back;
    private Intent myintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        back= (ImageView) findViewById(R.id.back_about);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 setResult(5,myintent);
                finish();
            }
        });

    }
}
