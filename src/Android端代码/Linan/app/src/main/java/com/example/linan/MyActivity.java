package com.example.linan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * “我的”活动
 */
public class MyActivity extends AppCompatActivity {
    ImageView iv_suggest;
    ImageView iv_announce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        iv_suggest = (ImageView)findViewById(R.id.iv_suggest);
        iv_announce = (ImageView)findViewById(R.id.iv_announce);
        iv_suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        iv_announce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this,AnnounceActivity.class);
                startActivity(intent);
            }
        });
    }
}
