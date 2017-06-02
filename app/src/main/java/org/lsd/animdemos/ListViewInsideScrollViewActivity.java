package org.lsd.animdemos;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.lsd.animdemos.widget.ListViewForEmbeddingInScrollView;

import java.util.ArrayList;
import java.util.List;

public class ListViewInsideScrollViewActivity extends AppCompatActivity
{

    /*
    默认listview会获取到焦点，
    解决办法是在rootview加入以下属性
    android:descendantFocusability="blocksDescendants"
     */

    private Button btnQuestion;
    private boolean isShowQueston;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_inside_scroll_view);
        btnQuestion = (Button) findViewById(R.id.btn_question);
        btnQuestion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changeShowState();
            }
        });

        mLinearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        fillLayout();
    }

    private void changeShowState()
    {
        isShowQueston = !isShowQueston;
        fillLayout();
        if (isShowQueston) {
            btnQuestion.setText("解决问题");
        } else {
            btnQuestion.setText("重现问题");
        }
    }

    private void fillLayout()
    {
        mLinearLayout.removeAllViews();
        for (int i = 0; i < 3; i++) {
            TextView textView = new TextView(this);
            textView.setText(String.valueOf(i));
            textView.setTextSize(50);
            mLinearLayout.addView(textView);
        }
        TextView textView = new TextView(this);
        textView.setTextColor(Color.RED);
        textView.setText("下面是listview");
        textView.setTextSize(50);
        mLinearLayout.addView(textView);

        ListView listView;
        if (isShowQueston) {
            listView = new ListView(this);
        } else {
            listView = new ListViewForEmbeddingInScrollView(this);
        }
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            strings.add("item  " + i);
        }
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings));
        mLinearLayout.addView(listView);

        TextView textView1 = new TextView(this);
        textView1.setTextColor(Color.RED);
        textView1.setText("上面是listview");
        textView1.setTextSize(50);
        mLinearLayout.addView(textView1);
        for (int i = 0; i < 3; i++) {
            TextView tv = new TextView(this);
            tv.setText(String.valueOf(i));
            tv.setTextSize(50);
            mLinearLayout.addView(tv);
        }

    }
}
