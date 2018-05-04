package com.example.androidtetris;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Homepage extends GameActivity {
    public ImageView startGame;
    public Button player_rank;
    public Button player_help;
    public Button player_again;
    private boolean isStart = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        getGameSharedPreference();

        player_rank=(Button) findViewById(R.id.palyer_rank);//分数排行榜按钮
        player_help=(Button) findViewById(R.id.palyer_help);//游戏帮助按钮
        player_again=(Button) findViewById(R.id.palyer_again);//继续游戏按钮
        startGame = (ImageView) findViewById(R.id.startGame);//开始游戏按钮
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        player_rank.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean isOpen = false;
                if (!isOpen){
                    isOpen= true;//判断活动是否开启多个?
                    Intent intent = new Intent(Homepage.this,CustomRank.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        player_again.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Homepage.this,MainActivity.class);
                intent.putExtra("again",2);
                startActivity(intent);
            }
        });
        player_help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this,PlayerHelp.class);
                startActivity(intent);

            }
        });
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            finish();//对back键进行重写
//        }
//        return true;
//    }


    public void getGameSharedPreference(){

        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        Constant.SPEED = sp.getInt("speed",800);//下落速度进行设置
        Constant.Cell_widthCount = sp.getInt("Cell_widthCount", 10);//网格线设置
        Constant.Cell_heightCount = sp.getInt("Cell_heightCount", Constant.Cell_widthCount*14/10);
        Constant.TouchLength = sp.getInt("touchLength", 100);
        SharedPreferences mp = getSharedPreferences("music_config", Context.MODE_PRIVATE);
        Log.d("TAG===>", mp.getBoolean("isButtonGridLin" + "e", true)+"  getSharedPreferences");
        Constant.GridPaint = mp.getBoolean("isButtonGridLine", true);
    }
}