package com.example.dell.drawchartline;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Shader;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import java.util.List;



/**
 * Created by dell on 2014/10/8.
 */
public class BrokeLine  extends View{

    private float xOryDimen;
    private float interval_left_right;
    private float margin_bottom;

    private Paint paint_date,paint_brokenLine,paint_dottedLine, paint_x_axis, framPaint;


    public List<String> XLabel;     //X轴上的刻度
    public List<Integer> Data;  //具体数据

    //private int screenWidth,screenHeight;


    public BrokeLine(Context context,List<String> XLabel,List<Integer> Data) {
        super(context);
        setWillNotDraw(false);
        this.XLabel = XLabel;
        this.Data = Data;
        //this.screenWidth = screenWidth;
        //this.screenHeight = screenHeight;

        init();
    }

    private void init() {
        Resources res = getResources();
        xOryDimen = res.getDimension(R.dimen.x_y_interval);
        interval_left_right = xOryDimen * 5.0f;
        margin_bottom = 8*xOryDimen * 0.2f;

        paint_date = new Paint();
        paint_date.setTextSize(xOryDimen * 0.8f);


        paint_brokenLine = new Paint();
        paint_brokenLine.setStrokeWidth(xOryDimen * 0.1f);
        paint_brokenLine.setColor(getResources().getColor(R.color.blueColor));
        paint_brokenLine.setAntiAlias(true);

        paint_x_axis = new Paint();
        paint_x_axis.setStrokeWidth(xOryDimen * 0.1f);
        paint_x_axis.setColor(getResources().getColor(R.color.fineColor));
        paint_x_axis.setAntiAlias(true);

        paint_dottedLine = new Paint();
        //paint_dottedLine.setStrokeWidth(xOryDimen * 0.1f);
        paint_dottedLine.setStyle(Paint.Style.STROKE);
        paint_dottedLine.setColor(getResources().getColor(R.color.fineColor));

        framPaint = new Paint();
        framPaint.setAntiAlias(true);
        framPaint.setStrokeWidth(2.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("画X轴了");
        super.onDraw(canvas);
        paintYLine(canvas);
        paintXLine(canvas);
        paintBrokenLine(canvas);
    }



    private void paintBrokenLine(Canvas canvas) {
        float xTemp = 0,yTemp = 0;
        Shader shader = new LinearGradient(0,0,0,getHeight(),
                new int[] {Color.argb(50,0,255,255),Color.argb(20,0,255,255),
                Color.argb(10,0,255,255)},null,Shader.TileMode.CLAMP);
        framPaint.setShader(shader);

        for(int i = 0;i < Data.size();i++) {
            float xPoint,yPoint;
            xPoint = 5.0f + interval_left_right * i;

            yPoint = getHeight() - margin_bottom - (((getHeight()-margin_bottom) / 100) * Data.get(i));
            if(i == 0) {
                paint_date.setColor(getResources().getColor(R.color.orangeColor));
                canvas.drawText(String.valueOf(Data.get(i)),xPoint,yPoint,paint_date);
            }else {
                paint_date.setColor(getResources().getColor(R.color.orangeColor));
                canvas.drawText(String.valueOf(Data.get(i)),xPoint,yPoint,paint_date);

                paint_date.setColor(getResources().getColor(R.color.blueColor));
                canvas.drawCircle(xPoint,yPoint,9.5f,paint_date);

                canvas.drawLine(xTemp,yTemp,xPoint,yPoint,paint_brokenLine);



                Path path1 = new Path();
                path1.moveTo(xTemp,yTemp);
                path1.lineTo(xPoint,yPoint);
                path1.lineTo(xPoint,getHeight());
                path1.close();
                canvas.drawPath(path1,framPaint);

                Path path2 = new Path();
                path2.moveTo(xTemp,yTemp);
                path2.lineTo(xTemp,getHeight());
                path2.lineTo(xPoint,getHeight());
                path2.close();
                canvas.drawPath(path2,framPaint);

//                if(i == Data.size() - 1) {
//                    path1.
//                }

            }

            xTemp = xPoint;
            yTemp = yPoint;
        }
    }

    private void paintYLine(Canvas canvas) {
        paint_date.setColor(getResources().getColor(R.color.fineColor));
        paint_date.setStrokeWidth(xOryDimen * 0.1f);
        canvas.drawLine(5.0f,0,5.0f,getHeight() - margin_bottom,paint_date);
        for(int i = 1;i < 11;i++) {
            paint_date.setStrokeWidth(xOryDimen * 0.08f);
            //paint_date.setColor(getResources().getColor(R.color.fineColor));
            canvas.drawText(String.valueOf(10 * i),5.0f,((getHeight() - margin_bottom) / 10) * (10 - i),paint_date );
            if(i == 5) {
                Path path = new Path();
                path.moveTo(5.0f,((getHeight() - margin_bottom) / 10) * 5);
                path.lineTo(getWidth(),((getHeight() - margin_bottom) / 10) * 5);
                PathEffect effect = new DashPathEffect(new float[] {
                        xOryDimen * 0.2f,xOryDimen * 0.2f,xOryDimen * 0.2f,xOryDimen * 0.2f
                },xOryDimen * 0.1f);
                paint_dottedLine.setPathEffect(effect);
                paint_dottedLine.setColor(getResources().getColor(R.color.orangeColor));
                canvas.drawPath(path,paint_dottedLine);
            }
        }
    }

    private void paintXLine(Canvas canvas) {
        System.out.println("发哈人口哈");
        canvas.drawLine(5.0f,getHeight() - margin_bottom,getWidth(),getHeight() - margin_bottom, paint_x_axis);
        for(int i = 0;i < XLabel.size();i++) {
            paint_date.setStrokeWidth(xOryDimen * 0.08f);
            canvas.drawText(XLabel.get(i),interval_left_right * i,getHeight()-15.0f,paint_date);
            if (i < XLabel.size() - 1) {
                Path path = new Path();
                path.moveTo(0.5f + interval_left_right * (i+1),xOryDimen * 1.5f);
                path.lineTo(0.5f + interval_left_right * (i+1),getHeight() - margin_bottom);
                PathEffect effect = new DashPathEffect(new float[] {
                        xOryDimen * 0.3f, xOryDimen * 0.3f,xOryDimen * 0.3f,xOryDimen * 0.3f
                },xOryDimen * 0.1f);
                paint_dottedLine.setPathEffect(effect);
                paint_dottedLine.setColor(getResources().getColor(R.color.fineColor));
                canvas.drawPath(path,paint_dottedLine);
            }
        }
    }


    //重写onMeasure方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //保存测量的数据
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(widthSpec);
        int specSize  = MeasureSpec.getSize(widthSpec);

        if(specMode == MeasureSpec.EXACTLY) {
            //父控件不对子View进行控制
            result = specSize;
        }else{
            Log.i("measureWidth","MeasureSpec.EXACTLY");
            result = (int)interval_left_right * XLabel.size() + 50;
        }

        return result;

    }

    private int measureHeight(int heightSpec) {
        int result =0;
        int specMode = MeasureSpec.getMode(heightSpec);
        int specSize = MeasureSpec.getSize(heightSpec);
        if(specMode == MeasureSpec.EXACTLY) {
            //父控件不对子View进行控制
            result = specSize;
        }else{
            Log.i("measureWidth","MeasureSpec.EXACTLY");
            result = 400;
        }
        return result;
    }


}