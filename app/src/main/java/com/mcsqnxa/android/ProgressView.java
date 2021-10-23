package com.mcsqnxa.android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;


public class ProgressView extends View {
    /**
     * 圆弧与信息的Paint
     */
    private final Paint parc, pinfo;

    /**
     * 圆弧宽度
     */
    private float strokeWith = 15;

    /**
     * 圆弧开始的角度
     */
    private float startAngle = 270;

    /**
     * 加载进度(0~100)
     */
    private float progress = 0;

    /**
     * 显示的信息
     */
    private String info;

    /**
     * 加载进度颜色
     */
    private int
            init = Color.parseColor("#FF00FF00"),
            load = Color.parseColor("#FFFF0000");


    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        this.parc = new Paint(Paint.ANTI_ALIAS_FLAG);
        //抗锯齿
        this.parc.setAntiAlias(true);
        //透明度（数值为0-255）
        this.parc.setAlpha(100);
        //画笔的画出的形状
        this.parc.setStrokeJoin(Paint.Join.ROUND);
        this.parc.setStrokeCap(Paint.Cap.ROUND);
        //画笔类型
        this.parc.setStyle(Paint.Style.STROKE);
        //画笔宽度
        this.parc.setStrokeWidth(this.strokeWith * Resources.getSystem().getDisplayMetrics().density);

        this.pinfo = new Paint();
        //抗锯齿
        this.pinfo.setAntiAlias(true);
        //文字颜色
        this.pinfo.setColor(Color.parseColor("#FF4A40"));
        //设置文本的对齐方式
        this.pinfo.setTextAlign(Paint.Align.CENTER);
        //文字大小
        this.pinfo.setTextSize(18 * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = super.getWidth() / 2;//中心x轴
        float centerY = super.getWidth() / 2;//中心y轴

        if (this.info != null)//绘制文本
        {
            Rect rect = new Rect();
            this.pinfo.getTextBounds(this.info, 0, this.info.length(), rect);
            canvas.drawText(this.info, centerX, centerY + rect.height() - 20, this.pinfo);
        }

        RectF rectf = new RectF(
                this.strokeWith + 12,//当前view的x轴进行绘制
                this.strokeWith + 12,//当前view的y轴进行绘制
                super.getWidth() - this.strokeWith - 12,
                super.getWidth() - this.strokeWith - 12
        );

        //圆弧背景颜色
        this.parc.setColor(this.init);
        //绘制背景圆弧
        canvas.drawArc(rectf, startAngle, 360, false, this.parc);
        //绘制圆弧颜色
        this.parc.setColor(this.load);
        //绘制圆弧
        canvas.drawArc(rectf, startAngle, (float) 3.6 * this.progress, false, this.parc);
    }

    /**
     * 设置圆弧宽度
     */
    public void setStrokeWith(float dp) {
        if (dp > 0) {
            this.strokeWith = dp;

            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.invalidate();
            } else {
                super.postInvalidate();
            }
        }
    }

    /**
     * 设置圆弧开始角度
     */
    public void setStartAngle(float dp) {
        if (dp > 0) {
            this.startAngle = dp;

            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.invalidate();
            } else {
                super.postInvalidate();
            }
        }
    }

    /**
     * 设置进度
     */
    public void setProgress(float progress) {
        if (progress >= 0 && progress <= 100) {
            this.progress = progress;

            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.invalidate();
            } else {
                super.postInvalidate();
            }
        }
    }

    /**
     * 设置进度与文字
     */
    public void setProgress(float progress, String info) {
        if (progress >= 0 && progress <= 100 && info != null) {
            this.progress = progress;
            this.info = info;

            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.invalidate();
            } else {
                super.postInvalidate();
            }
        }
    }

    /**
     * 设置文字
     */
    public void setText(String info) {
        if (info != null) {
            this.info = info;

            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.invalidate();
            } else {
                super.postInvalidate();
            }
        }
    }

    /**
     * 设置文字大小
     */
    public void setTextSize(float dp) {
        if (dp > 0) {
            this.pinfo.setTextSize(dp * Resources.getSystem().getDisplayMetrics().density);

            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.invalidate();
            } else {
                super.postInvalidate();
            }
        }
    }

    /**
     * 设置文字颜色
     */
    public void setTextColor(int id) {
        this.pinfo.setColor(super.getResources().getColor(id));

        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.invalidate();
        } else {
            super.postInvalidate();
        }
    }

    /**
     * 设置文字颜色
     */
    public void setTextColor(String color) {
        this.pinfo.setColor(Color.parseColor(color));

        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.invalidate();
        } else {
            super.postInvalidate();
        }
    }

    /**
     * 设置进度颜色
     */
    public void setProgressColor(int init, int load) {
        this.init = super.getResources().getColor(init);
        this.load = super.getResources().getColor(load);

        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.invalidate();
        } else {
            super.postInvalidate();
        }
    }

    /**
     * 设置进度颜色
     */
    public void setProgressColor(String init, String load) {
        this.init = Color.parseColor(init);
        this.load = Color.parseColor(load);

        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.invalidate();
        } else {
            super.postInvalidate();
        }
    }


}
