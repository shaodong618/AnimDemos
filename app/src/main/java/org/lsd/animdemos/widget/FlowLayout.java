package org.lsd.animdemos.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaodong on 2017/5/23 0023.
 */

public class FlowLayout extends ViewGroup
{
    // 用于记录每行view
    private List<List<View>> mViewsLineList = new ArrayList<>();
    // 用于记录每行的高度
    private List<Integer> mLineHeights = new ArrayList<>();

    public FlowLayout(Context context)
    {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p)
    {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams()
    {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int measureWidth = 0;
        int measureHeight = 0;
        int currWidth = 0;
        int currHeight = 0;

        int childWidth;
        int childHeight;
        int childCount = getChildCount();
        mViewsLineList.clear();
        mLineHeights.clear();
        List<View> currLineList = new ArrayList<>();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            childWidth = lp.leftMargin + lp.rightMargin + childView.getMeasuredWidth();
            childHeight = lp.topMargin + lp.bottomMargin + childView.getMeasuredHeight();

            if (currWidth + childWidth > (widthSize - paddingLeft - paddingRight)) {
                // 换行
                // 记录该行的高度和该行的views
                mLineHeights.add(currHeight);
                mViewsLineList.add(currLineList);
                // 累计到总测高度宽度中
                measureWidth = Math.max(currWidth, measureWidth);
                measureHeight += currHeight;
                // 换行后记录新行第一个元素的宽高度
                currWidth = childWidth;
                currHeight = childHeight;
                // 重新创建一个列表记录行内view
                currLineList = new ArrayList<>();
                currLineList.add(childView);
            } else {
                // 当前行的宽度累加，高度取最大值
                currWidth += childWidth;
                currHeight = Math.max(currHeight, childHeight);
                // 记录当前行内的view
                currLineList.add(childView);
            }
        }
        // 最后一行的宽高度也要累计到总的宽高度中
        measureWidth = Math.max(currWidth, measureWidth);
        measureHeight += currHeight;
        // 记录最后一行的高度和最后一行的views
        mLineHeights.add(currHeight);
        mViewsLineList.add(currLineList);
        // 把padding也计算到总宽高中
        measureWidth = measureWidth + paddingLeft + paddingRight;
        measureHeight = measureHeight + paddingTop + paddingBottom;

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : measureWidth,
                heightMode == MeasureSpec.EXACTLY ? heightSize : measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();

        // 记录当前这个view的左上点
        int currTop = 0;
        int currLeft = 0;
        int lineCount = mViewsLineList.size();
        for (int i = 0; i < lineCount; i++) {
            if (i == 0) {
                // 第一行的内容
                currTop = paddingTop;
            }
            // 取出当前行的总views
            List<View> views = mViewsLineList.get(i);
            int viewsCount = views.size();
            for (int j = 0; j < viewsCount; j++) {
                if (j == 0) {
                    // 每一行的第一内容
                    currLeft = paddingLeft;
                }
                View childView = views.get(j);
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                l = currLeft + lp.leftMargin;
                t = currTop + lp.topMargin;
                r = l + childView.getMeasuredWidth();
                b = t + childView.getMeasuredHeight();
                childView.layout(l, t, r, b);
                currLeft += lp.leftMargin + childView.getMeasuredWidth() + lp.rightMargin;
            }
            // 换行数据预处理
            currLeft = 0;
            currTop += mLineHeights.get(i);
        }
    }
}
