package com.hencoder.hencoderpracticedraw3.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12MeasureTextView extends View {
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    String text1 = "三个月内你胖了";
    String text2 = "4.5";
    String text3 = "公斤";

    public Practice12MeasureTextView(Context context) {
        super(context);
    }

    public Practice12MeasureTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12MeasureTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint1.setTextSize(60);
        paint2.setTextSize(120);
        paint2.setColor(Color.parseColor("#E91E63"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.measureText 测量出文字宽度，让文字可以相邻绘制

        /**
         * 咦，前面有了 getTextBounds()，这里怎么又有一个 measureText()？

         如果你用代码分别使用 getTextBounds() 和 measureText() 来测量文字的宽度，你会发现  measureText()
         测出来的宽度总是比 getTextBounds() 大一点点。这是因为这两个方法其实测量的是两个不一样的东西。

         getTextBounds: 它测量的是文字的显示范围（关键词：显示）。形象点来说，你这段文字外放置一个可变的矩形，
         然后把矩形尽可能地缩小，一直小到这个矩形恰好紧紧包裹住文字，那么这个矩形的范围，就是这段文字的 bounds。

         measureText(): 它测量的是文字绘制时所占用的宽度（关键词：占用）。前面已经讲过，一个文字在界面中，
         往往需要占用比他的实际显示宽度更多一点的宽度，以此来让文字和文字之间保留一些间距，不会显得过于拥挤。
         上面的这幅图，我并没有设置 setLetterSpacing() ，这里的 letter spacing 是默认值 0，但你可以看到，
         图中每两个字母之间都是有空隙的。另外，下方那条用于表示文字宽度的横线，在左边超出了第一个字母 H 一段距离的，在
         右边也超出了最后一个字母 r（虽然右边这里用肉眼不太容易分辨），而就是两边的这两个「超出」，导致了 measureText()
         比 getTextBounds() 测量出的宽度要大一些。


         在实际的开发中，测量宽度要用 measureText() 还是 getTextBounds() ，需要根据情况而定。
         不过你只要掌握了上面我所说的它们的本质，在选择的时候就不会为难和疑惑了。
         */

        canvas.drawText(text1, 50, 200, paint1);
        canvas.drawText(text2, 50 + paint1.measureText(text1), 200, paint2);
        canvas.drawText(text3, 50 + paint1.measureText(text1) + paint2.measureText(text2), 200, paint1);
    }
}
