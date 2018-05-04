package com.example.androidtetris;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by duanyecong on 17-9-28.
 */

public class PlayerName extends GameActivity{
    public EditText name;
    public TextView player_score;
    public Button   back_homepage;
    public Button   save_name;
    public String   score1;
    private DatabaseHelper helper;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_name);
        name=(EditText)findViewById(R.id.name);//输入玩家名称
        helper = new DatabaseHelper(this);//创建数据库表格

        Intent intent = getIntent();
        player_score = (TextView) findViewById(R.id.player_score);

        score1 = intent.getStringExtra("score");
        Log.d("PlayerName",score1+" ");
        player_score.setText(score1);//获得游戏积分
        back_homepage = (Button) findViewById(R.id.back_homepage);
        save_name = (Button) findViewById(R.id.savename);//保存玩家名称
        back_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(PlayerName.this, Homepage.class);
                    startActivity(intent);
                    finish();
                }
        });
        save_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String n =name.getText().toString();
                insertDatabase(n,score1);//向数据库插入数据（玩家姓名，得分）
                finish();
            }
        });
    }

    public void insertDatabase(String name, String score1){
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("player_name", name);//向表格插入玩家姓名
        values.put("player_score", score1);//向表格插入玩家积分
        db.insert(DatabaseHelper.TABLE_NAME, null, values);
    }
    public void updataDatabase(String score1)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("player_score", score1);
        db.update("table_person",values,"player_score= ?",new String[]{"-"});
    }

}

