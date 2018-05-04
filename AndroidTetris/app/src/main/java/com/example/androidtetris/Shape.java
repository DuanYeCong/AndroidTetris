package com.example.androidtetris;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by duanyecong on 17-9-25.
 */

public class Shape {
    public int[][][] body;
    public ComeBackAdapter  listenner;
    public int top=0;
    public int left=4;
    public int status=0;
    public static  final int ROTATE=0;
    public static  final int LEFT=1;
    public static  final int RIGHT=2;
    public static  final int DOWN=3;
    public Bitmap  shapeImg;
    public void moveLeft(){
        left--;
    }
    public void moveRight(){
        left++;
    }
    public void moveDown(){
        top++;
    }
    public void moveUp(){
        status=(status+1)%body.length;
    }
    public void drawMe(Canvas canvas, Resources res){
        Paint paint1=new Paint();//绘画canvas
        paint1.setColor(Color.BLACK);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(8);//设置空心线宽
        shapeImg = BitmapFactory.decodeResource(res, R.drawable.star_b);//bitmap对图片进行操作，decodeResource字节数组获取，方块颜色
        for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(body[status][i][j]==1){
                    Rect src=new Rect();//Rectangle :矩形或者长方形
                    Rect dst=new Rect();
                    src.left=0;
                    src.top=0;
                    src.right=shapeImg.getWidth();
                    src.bottom=shapeImg.getHeight();
                    dst.left=(left+j)*GameView.Cell_Size;
                    dst.top=(top+i)*GameView.Cell_Size;
                    dst.right =(left+j+1)*GameView.Cell_Size;
                    dst.bottom = (top+i+1)*GameView.Cell_Size;
                    canvas.drawBitmap(shapeImg, src, dst, null);
                }
            }
        }
    }
    public synchronized boolean isMemeber(int x,int y,int flag){
        //Log.e("Shape111", "flag:"+flag+" x:"+x+" y:"+y);
        return body[flag][x][y]==1;
    }
    public Shape(ComeBackAdapter listener, int[][][] body, int status){
        this.listenner = listener;
        this.body = body;
        this.status = status;//在活动开始前先生成形状,bug解决方法
        new Thread(new ShapeMove()).start();
    }
    public void startThread(){
        Log.d("TAG===>","游戏重启");
    }
    private boolean isRunning = true;
    public void stopRunning(){
        isRunning = false;
    }

    class ShapeMove implements Runnable{
        @Override
        public void run() {
            Log.e("zhangqi", "ShapeMove111");
            while (isRunning){
                if(listenner != null && !listenner.isEnd(Shape.this)){
                    moveDown();
                    Log.e("zhangqi", "ShapeMove222");
                    listenner.refreshDisplay(Shape.this);
                }else{
                    //isRunning = false;
                }
                try{
                    Thread.sleep(Constant.SPEED);
                }catch (InterruptedException e1){
                    e1.printStackTrace();
                }
            }
            Log.e("zhangqi", "ShapeMove333");
        }
    }


    public int getTop(){
        return top;
    }
    public int getLeft(){
        return left;
    }
    public int[][][] getBody(){
        return body;
    }
}