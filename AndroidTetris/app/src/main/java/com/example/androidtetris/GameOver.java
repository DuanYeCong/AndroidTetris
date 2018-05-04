package com.example.androidtetris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by duanyecong on 17-9-26.
 */

public class GameOver extends GameActivity {
    public Button button1;
    public TextView textViewTime;
    public TextView textViewScore;
    public Button button2;
    public int score;
    private String stringExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        Intent intent = getIntent();

        textViewTime = (TextView) findViewById(R.id.TextView1);
        final String str =intent.getStringExtra("time");
        textViewTime.setText(str);//获得游戏时间
        textViewScore = (TextView) findViewById(R.id.TextView2);
        stringExtra = intent.getStringExtra("score");
        this.score = Integer.parseInt(intent.getStringExtra("score"));
        textViewScore.setText(intent.getStringExtra("score"));//获得游戏积分

        button1 = (Button) findViewById(R.id.Button1);
        button2 = (Button) findViewById(R.id.Button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOver.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GameOver.this,PlayerName.class);
                intent.putExtra("score", stringExtra);

                Log.d("tag",String.valueOf(GameOver.this.score)+"1");
                startActivity(intent);
                finish();
            }
        });
    }
}
