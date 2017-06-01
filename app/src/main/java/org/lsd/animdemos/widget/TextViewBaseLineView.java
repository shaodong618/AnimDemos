package org.lsd.animdemos.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import org.lsd.animdemos.R;

public class TextViewBaseLineView extends View
{
    /*
    根据已知的中心点，画文本居中
    重点在于计算文本的baseline
     */

    private Paint mPaint;

    public TextViewBaseLineView(Context context)
    {
        this(context, null);
    }

    private void init()
    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public TextViewBaseLineView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.LTGRAY);

        int i = getWidth() / 2;

        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(180);
        mPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baselineY = i + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float topY = baselineY + fontMetrics.top;
        float bottomY = baselineY + fontMetrics.bottom;

        canvas.drawText("你好中国", i, baselineY, mPaint);

        // 画baseline线
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, baselineY, getWidth(), baselineY, mPaint);
        String baselineStr = "baseline";
        mPaint.setTextSize(35);
        mPaint.setTextAlign(Paint.Align.LEFT);
        float textWidth = mPaint.measureText(baselineStr);
        canvas.drawText(baselineStr, getWidth() - textWidth, baselineY, mPaint);
        // 画top线
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, topY, getWidth(), topY, mPaint);
        String topStr = "top线";
        mPaint.setTextSize(35);
        mPaint.setTextAlign(Paint.Align.LEFT);
        textWidth = mPaint.measureText(topStr);
        canvas.drawText(topStr, getWidth() - textWidth, topY, mPaint);
        // 画bottom线
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, bottomY, getWidth(), bottomY, mPaint);
        String bottomStr = "bottom线";
        mPaint.setTextSize(35);
        mPaint.setTextAlign(Paint.Align.LEFT);
        textWidth = mPaint.measureText(bottomStr);
        canvas.drawText(bottomStr, getWidth() - textWidth, bottomY, mPaint);

        // 画文字说明
        mPaint.setColor(Color.BLACK);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setTextSize(35);
        float top = mPaint.getFontMetrics().top;
        canvas.drawText("红色点是整个view的中心点", 0, 0 - top, mPaint);
        // 画中心点
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        canvas.drawPoint(i, i, mPaint);
    }
}
