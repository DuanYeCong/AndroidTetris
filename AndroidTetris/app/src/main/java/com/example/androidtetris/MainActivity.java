package com.example.androidtetris;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends GameActivity implements View.OnTouchListener,ComeBackAdapter {
    public Shape shape;
    public Ground ground;
    public ShapeFactory shapeFactory;
    public View gameView;
    public Controller controller;
    public int downX;
    public int downY;
    public int upX;
    public int upY;
    public ImageView pause;
    public TextView currentTime;
    public long Score = 0;
    public String etime;
    public String escore;
    public TextView gameScore;
    public Chronometer timer;
    public long timer1 = 0;

    public SimpleDateFormat sd;
    public int milliseconds;
    public int hour;
    public int minute;
    public int sec;
    public int touchLength = Constant.TouchLength;
    public int again;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        again = intent.getIntExtra("again", 0);//判断是否为继续游戏
        startActivity();
    }

    public void startActivity() {
        timer = (Chronometer) findViewById(R.id.gameTime);//计时器Chronometer
//        if (again == 2) {
//            if (timer != null) {
//                timeAgain();
//                timer.setBase(SystemClock.elapsedRealtime()+milliseconds);
//
//                Log.d("DycCC", "ww" +SystemClock.elapsedRealtime());
//                Log.d("DycCC", "ww" +milliseconds);
//                Log.d("DycCC", "ww" +(SystemClock.elapsedRealtime()+milliseconds));
//            }
//        }
        //计时时间：当前时刻时间减去暂停时刻（游戏开始时间）的时间
//        time = (int)( (timer.getBase()) / 1000 / 60);
//        timer.setBase(SystemClock.elapsedRealtime() - timer1);
//        timer.setFormat("00:%s");
//        timer.start();

        final LinearLayout panel = (LinearLayout) findViewById(R.id.panel);
        pause = (ImageView) findViewById(R.id.pause);//游戏暂停按钮
        gameScore = (TextView) findViewById(R.id.gameScore);//游戏积分
        currentTime = (TextView) findViewById(R.id.currentTime);//当前时间
        gameView = findViewById(R.id.gameView);
        Log.d("TAG===>", "" + GameView.Cell_heightCount + "  " + GameView.Cell_widthCount);
        shapeFactory = new ShapeFactory();//方块形状生成
        ground = new Ground();


        if (again == 2) {
            if (ground != null && timer != null) {

                timeAgain();
                ground.setScore(Integer.valueOf(load()));//继续游戏恢复上次游戏的得分
                timer1 = (SystemClock.elapsedRealtime() + milliseconds) - timer.getBase();//继续游戏恢复上次游戏的时刻


//                GameData d = new GameData();
//                d.getShapeFactory();
//                Log.e("CYD", "d.getShapeFactory: " + d.getShapeFactory());
//                d.getGround();
//                Log.e("CYD", "d.getGround: " + d.getGround());


            }
        }
        timer.setBase(SystemClock.elapsedRealtime() - timer1);
        timer.setFormat("00:%s");
        timer.start();
        controller = new Controller(ground, shapeFactory, MainActivity.this);
        controller.startGame();
        panel.setOnTouchListener(this);
        new Thread(new TimeThread()).start();
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!controller.isPause) {
                    timer.stop();
                    timer1 = SystemClock.elapsedRealtime() - timer.getBase();//计时时间：当前时刻时间减去暂停时刻的时间，继续计时
                    controller.isPause = true;
                    pause.setImageResource(R.drawable.pausebutton);
                    pausedByButton = true;
                } else {
                    pause.setImageResource(R.drawable.playbutton);
                    controller.beginThread();
                    timer.setBase(SystemClock.elapsedRealtime() - timer1);//计时时间：当前时刻时间减去开始时刻的时间
                    timer.start();
                    pausedByButton = false;
                }

            }
        });
    }

    private boolean pausedByButton = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (!pausedByButton) {
            controller.isPause = false;
            timer.setBase(SystemClock.elapsedRealtime() - timer1);//计时时间：当前时刻时间减去开始时刻的时间
            timer.start();
            pause.setImageResource(R.drawable.playbutton);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        controller.isPause = true;
        timer.stop();
        timer1 = SystemClock.elapsedRealtime() - timer.getBase();//计时时间：当前时刻时间减去暂停时刻的时间，继续计时
        pause.setImageResource(R.drawable.pausebutton);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!pausedByButton) {
            controller.isPause = false;
            timer.setBase(SystemClock.elapsedRealtime() - timer1);//计时时间：当前时刻时间减去开始时刻的时间
            timer.start();
            pause.setImageResource(R.drawable.playbutton);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (controller.isPause) {
            return false;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("TAG===>", "onTouch");
            downX = (int) event.getX();
            downY = (int) event.getY();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {

            upX = (int) event.getX();
            upY = (int) event.getY();

            if ((Math.abs(upX - downX) - Math.abs(upY - downY)) > 0) {

                if ((upX - downX) > touchLength) {

                    if (ground.isMove(shape, Shape.RIGHT)) {

                        shape.moveRight();
                        refreshDisplay(shape, ground);
                        return true;

                    }
                } else if ((upX - downX) < -touchLength) {

                    if (ground.isMove(shape, Shape.LEFT)) {
                        shape.moveLeft();
                        refreshDisplay(shape, ground);
                        return true;
                    }
                }

            } else if ((Math.abs(upX - downX) - Math.abs(upY - downY)) < 0) {

                if ((upY - downY) > touchLength) {
                    if (ground.isMove(shape, Shape.DOWN)) {
                        while (ground.isMove(shape, Shape.DOWN)) {


                            shape.moveDown();
                            refreshDisplay(shape, ground);
                        }
                        return true;
                    }
                } else if ((upY - downY) < -touchLength) {

                    if (ground.isMove(shape, Shape.ROTATE)) {
                        shape.moveUp();
                        refreshDisplay(shape, ground);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);//异步处理机制
            switch (msg.what) {
                case 1:
                    ((GameView) gameView).display((Shape) msg.obj, ground);

                    gameScore.setText(ground.getScore() + "");
                    switch ((int) (ground.getScore() - Score)) {
                        case 50:
                            Score = ground.getScore();
                            break;
                        case 100:
                            Score = ground.getScore();
                            break;
                        case 200:
                            Score = ground.getScore();
                            break;
                        case 400:
                            Score = ground.getScore();
                            break;

                        default:
                            break;
                    }
                    break;

                case 2:
                    Calendar cal = Calendar.getInstance();//通过日历组件获取当前时间,Calendar可以获得年月日时分秒
                    hour = cal.get(Calendar.HOUR_OF_DAY);
                    minute = cal.get(Calendar.MINUTE);
                    sec = cal.get(Calendar.SECOND);
                    currentTime.setText(CurrentTime.getTime(hour, minute, sec));
                    break;
            }
        }
    };


    @Override
    public void refreshDisplay(Shape shape) {

    }

    @Override
    public void refreshDisplay(Shape shape, Ground ground) {
        this.shape = shape;
        Message message = new Message();
        message.what = 1;
        message.obj = shape;
        handler.sendMessage(message);
    }

    @Override
    public boolean isEnd(Shape eshape) {
        return false;
    }

    public void timeAgain() {

        String a = load1();
        List<String> digitList = new ArrayList<String>();//对时间字符串进行数字筛选
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(a);
        String result = m.replaceAll("");
        for (int i = 0; i < result.length(); i++) {
            digitList.add(result.substring(i, i + 1));
        }
        int s1 = Integer.valueOf(digitList.get(0));
        int s2 = Integer.valueOf(digitList.get(1));
        int s3 = Integer.valueOf(digitList.get(2));
        int s4 = Integer.valueOf(digitList.get(3));
        int s5 = Integer.valueOf(digitList.get(4));
        int s6 = Integer.valueOf(digitList.get(5));

        milliseconds = s6 * 1000 + s5 * 10000 + s4 * 60 * 1000 + s3 * 60 * 10000 + s2 * 3600 * 1000 + s1 * 3600 * 10000;
//        Log.e("DYDDC", "BBBb " +milliseconds );
//        sd = new SimpleDateFormat("00:mm:ss");
//        sd.format(new Date(milliseconds));
//        Log.e("DYDDC", "BBBb " +sd.format(new Date(milliseconds)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        escore = gameScore.getText().toString();
        etime = timer.getText().toString();
        save(escore);//保存活动结束分数
        save1(etime);//保存活动结束时间
//        String shapeFactory=ShapeFactory;
//
//        GameData gamedata = new GameData();
//        gamedata.setShapeFactory("shapeFactory");
//        gamedata.setGround("ground");


        controller.isPause = true;
        finish();
    }

    class TimeThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    public void gameOver() {

        timer.stop();
        etime = timer.getText().toString();//计时器时间获取传递
        Intent intent = new Intent(this, GameOver.class);
        intent.putExtra("time", etime);
        Log.d("TAG----=>", "" + etime);
        intent.putExtra("score", ground.getScore() + "");//分数获取传递
        Log.d("tag", intent.getStringExtra("score") + "1");
        startActivity(intent);
        finish();
    }

    public void save(String escore) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("escore", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(escore);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("escore");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    public void save1(String etime) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("etime", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(etime);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String load1() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("etime");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

//    public GameData getGameData() {
//        GameData gd = new GameData();
//        gd.setShapeFactory(this.shapeFactory);
//        gd.setGround(this.ground);
//        return gd;
//    }
//
//    public GameData setGameData(GameData gd) {
//        this.shapeFactory = gd.getShapeFactory();
//        this.ground = gd.getGround();
//    }
}