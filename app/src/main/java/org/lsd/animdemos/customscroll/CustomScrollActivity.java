package org.lsd.animdemos.customscroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.lsd.animdemos.R;

public class CustomScrollActivity extends AppCompatActivity
{

    /*
    想法： 实现类似QQ的好友动态页面的下拉效果
     */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scroll);
    }
}
