package com.geyek.widget.diagram.chart;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.geyek.widget.diagram.GeyekChartView;
import com.geyek.widget.diagram.kernel.BaseChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiHuan on 2016/12/23.
 */
public class LineChart extends BaseChart {
    private static final String TAG = "LineChart";
    private List<Float> mPointList = new ArrayList<>();

    private float mOffset;  //设置偏移量
    private float mCurrentPosition; //标记当前的坐标
    private int mMaxItem = 1; //当前界面显示的最大条目数,最小为1
    private float mMaxValue;    //当前单个条目最大的值
    private float mMinValue;
    private int mLineColor;
    private float mLineWidth;
    private boolean mIsAutoMaxValue;
    private boolean mIsAutoMinValue;

    public LineChart(GeyekChartView chartView) {
        super(chartView);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(mLineWidth);
        paint.setColor(mLineColor);

        int size = mPointList.size();
        Float prePoint = null;
        float drawableHeight = mCharView.getDrawableHeight();
        float cellHeight = getCellHeight();
        float cellWidth = getCellWidth();
        for (int i = 0; i < size; i++) {
            Float currentPoint = mPointList.get(i);
            if (prePoint == null) {
                prePoint = currentPoint;
                continue;
            }

            canvas.drawLine(
                    mOffset + (i - 1) * cellWidth,
                    drawableHeight - (prePoint - mMinValue) * cellHeight,
                    mOffset + i * cellWidth,
                    drawableHeight - (currentPoint - mMinValue) * cellHeight,
                    paint
            );

            prePoint = currentPoint;
        }
    }

    /**
     * 获取单位值所占用的高度
     * @return
     */
    public float getCellHeight() {
        float v = mMaxValue - mMinValue;
        if (v != 0) {
            return mCharView.getDrawableHeight() / v;
        } else {
            float v1 = mMaxValue - 0;
            if (v1 != 0) {
                return mCharView.getDrawableHeight() / v1;
            } else {
                return 0;
            }
        }
    }

    /**
     * 获取单位值所占用的宽度
     * @return
     */
    public float getCellWidth() {
        return mCharView.getDrawableWidth() / mMaxItem;
    }

    public void setOffset(float offset) {
        mOffset = offset;
    }

    public boolean setPointList(List<Float> pointList) {
        if (pointList == null) {
            return false;
        }
        boolean isSuccess = mPointList.addAll(pointList);
        refreshMaxValue();
        refreshMinValue();
        return isSuccess;
    }

    public boolean setPoint(float point) {
        boolean isSuccess = mPointList.add(point);
        refreshMaxValue(point);
        refreshMinValute(point);
        return isSuccess;
    }

    public void clear() {
        mPointList.clear();
    }

    public void setMaxItem(int item) {
        if (item < 1) {
            item = 1;
        }
        mMaxItem = item;
    }

    public void setMaxValue(float maxValue) {
        if (mMaxValue < maxValue) {
            mMaxValue = maxValue;
        }
        if (mMinValue > mMaxValue) {
            float temp = mMinValue;
            mMinValue = mMaxValue;
            mMaxValue = temp;
        }
    }

    public void setMinValue(float minValue) {
        if (mMinValue > minValue) {
            mMinValue = minValue;
        }
        if (mMinValue > mMaxValue) {
            float temp = mMinValue;
            mMinValue = mMaxValue;
            mMaxValue = temp;
        }
    }

    public void setLineColor(int lineColor) {
        mLineColor = lineColor;
    }

    public void setLineWidth(float lineWidth) {
        if (lineWidth < 0) {
            lineWidth = 1;
        }
        mLineWidth = lineWidth;
    }

    public void setAutoMaxValue(boolean autoMaxValue) {
        mIsAutoMaxValue = autoMaxValue;
        refreshMaxValue();
    }

    public void setAutoMinValue(boolean autoMinValue) {
        mIsAutoMinValue = autoMinValue;

    }

    private void refreshMaxValue() {
        if (!mIsAutoMaxValue) {
            return;
        }
        float maxValue = 0;
        for (Float point : mPointList) {
            if (point == null) {
                continue;
            }
            if (maxValue < point) {
                maxValue = point;
            }
        }
        if (mMaxValue < maxValue) {
            mMaxValue = maxValue;
        }
    }

    private void refreshMaxValue(float point) {
        if (!mIsAutoMaxValue) {
            return;
        }
        if (mMaxValue < point) {
            mMaxValue = point;
        }
    }

    private void refreshMinValue() {
        if (!mIsAutoMaxValue) {
            return;
        }
        float minValue = 0;
        for (Float point : mPointList) {
            if (point == null) {
                continue;
            }
            if (minValue > point) {
                minValue = point;
            }
        }
        if (mMinValue > minValue) {
            mMinValue = minValue;
        }
    }

    private void refreshMinValute(float point) {
        if (!mIsAutoMinValue) {
            return;
        }
        if (mMinValue > point) {
            mMinValue = point;
        }
    }

    public boolean isAutoMaxValue() {
        return mIsAutoMaxValue;
    }
}
