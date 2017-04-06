package com.gjr.wheelpickerbyrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by geng
 * on 2017/3/31.
 */
public class WheelRecyclerView extends RecyclerView {

    private Paint paint;
    private int firstLineY;
    private int secondLineY;

    private ScrollListener scrollListener;
    private int itemHeight;
    private int distance;
    private int visibleCount;
    private int wheelWidthWeight;
    private LinearLayout parent;
    private Rect mRect;
    private Paint paint1;

    public WheelRecyclerView(Context context) {
        this(context, null);
    }

    public WheelRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        paint = new Paint();
        paint1 = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);// 线宽 1px

        setClipToPadding(false);// 滚动不裁剪，不显示顶部的padding
        setClipChildren(false);

        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.WheelRecyclerView);
            visibleCount = ta.getInteger(R.styleable.WheelRecyclerView_visibleCount, 5);// 默认显示5个
            itemHeight = ta.getDimensionPixelOffset(R.styleable.WheelRecyclerView_itemHeight, getResources().getDimensionPixelSize(R.dimen.wheel_recycler_view_item_height));
            wheelWidthWeight = ta.getInteger(R.styleable.WheelRecyclerView_wheelWidthWeight, 1);// 默认是1
            ta.recycle();
        } else {
            visibleCount = 5;
            itemHeight = ScreenUtil.dip2px(getContext(), 36f);
            wheelWidthWeight = 1;
        }

        setPadding(0, itemHeight * (visibleCount / 2), 0, itemHeight * (visibleCount / 2));
        firstLineY = (visibleCount / 2) * itemHeight;
        secondLineY = (visibleCount / 2 + 1) * itemHeight;

        mRect = new Rect();
    }

    @Override
    public void onScrollStateChanged(int state) {
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            smoothScrollToView(findViewAtCenter());
            if (scrollListener != null && distance == 0) {// 解决微调时，连续动2次的问题，避免影响index的定位
                scrollListener.scrollChanged(state);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        mRect.set(0, 0, getWidth(), getHeight());
        LinearGradient lg = new LinearGradient(0, 0, 0, getHeight() / 2, Color.parseColor("#ddffffff"), Color.parseColor("#00ffffff"),
                Shader.TileMode.MIRROR);

        paint1.setShader(lg);
        canvas.drawRect(mRect, paint1);

        drawLine(canvas);
    }

    /**
     * 画2条分割线
     *
     * @param c Canvas
     */
    private void drawLine(Canvas c) {
        // TODO: 2017/3/31 将来添加属性，来定制线条
        c.drawLine(0, firstLineY, getWidth(), firstLineY, paint);
        c.drawLine(0, secondLineY, getWidth(), secondLineY, paint);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);

        // 将宽度 按照 宽度的权重进行设置
        try {
            parent = (LinearLayout) getParent();
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
        float count;
        if (parent != null) {
            count = parent.getChildCount();
        } else {
            count = 1;
        }
        int screenW = ScreenUtil.getScreenW(getContext());

        // 将高度按照 item 的高度定制
        int resultWidth = (int) (wheelWidthWeight / count * screenW);
        int resultHeight = itemHeight * visibleCount;
        setMeasuredDimension(resultWidth, resultHeight);
    }

    /**
     * 位置微调，把 item 放在2条线内
     *
     * @param v item
     */
    public void smoothScrollToView(View v) {
        if (v == null) {
            return;
        }
        int y = v.getTop() + itemHeight / 2;
        int halfHeight = getHeight() / 2;
        distance = (y - halfHeight);
        smoothScrollBy(0, distance);
    }

    /**
     * 寻找中间位置的 item
     */
    public View findViewAt(int y) {
        final int count = getChildCount();
        for (int i = 0; i < count; ++i) {
            final View v = getChildAt(i);
            final int y0 = v.getTop();
            final int y1 = v.getHeight() + y0;
            if (y >= y0 && y <= y1) {
                return v;
            }
        }
        return null;
    }

    public View findViewAtCenter() {
        return findViewAt(getHeight() / 2);
    }

    interface ScrollListener {
        void scrollChanged(int state);
    }

    public void setScrollListener(ScrollListener listener) {
        this.scrollListener = listener;
    }

    public void moveToPosition(int position) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
        layoutManager.scrollToPosition(position);
    }
}
