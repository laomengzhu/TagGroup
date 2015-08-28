package com.xiaofan.taggroup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class TagGroup extends ViewGroup implements OnClickListener {

    /**
     * default horizontal space(dip) between tags
     */
    private static final int DEFAULT_HORIZONTAL_SPACE = 5;

    /**
     * default vertical space(dip) between tags
     */
    private static final int DEFAULT_VERTICAL_SPACE = 5;

    /**
     * default tag horizontal padding(dp)
     */
    private static final int DEFAULT_HORIZONTAL_PADDING = 8;

    /**
     * default tag vertical padding(dp)
     */
    private static final int DEFAULT_VERTICAL_PADDING = 2;

    /**
     * horizontal space(px) between tags
     */
    private int tagHorizontalSpace = 0;

    /**
     * vertical space(px) between tags
     */
    private int tagVerticalSpace = 0;

    /**
     * tagView horizontal padding
     */
    private int tagHorizontalPadding;

    /**
     * tagView vertical padding
     */
    private int tagVerticalPadding;

    /**
     * tagView text size
     */
    private int tagViewTextSize;

    /**
     * tagView text color
     */
    private int tagViewTextColor;

    /**
     * tag stroke color
     */
    private int tagStrokeColor;

    /**
     * tag background color
     */
    private int tagBackgroundColor;

    private int tagCornerRadius;

    private OnTagClickListener onTagClickListener;

    public TagGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initParam(context, attrs);
    }

    public TagGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagGroup(Context context) {
        this(context, null);
    }

    private void initParam(Context context, AttributeSet attrs) {
        tagHorizontalSpace = DisplayUtils.dip2px(DEFAULT_HORIZONTAL_SPACE, context);
        tagVerticalSpace = DisplayUtils.dip2px(DEFAULT_VERTICAL_SPACE, context);

        tagHorizontalPadding = DisplayUtils.dip2px(DEFAULT_HORIZONTAL_PADDING, context);
        tagVerticalPadding = DisplayUtils.dip2px(DEFAULT_VERTICAL_PADDING, context);
        tagViewTextSize = DisplayUtils.dip2px(14, context);
        tagViewTextColor = Color.BLACK;
        tagBackgroundColor = Color.WHITE;
        tagStrokeColor = Color.BLACK;
        tagCornerRadius = DisplayUtils.dip2px(13, context);

        if (attrs != null && !isInEditMode()) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TagGroup);
            Resources resources = context.getResources();

            int resID = ta.getResourceId(R.styleable.TagGroup_tagHorizontalSpace, -1);
            if (resID != -1) {
                tagHorizontalSpace = resources.getDimensionPixelSize(resID);
            } else {
                tagHorizontalSpace = ta.getDimensionPixelSize(R.styleable.TagGroup_tagHorizontalSpace, tagHorizontalSpace);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagVerticalSpace, -1);
            if (resID != -1) {
                tagVerticalSpace = resources.getDimensionPixelSize(resID);
            } else {
                tagVerticalSpace = ta.getDimensionPixelSize(R.styleable.TagGroup_tagVerticalSpace, tagVerticalSpace);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagHorizontalPadding, -1);
            if (resID != -1) {
                tagHorizontalPadding = resources.getDimensionPixelSize(resID);
            } else {
                tagHorizontalPadding = ta.getDimensionPixelSize(R.styleable.TagGroup_tagHorizontalPadding, tagHorizontalPadding);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagVerticalPadding, -1);
            if (resID != -1) {
                tagVerticalPadding = resources.getDimensionPixelSize(resID);
            } else {
                tagVerticalPadding = ta.getDimensionPixelSize(R.styleable.TagGroup_tagVerticalPadding, tagVerticalPadding);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagTextColor, -1);
            if (resID != -1) {
                tagViewTextColor = resources.getColor(resID);
            } else {
                tagViewTextColor = ta.getColor(R.styleable.TagGroup_tagTextColor, tagViewTextColor);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagTextSize, -1);
            if (resID != -1) {
                tagViewTextSize = resources.getDimensionPixelSize(resID);
            } else {
                tagViewTextSize = ta.getDimensionPixelSize(R.styleable.TagGroup_tagTextSize, tagViewTextSize);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagStrokeColor, -1);
            if (resID != -1) {
                tagStrokeColor = resources.getColor(resID);
            } else {
                tagStrokeColor = ta.getColor(R.styleable.TagGroup_tagStrokeColor, tagStrokeColor);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagBackgroundColor, -1);
            if (resID != -1) {
                tagBackgroundColor = resources.getColor(resID);
            } else {
                tagBackgroundColor = ta.getColor(R.styleable.TagGroup_tagBackgroundColor, tagBackgroundColor);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagCornerRadius, -1);
            if (resID != -1) {
                tagCornerRadius = resources.getDimensionPixelSize(resID);
            } else {
                tagCornerRadius = ta.getDimensionPixelSize(R.styleable.TagGroup_tagCornerRadius, tagCornerRadius);
            }

            ta.recycle();
        }

        if (isInEditMode()) {
            List<Tag> tagList = new ArrayList<>();
            Tag tag;
            for (int i = 0; i < 7; i++) {
                tag = new Tag();
                tag.setType(Tag.TYPE_TEXT);
                tag.setTagText("tag" + i);
                tagList.add(tag);
            }

            tag = new Tag();
            tag.setType(Tag.TYPE_ICON);
            tag.setIconID(R.drawable.icon_more);
            tagList.add(tag);

            setTags(tagList);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View child;
        // 布局总高度
        int layoutHeight = 0;
        // 布局总宽度
        int layoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        // 当前x坐标
        int positionX = getPaddingLeft();
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            child.measure(widthMeasureSpec, heightMeasureSpec);

            if (i == 0 || (positionX + child.getMeasuredWidth()/* + tagHorizontalSpace*/ + getPaddingRight()) > layoutWidth) {
                layoutHeight += (child.getMeasuredHeight() + tagVerticalSpace);
                positionX = getLeft() + getPaddingLeft();
            } else {
                positionX += (child.getMeasuredWidth() + tagHorizontalSpace);
            }
        }
        layoutHeight -= tagVerticalSpace;
        // 设置控件高度
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), layoutHeight + getPaddingTop() + getPaddingBottom());
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int positionX = getPaddingLeft();
        int positionY = getPaddingTop();
        int widgetHeight = 0;
        View child;
        for (int i = 0; i < count; i++) {

            child = this.getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            if (height > widgetHeight) {
                widgetHeight = height;
            }

            if ((l + positionX /*+ tagHorizontalSpace*/ + width + getPaddingRight()) > r) {
                positionX = getPaddingLeft();
                positionY += (tagVerticalSpace + widgetHeight);
            }

            width = child.getMeasuredWidth();
            height = child.getMeasuredHeight();
            child.layout(positionX, positionY, positionX + width, positionY + height);
            positionX += (width + tagHorizontalSpace);
        }
    }

    public void setTags(List<Tag> tags) {
        this.removeAllViews();
        if (tags != null) {
            for (int i = 0; i < tags.size(); i++) {
                TagView tagView = new TagView(getContext(), tags.get(i));
                tagView.setTextSize(tagViewTextSize);
                tagView.setTextColor(tagViewTextColor);
                tagView.setHorizontalPadding(tagHorizontalPadding);
                tagView.setVerticalPadding(tagVerticalPadding);
                tagView.setStrokeColor(tagStrokeColor);
                tagView.setBackgroundColor(tagBackgroundColor);
                tagView.setTagCornerRadius(tagCornerRadius);
                tagView.initData();
                tagView.setOnClickListener(this);
                this.addView(tagView);
            }
        }
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }

    public interface OnTagClickListener {

        void onTagClick(TagView tagView, Tag tag);
    }

    @Override
    public void onClick(View v) {
        if (onTagClickListener != null) {
            onTagClickListener.onTagClick((TagView) v, ((TagView) v).getTagData());
        }
    }

}
