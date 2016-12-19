package com.geyek.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiHuan on 2016-12-18.
 */
public class GeyekChartView extends View {

    private BaseGround mBackground;
    private BaseGround mForeground;
    private List<BaseChart> mChartList = new ArrayList<>();

    private int mHeight;
    private int mWidth;

    private int mPaddingLeft;
    private int mPaddingTop;
    private int mPaddingRight;
    private int mPaddingBottom;

    private int mLeftTagWidth;
    private int mTopTagHeight;
    private int mRightTagWidth;
    private int mBottomTagHeight;

    public GeyekChartView(Context context) {
        this(context, null);
    }

    public GeyekChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GeyekChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*public GeyekChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();

        if (mBackground != null) {
            mBackground.setStarX(mPaddingLeft + mLeftTagWidth);
            mBackground.setStartY(mPaddingTop + mTopTagHeight);
            mBackground.setEndX(mWidth - mPaddingRight - mRightTagWidth);
            mBackground.setEndY(mHeight - mPaddingBottom - mBottomTagHeight);
        }

        if (mChartList != null) {
            for (BaseChart chart : mChartList) {
                chart.setStarX(mPaddingLeft + mLeftTagWidth);
                chart.setStartY(mPaddingTop + mTopTagHeight);
                chart.setEndX(mWidth - mPaddingRight - mRightTagWidth);
                chart.setEndY(mHeight - mPaddingBottom - mBottomTagHeight);
            }
        }

        if (mForeground != null) {
            mForeground.setStarX(mPaddingLeft + mLeftTagWidth);
            mForeground.setStartY(mPaddingTop + mTopTagHeight);
            mForeground.setEndX(mWidth - mPaddingRight - mRightTagWidth);
            mForeground.setEndY(mHeight - mPaddingBottom - mBottomTagHeight);
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBackground != null) {
            mBackground.onDraw(canvas);
        }

        if (mChartList != null) {
            for (BaseChart chart : mChartList) {
                chart.onDraw(canvas);
            }
        }

        if (mForeground != null) {
            mForeground.onDraw(canvas);
        }
    }

    public GeyekChartView setBackground(BaseGround background) {
        if (background == null) {
            return this;
        }
        mBackground = background;
        mBackground.setStarX(mPaddingLeft + mLeftTagWidth);
        mBackground.setStartY(mPaddingTop + mTopTagHeight);
        mBackground.setEndX(mWidth - mPaddingRight - mRightTagWidth);
        mBackground.setEndY(mHeight - mPaddingBottom - mBottomTagHeight);
        return this;
    }

    public GeyekChartView setForefground(BaseGround forefground) {
        if (forefground == null) {
            return this;
        }
        mForeground = forefground;
        mForeground.setStarX(mPaddingLeft + mLeftTagWidth);
        mForeground.setStartY(mPaddingTop + mTopTagHeight);
        mForeground.setEndX(mWidth - mPaddingRight - mRightTagWidth);
        mForeground.setEndY(mHeight - mPaddingBottom - mBottomTagHeight);
        return this;
    }

    public GeyekChartView setChart(BaseChart chart) {
        if (chart == null) {
            return this;
        }
        chart.setStarX(mPaddingLeft + mLeftTagWidth);
        chart.setStartY(mPaddingTop + mTopTagHeight);
        chart.setEndX(mWidth - mPaddingRight - mRightTagWidth);
        chart.setEndY(mHeight - mPaddingBottom - mBottomTagHeight);
        mChartList.add(chart);
        return this;
    }
}
