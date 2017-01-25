package org.lsd.animdemos.swipecard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lsd.animdemos.R;

import java.util.List;

public class SwipeCardViewActivity extends AppCompatActivity
{

    private List<Datas> mDatas;
    private RecyclerView mRv;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_card_view);

        // 模拟初始化数据
        mDatas = Datas.initData();
        CardConfig.initConfig(this);
        initView();
    }

    private void initView()
    {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setAdapter(mAdapter = new MyAdapter());
//        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setLayoutManager(new SwipeCardLayoutManager());
        SwipeCardCallBack callback = new SwipeCardCallBack(mRv, mAdapter, mDatas);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRv);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return new MyViewHolder(getLayoutInflater().inflate(R.layout.item_swipe_card, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.mTvNumber.setText(mDatas.get(position).getName());
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        public TextView mTvNumber;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            mTvNumber = (TextView) itemView.findViewById(R.id.tv_number);
        }
    }
}
