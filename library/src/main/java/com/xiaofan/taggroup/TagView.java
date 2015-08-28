package com.xiaofan.taggroup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class TagView extends View {

    private int textSize;
    private int textColor;
    private int strokeColor;
    private int backgroundColor;
    private int horizontalPadding, verticalPadding;
    private int tagCornerRadius;

    private Paint paint;
    private static final int OFFSET = 3;

    private int mTextHeight;
    private int mViewWidth;
    private int mViewHeight;

    private int offset;

    private Tag tag;

    public TagView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TagView(Context context) {
        this(context, null, 0);
    }

    public TagView(Context context, Tag tag) {
        super(context);
        this.tag = tag;
    }

    protected void initData() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(textSize);

        mTextHeight = DisplayUtils.getTextHeight(paint);
        mViewHeight = mTextHeight + 2 * verticalPadding;

        offset = DisplayUtils.dip2px(OFFSET, getContext());

        if (tag != null && tag.getType() == Tag.TYPE_TEXT) {
            int mTextWidth = DisplayUtils.getTextWidth(tag.getTagText(), paint);
            mViewWidth = mTextWidth + 2 * horizontalPadding;

        } else if (tag != null && tag.getType() == Tag.TYPE_ICON) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), tag.getIconID());
            mViewWidth = bitmap.getWidth() + 2 * horizontalPadding;
            bitmap.recycle();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mViewWidth + 2, mViewHeight + 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(strokeColor);//边框颜色
        RectF rect = new RectF(0, 0, mViewWidth + 2, mViewHeight + 2);
        canvas.drawRoundRect(rect, tagCornerRadius, tagCornerRadius, paint);

        paint.setColor(backgroundColor);
        rect = new RectF(1, 1, mViewWidth + 1, mViewHeight + 1);
        canvas.drawRoundRect(rect, tagCornerRadius - 1, tagCornerRadius - 1, paint);

        if (tag != null && tag.getType() == Tag.TYPE_TEXT) {
            paint.setColor(textColor);
            canvas.drawText(tag.getTagText(), horizontalPadding, mTextHeight + verticalPadding - offset, paint);
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), tag.getIconID());
            canvas.drawBitmap(bitmap, horizontalPadding + 1, (float) (mViewHeight + 2 - bitmap.getHeight()) / (float) 2, paint);
        }
    }

    public Tag getTagData() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
        if (tag != null && tag.getType() == Tag.TYPE_TEXT) {

            int mTextWidth = DisplayUtils.getTextWidth(tag.getTagText(), paint);
            mViewWidth = mTextWidth + 2 * horizontalPadding;

        } else if (tag != null && tag.getType() == Tag.TYPE_ICON) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), tag.getIconID());
            mViewWidth = bitmap.getHeight() + 2 * horizontalPadding;
            bitmap.recycle();
        }

        setMeasuredDimension(mViewWidth + 2, mViewHeight + 2);
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setHorizontalPadding(int horizontalPadding) {
        this.horizontalPadding = horizontalPadding;
    }

    public void setVerticalPadding(int verticalPadding) {
        this.verticalPadding = verticalPadding;
    }

    public void setTagCornerRadius(int tagCornerRadius) {
        this.tagCornerRadius = tagCornerRadius;
    }
}
