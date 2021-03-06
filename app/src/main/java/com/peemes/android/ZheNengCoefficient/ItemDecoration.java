package com.peemes.android.ZheNengCoefficient;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by cshao on 2018/11/28.
 *RecyclerView的分割线
 */

public class ItemDecoration extends RecyclerView.ItemDecoration{

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDivider;

    public ItemDecoration(Context context){
        final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);

        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        // TODO Auto-generated method stub
        super.onDraw(c, parent, state);
        drawVertical(c , parent);
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        // TODO Auto-generated method stub
        //recyclerview左边界+paddingleft后的位置
        final int left = parent.getPaddingLeft();
        //右边界-paddingright后的位置
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int count = parent.getChildCount();
        for(int i = 0 ; i < count ; i ++){
            final View childView = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();

            final int top = childView.getBottom() + params.bottomMargin ;
            //top加上divider的高度
            final int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left , top , right , bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // TODO Auto-generated method stub
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }
}
