package com.zhixiangzhonggong.tiebaobei.CustomizedView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;
import com.zhixiangzhonggong.tiebaobei.R;

/**
 * Created by luogang on 15-08-12.
 */
public class SlidingMenu extends HorizontalScrollView {
    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mMenuWidth;
    private int mScreenWidth;
    private int mMenuRightPadding=50;
    private boolean once=false;
    private boolean isMenuOpen=false;

    //when you use customized view attributes ,will call this two methods
    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //get the value of customized view attrs
        TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlidingMenu,defStyleAttr,0);
        int n =a.getIndexCount();
        for(int i=0;i<n;i++){
        int attr=a.getIndex(i);
            switch (attr){
                case R.styleable.SlidingMenu_rightPadding:
                    mMenuRightPadding=a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context.getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth=outMetrics.widthPixels;

        //convert dp to pix
      //  mMenuRightPadding= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context.getResources().getDisplayMetrics());

    }


    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        //get Screen width
      /*  WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth=outMetrics.widthPixels;

        //convert dp to pix
       mMenuRightPadding= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context.getResources().getDisplayMetrics());
*/
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(!once){
            mWapper= (LinearLayout) getChildAt(0);
            mMenu= (ViewGroup) mWapper.getChildAt(0);
            mContent= (ViewGroup) mWapper.getChildAt(1);
            mMenuWidth= mMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
            mContent.getLayoutParams().width=mScreenWidth;
            once=true;
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(mMenuWidth,0);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
               int  scrollX=getScrollX();
                if(scrollX>=mMenuWidth/2){
                    this.smoothScrollTo(mMenuWidth,0);
                    isMenuOpen=false;
                }else {
                    this.smoothScrollTo(0,0);
                    isMenuOpen=true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }


    public void openMenu(){
        if(isMenuOpen)
            return;
        this.smoothScrollTo(0,0);
        isMenuOpen=true;
    }


    public void closeMenu(){
        if (!isMenuOpen)
            return;
            this.smoothScrollTo(mMenuWidth,0);
        isMenuOpen=false;
    }

    public void toggle(){
        if(!isMenuOpen){
           openMenu();
        }else {
            closeMenu();
        }
    }

    //when scroll ,call this methods
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale=l*1.0f/mMenuWidth;//1~0
        //call animation ,set translationX,l is current scrollX value
        ViewHelper.setTranslationX(mMenu,mMenuWidth*scale);

    }
}


