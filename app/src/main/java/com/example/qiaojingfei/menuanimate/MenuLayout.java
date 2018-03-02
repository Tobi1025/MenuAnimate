package com.example.qiaojingfei.menuanimate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

/**
 * description: MenuLayout
 * author: qiaojingfei
 * date: 2018/3/2 上午10:07
*/

public class MenuLayout extends ViewGroup implements View.OnClickListener {
    private Context mContext;
    private int padding;
    private int mWidth;
    private int mHeight;
    private boolean isExpand;
    private Button mButton;


    public MenuLayout(Context context) {
        this(context, null);
    }

    public MenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        padding = DimenUtils.dip2px(mContext, 10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = widthSize;
        mHeight = heightSize;
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int defaultWidth = DimenUtils.dip2px(mContext, 200);
        int defaultHeight = DimenUtils.dip2px(mContext, 100);
        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? mWidth
                : defaultWidth, (heightMode == MeasureSpec.EXACTLY) ? mHeight
                : defaultHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.setVisibility(GONE);
            if (i < 3) {
                child.layout(padding + (mWidth / 3) * i, mHeight / 2 + padding, (mWidth / 3) * (i + 1) - padding, mHeight / 2 + padding + child.getMeasuredHeight());
                if (i == 1) {
                    child.setVisibility(VISIBLE);
                }
            } else {
                if (i == getChildCount() - 1) {
                    child.layout(mWidth / 2 - child.getMeasuredWidth() / 2, mHeight - child.getMeasuredHeight(), mWidth / 2 + child.getMeasuredWidth() / 2, mHeight - padding);
                    child.setOnClickListener(this);
                    child.setVisibility(VISIBLE);
                    mButton = (Button) child;
                } else {
                    child.layout(padding + (mWidth / 3) * (i - 3), mHeight / 2 + child.getMeasuredHeight() + 3 * padding, (mWidth / 3) * (i - 2) - padding, mHeight / 2 + 3 * padding + 2 * child.getMeasuredHeight());
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        isExpand = !isExpand;
        if (isExpand) {
            //图标扩散
            mButton.setText("收缩");
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                AnimationSet animationSet;
                float fromXDel;
                float fromYDel;
                switch (i) {
                    case 0:
                        AlphaAnimation alphaAnimation0 = new AlphaAnimation(0, 1);
                        alphaAnimation0.setDuration(500);
                        animationSet = new AnimationSet(true);
                        animationSet.addAnimation(alphaAnimation0);

                        fromXDel = padding + (mWidth / 3) - padding;
                        TranslateAnimation animation0 = new TranslateAnimation(fromXDel, 0, 0, 0);
                        animation0.setDuration(500);
                        animationSet.addAnimation(animation0);

                        animationSet.setFillAfter(true);
                        child.startAnimation(animationSet);
                        child.setVisibility(VISIBLE);
                        Log.e("icon", "第" + i + "个图标平移");
                        break;
                    case 1:
                        break;
                    case 2:
                        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0, 1);
                        alphaAnimation2.setDuration(500);
                        animationSet = new AnimationSet(true);
                        animationSet.addAnimation(alphaAnimation2);

                        fromXDel = padding + (mWidth / 3) - (padding + (mWidth / 3) * 2);
                        TranslateAnimation animation2 = new TranslateAnimation(fromXDel, 0, 0, 0);
                        animation2.setDuration(500);
                        animationSet.addAnimation(animation2);

                        animationSet.setFillAfter(true);
                        child.startAnimation(animationSet);
                        child.setVisibility(VISIBLE);
                        Log.e("icon", "第" + i + "个图标平移");
                        break;
                    case 3:
                        AlphaAnimation alphaAnimation3 = new AlphaAnimation(0, 1);
                        alphaAnimation3.setDuration(500);
                        animationSet = new AnimationSet(true);
                        animationSet.addAnimation(alphaAnimation3);

                        fromXDel = padding + (mWidth / 3) - padding;
                        fromYDel = mHeight / 2 + padding - (mHeight / 2 + child.getMeasuredHeight() + 3 * padding);
                        TranslateAnimation animation3 = new TranslateAnimation(fromXDel, 0, fromYDel, 0);
                        animation3.setDuration(500);
                        animationSet.addAnimation(animation3);

                        animationSet.setFillAfter(true);
                        child.startAnimation(animationSet);
                        child.setVisibility(VISIBLE);
                        Log.e("icon", "第" + i + "个图标平移");
                        break;
                    case 4:
                        AlphaAnimation alphaAnimation4 = new AlphaAnimation(0, 1);
                        alphaAnimation4.setDuration(500);
                        animationSet = new AnimationSet(true);
                        animationSet.addAnimation(alphaAnimation4);

                        fromYDel = mHeight / 2 + padding - (mHeight / 2 + child.getMeasuredHeight() + 3 * padding);
                        TranslateAnimation animation4 = new TranslateAnimation(0, 0, fromYDel, 0);
                        animation4.setDuration(500);
                        animationSet.addAnimation(animation4);

                        animationSet.setFillAfter(true);
                        child.startAnimation(animationSet);
                        child.setVisibility(VISIBLE);
                        Log.e("icon", "第" + i + "个图标平移");
                        break;
                    case 5:
                        AlphaAnimation alphaAnimation5 = new AlphaAnimation(0, 1);
                        alphaAnimation5.setDuration(500);
                        animationSet = new AnimationSet(true);
                        animationSet.addAnimation(alphaAnimation5);

                        fromXDel = padding + (mWidth / 3) - (padding + (mWidth / 3) * 2);
                        fromYDel = mHeight / 2 + padding - (mHeight / 2 + child.getMeasuredHeight() + 3 * padding);
                        TranslateAnimation animation5 = new TranslateAnimation(fromXDel, 0, fromYDel, 0);
                        animation5.setDuration(500);
                        animationSet.addAnimation(animation5);

                        animationSet.setFillAfter(true);
                        child.startAnimation(animationSet);
                        child.setVisibility(VISIBLE);
                        Log.e("icon", "第" + i + "个图标平移");
                        break;
                }
            }
        } else {
            //图标收缩
            mButton.setText("扩散");
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                AnimationSet animationSet;
                float toXDel;
                float toYDel;
                switch (i) {
                    case 0:
                        AlphaAnimation alphaAnimation0 = new AlphaAnimation(1, 0);
                        alphaAnimation0.setDuration(500);
                        animationSet = new AnimationSet(true);
                        animationSet.addAnimation(alphaAnimation0);

                        toXDel = padding + (mWidth / 3) - padding;
                        TranslateAnimation animation0 = new TranslateAnimation(0, toXDel, 0, 0);
                        animation0.setDuration(500);
                        animationSet.addAnimation(animation0);

                        animationSet.setFillAfter(true);
                        child.startAnimation(animationSet);
                        child.setVisibility(GONE);
                        Log.e("icon", "第" + i + "个图标平移");
                        break;
                    case 1:
                        break;
                    case 2:
                        AlphaAnimation alphaAnimation2 = new AlphaAnimation(1, 0);
                        alphaAnimation2.setDuration(500);
                        animationSet = new AnimationSet(true);
                        animationSet.addAnimation(alphaAnimation2);

                        toXDel = padding + (mWidth / 3) - (padding + (mWidth / 3) * 2);
                        TranslateAnimation animation2 = new TranslateAnimation(0, toXDel, 0, 0);
                        animation2.setDuration(500);
                        animationSet.addAnimation(animation2);

                        animationSet.setFillAfter(true);
                        child.startAnimation(animationSet);
                        child.setVisibility(GONE);
                        Log.e("icon", "第" + i + "个图标平移");
                        break;
                    case 3:
                        AlphaAnimation alphaAnimation3 = new AlphaAnimation(1, 0);
                        alphaAnimation3.setDuration(500);
                        animationSet = new AnimationSet(true);
                        animationSet.addAnimation(alphaAnimation3);

                        toXDel = padding + (mWidth / 3) - padding;
                        toYDel = mHeight / 2 + padding - (mHeight / 2 + child.getMeasuredHeight() + 3 * padding);
                        TranslateAnimation animation3 = new TranslateAnimation(0, toXDel, 0, toYDel);
                        animation3.setDuration(500);
                        animationSet.addAnimation(animation3);

                        animationSet.setFillAfter(true);
                        child.startAnimation(animationSet);
                        child.setVisibility(GONE);
                        Log.e("icon", "第" + i + "个图标平移");
                        break;
                    case 4:
                        AlphaAnimation alphaAnimation4 = new AlphaAnimation(1, 0);
                        alphaAnimation4.setDuration(500);
                        animationSet = new AnimationSet(true);
                        animationSet.addAnimation(alphaAnimation4);

                        toYDel = mHeight / 2 + padding - (mHeight / 2 + child.getMeasuredHeight() + 3 * padding);
                        TranslateAnimation animation4 = new TranslateAnimation(0, 0, 0, toYDel);
                        animation4.setDuration(500);
                        animationSet.addAnimation(animation4);

                        animationSet.setFillAfter(true);
                        child.startAnimation(animationSet);
                        child.setVisibility(GONE);
                        Log.e("icon", "第" + i + "个图标平移");
                        break;
                    case 5:
                        AlphaAnimation alphaAnimation5 = new AlphaAnimation(1, 0);
                        alphaAnimation5.setDuration(500);
                        animationSet = new AnimationSet(true);
                        animationSet.addAnimation(alphaAnimation5);

                        toXDel = padding + (mWidth / 3) - (padding + (mWidth / 3) * 2);
                        toYDel = mHeight / 2 + padding - (mHeight / 2 + child.getMeasuredHeight() + 3 * padding);
                        TranslateAnimation animation5 = new TranslateAnimation(0, toXDel, 0, toYDel);
                        animation5.setDuration(500);
                        animationSet.addAnimation(animation5);

                        animationSet.setFillAfter(true);
                        child.startAnimation(animationSet);
                        child.setVisibility(GONE);
                        Log.e("icon", "第" + i + "个图标平移");
                        break;
                }
            }
        }

    }
}
