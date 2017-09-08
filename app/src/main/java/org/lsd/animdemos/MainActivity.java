package org.lsd.animdemos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.lsd.animdemos.swipecard.SwipeCardViewActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_taobao).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, IOSTaoBaoActivity.class));
            }
        });

        findViewById(R.id.btn_swipe).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, SwipeCardViewActivity.class));
            }
        });

        findViewById(R.id.btn_draw_text).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, TestBaseLineActivity.class));
            }
        });

        findViewById(R.id.btn_scroll_list).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, ListViewInsideScrollViewActivity.class));
            }
        });

        findViewById(R.id.btn_weixin_bank).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, WeixinBankActivity.class));
            }
        });

        findViewById(R.id.btn_immerse).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this, "未完成", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
