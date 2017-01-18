package org.lsd.animdemos;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class IOSTaoBaoActivity extends AppCompatActivity implements View.OnClickListener
{

    private View mContentView;
    private PopupWindow mPopupWindow;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iostao_bao);

        // 设置背景色为黑色
        View decorView = getWindow().getDecorView();
        decorView.setBackgroundColor(Color.BLACK);
        mContentView = findViewById(android.R.id.content);

        initPopWindow();

        findViewById(R.id.rela).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.rela:

                // 在底部显示
                mPopupWindow.showAtLocation(mContentView, Gravity.BOTTOM, 0, 0);
                //设置其他地方变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.4f;
                getWindow().setAttributes(lp);

                // contentView的动画
                startContentViewAnim(false);

                break;
            case R.id.btn_cancel:
                mPopupWindow.dismiss();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private void startContentViewAnim(boolean isSmall)
    {
        // 设置旋转的中心点
        float pivotX = mContentView.getWidth() / 2.0f;
        float pivotY = mContentView.getHeight();
        mContentView.setPivotX(pivotX);
        mContentView.setPivotY(pivotY);
        final ViewPropertyAnimator animate = mContentView.animate();
        if (isSmall)
        {
            // 变大
            final android.view.ViewPropertyAnimator viewPropertyAnimator = animate
                    .rotationX(5f).scaleX(1.0f).scaleY(1.0f).y(0);
            viewPropertyAnimator.setListener(new android.animation.AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(android.animation.Animator animation)
                {
                    animate.rotationX(0);
                    viewPropertyAnimator.setListener(null);
                }
            });
        }
        else
        {
            // 缩小
            final ViewPropertyAnimator viewPropertyAnimator = animate
                    .rotationX(5);
            viewPropertyAnimator.setListener(new android.animation.AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(android.animation.Animator animation)
                {
                    animate.rotationX(0).scaleX(0.85f).scaleY(0.85f).y(-(mContentView.getHeight() * 0.15f / 2.0f));
                    viewPropertyAnimator.setListener(null);
                }
            });
        }
    }

    private void initPopWindow()
    {
        View view = getLayoutInflater().inflate(R.layout.item_bottom, null);
        view.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mPopupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setAnimationStyle(R.style.mypopwindow_anim_style_slide);
        //popWindow消失监听方法
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {
            @Override
            public void onDismiss()
            {
                startContentViewAnim(true);
                //当消失后复原变暗的部分
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }
}
