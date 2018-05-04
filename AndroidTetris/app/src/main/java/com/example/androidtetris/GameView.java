package com.example.androidtetris;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by duanyecong on 17-9-25.
 */

public class GameView extends View{
    public Shape shape;
    public Ground ground;
    public static int Cell_Size;
    public static int Cell_widthCount;
    public static int Cell_heightCount;

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Cell_widthCount=Constant.Cell_widthCount;
        Cell_heightCount=Constant.Cell_heightCount;
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Cell_widthCount=Constant.Cell_widthCount;
        Cell_heightCount=Constant.Cell_heightCount;
    }

    public GameView(Context context) {
        super(context);
        Cell_widthCount=Constant.Cell_widthCount;
        Cell_heightCount=Constant.Cell_heightCount;
    }

    public void display(Shape shape,Ground ground){

        this.shape = shape;
        this.ground = ground;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Init();
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        canvas.drawRect(0, 0, getWidth(), Cell_heightCount*Cell_Size+2, paint);
        Resources res = getResources();

        if(shape!=null&&ground!=null){
            shape.drawMe(canvas,res);
            ground.drawMe(canvas,res);
        }

    }
    public void Init(){
        int Width =getWidth();
        int Height =getHeight();
        Cell_Size =Width/Cell_widthCount;
        Cell_heightCount =Height/Cell_Size;

    }
}
