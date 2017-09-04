package com.hencoder.hencoderpracticedraw3.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice13GetTextBoundsView extends View {
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    String text1 = "A";
    String text2 = "a";
    String text3 = "J";
    String text4 = "j";
    String text5 = "Â";
    String text6 = "â";
    int top = 200;
    int bottom = 400;
    private int offset;

    public Practice13GetTextBoundsView(Context context) {
        super(context);
    }

    public Practice13GetTextBoundsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice13GetTextBoundsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(20);
        paint1.setColor(Color.parseColor("#E91E63"));
        paint2.setTextSize(160);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(50, top, getWidth() - 50, bottom, paint1);

        // 使用 Paint.getTextBounds() 计算出文字的显示区域
        // 然后计算出文字的绘制位置，从而让文字上下居中
        // 这种居中算法的优点是，可以让文字精准地居中，分毫不差

        //https://wenhaiz.github.io/blog/get-text-bounds/
        /**
         * 要让文字在矩形中居中:
         * 1.先确定矩形的中线中标也就是: int middle = (top + bottom) / 2;
         * 2.让文字的中线和矩形的中线重合,相当于就居中了
         * 3.确定文字的中线坐标(中线就是文字y轴平分的那个坐标):
         *  (1) paint2.getTextBounds(text1, 0, text1.length(), rect);
         *      通过这个方法获取到的Rect, 原点(0, 0)就是baseline的坐标,
         *      对于A来说rect.bottom是等于0的, 这个时候bottom也就是baseline, top因为是在baseline的上面所以是一个负值,
         *      A的中线相对于baseline的坐标等于=(rect.top + rect.bottom) / 2, 也是一个负值,因为在baseline的上面;
         *  (2)
         *      对于j来说rect.bottom是大于0的, 这个时候bottom在baseline的下方,
         *      j的中线相对于baseline的坐标等于=(rect.top + rect.bottom) / 2, 也是一个负值,因为在baseline的上面;
         *
         * 4.让文字在矩形居中, 就相当于是让文字中线和矩形中线重合,
         * drawText的第二个参数是填写的y轴的文字的基线坐标,
         * 要让文字居中就要计算文字基线坐标相对于文字中线坐标的差值,差值也就是我们刚刚
         * 计算的,中线相对于baseline的坐标(一个负值),
         *
         * 所以要让文字居中y轴的基线坐标就等于矩形的中线坐标+文字中线坐标相对于baseline的差值,也就是:
         * middle + Math.abs((rect.top + rect.bottom) / 2);
         *
         * 备注:配合drawable中的3张图片理解!!!
         *
         *
         */
        int middle = (top + bottom) / 2;

        Rect bounds = new Rect();
        paint2.getTextBounds(text1, 0, text1.length(), bounds);
        offset = (bounds.top + bounds.bottom) / 2;


        canvas.drawText(text1, 100, middle + Math.abs(offset), paint2);


        paint2.getTextBounds(text2, 0, text2.length(), bounds);
        offset = (bounds.top + bounds.bottom) / 2;
        canvas.drawText(text2, 200, middle + Math.abs(offset), paint2);

        paint2.getTextBounds(text3, 0, text3.length(), bounds);
        offset = (bounds.top + bounds.bottom) / 2;
        canvas.drawText(text3, 300, middle + Math.abs(offset), paint2);

        paint2.getTextBounds(text4, 0, text4.length(), bounds);
        offset = (bounds.top + bounds.bottom) / 2;
        canvas.drawText(text4, 400, middle + Math.abs(offset), paint2);

        paint2.getTextBounds(text5, 0, text5.length(), bounds);
        offset = (bounds.top + bounds.bottom) / 2;
        canvas.drawText(text5, 500, middle + Math.abs(offset), paint2);

        paint2.getTextBounds(text6, 0, text6.length(), bounds);
        offset = (bounds.top + bounds.bottom) / 2;
        canvas.drawText(text6, 600, middle + Math.abs(offset), paint2);
    }
}