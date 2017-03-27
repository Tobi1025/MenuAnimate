## MenuAnimate

### xml文件中：
``` java
<com.example.qiaojingfei.menuanimate.MenuLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        >
        <ImageView
            android:layout_width="64dp"
            android:layout_height="64dp"
            android:src="@mipmap/share_sina"
            />
            ...
</com.example.qiaojingfei.menuanimate.MenuLayout>
``` 

### java代码中:
1. onMeasure（）中测量ViewGroup上级容器的宽和高与其childView的宽和高
``` java
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
 ``` 
    
2.onLayout()中定位childView的位置。
* 在for循环中遍历，通过getChildAt(i)获取childView;
* 计算childview坐标，并通过childView.layout(left，top，right，bottom)定位；

3.在按钮点击事件中设置分享弹出动画
``` java
  AnimationSet animationSet;
//设置透明动画并添加到AnimationSet动画集合中
  AlphaAnimation alphaAnimation0 = new AlphaAnimation(0, 1);
  alphaAnimation0.setDuration(500);
  animationSet = new AnimationSet(true);
  animationSet.addAnimation(alphaAnimation0);
//设置平移动画并添加到AnimationSet动画集合中        
  fromXDel = padding + (mWidth / 3) - padding;
  TranslateAnimation animation = new TranslateAnimation(fromXDel, 0, 0, 0);
  animation.setDuration(500);
  animationSet.addAnimation(animation);
  
  animationSet.setFillAfter(true);
  child.startAnimation(animationSet);
```
**这里要注意TranslateAnimation（）这个构造方法** ：TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)
* float fromXDelta 动画开始的点离当前View X坐标上的差值 
* float toXDelta 动画结束的点离当前View X坐标上的差值 
* float fromYDelta 动画开始的点离当前View Y坐标上的差值 
* float toYDelta 动画结束的点离当前View Y坐标上的差值 

**以上都是与view原始坐标的差值，而不是具体的坐标。**
