package com.jmy.foodsystem.order;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.jmy.dao.NoCommentAdapter;
import com.jmy.foodsystem.R;
import com.jmy.foodsystem.home.MainActivity;
import com.jmy.model.NoComment;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class WaitCommentActivity extends Activity {

    private ImageView back;
    private ListView nocommentlist;
    private Intent myintent;
    private int[] imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_comment);
        initView();
        initEven();

    }

    public void initView() {
        back = (ImageView) findViewById(R.id.back_waitcomment);
        nocommentlist = (ListView) findViewById(R.id.listview_waitcomment);
        myintent = getIntent();

    }

    public void initEven() {
        back.setOnClickListener(new onClickListerner());
        NoCommentAdapter myadapter = new NoCommentAdapter(this, getnocomments());
        nocommentlist.setAdapter(myadapter);
        nocommentlist.setOnItemClickListener(new onItemClickListerner());
    }

    public class onClickListerner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(WaitCommentActivity.this, MainActivity.class);
            setResult(10,intent);
            finish();
        }
    }

    public class onItemClickListerner implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(WaitCommentActivity.this, "别点我，没有内容了", Toast.LENGTH_SHORT).show();
        }
    }

    public List<NoComment> getnocomments() {
        imgs = new int[]{R.drawable.rdd6, R.drawable.rdd5, R.drawable.rdd1, R.drawable.rdd3, R.drawable.rdd2,
                R.drawable.rdd4, R.drawable.rdd8, R.drawable.rdd10, R.drawable.rdd9, R.drawable.rdd};
        List<NoComment> noComments = new ArrayList<NoComment>();
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int a = random.nextInt(5) + 1;
            int b = random.nextInt(28) + 1;
            int c = random.nextInt(23) + 1;
            int d = random.nextInt(59) + 1;
            NoComment noComment = new NoComment(imgs[i], "朵朵砂锅", "2017-" + a + "-" + b + "  " + c + ":" + d + "下单");
            noComments.add(noComment);
        }
        int position;
         if ((position=myintent.getIntExtra("myposition",-1))!=-1)
         {
             noComments.remove(position);
         }
        return noComments;
    }
}
