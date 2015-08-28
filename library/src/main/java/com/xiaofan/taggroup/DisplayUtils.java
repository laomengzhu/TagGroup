package com.xiaofan.taggroup;

import android.content.Context;
import android.graphics.Paint;

/**
 * Created by xiaolifan on 2015/8/26.
 */
public abstract class DisplayUtils {

    public static int dip2px(int dipValue, Context context) {
        return (int) (context.getResources().getDisplayMetrics().density * dipValue + 0.5f);
    }

    public static int px2dip(int pxValue, Context context) {
        return (int) ((pxValue - 0.5f)/context.getResources().getDisplayMetrics().density);
    }

    public static int getTextWidth(String text, Paint paint) {
        int iRet = 0;
        float iRet_f = 0;
        if (text != null && text.length() > 0) {
            int len = text.length();
            float[] widths = new float[len];
            paint.getTextWidths(text, widths);
            for (int j = 0; j < len; j++) {
                iRet_f += widths[j];
            }
            iRet = (int) Math.ceil(iRet_f);
        }
        return iRet;
    }

    public static int getTextWidthCount(String text, Paint paint, int BGWidth) {
        int iRet = 0;
        float iRet_f = 0;
        int count = 0;//字数
        if (text != null && text.length() > 0) {
            int len = text.length();
            float[] widths = new float[len];
            paint.getTextWidths(text, widths);
            for (count = 0; count < len; count++) {
                iRet_f += widths[count];
                iRet = (int) Math.ceil(iRet_f);
                if(iRet>BGWidth){
                    break;
                }
            }

        }
        return count;
    }

    /**获取指定画笔的文字高度*/
    public static int getTextHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.ascent);
    }
}
