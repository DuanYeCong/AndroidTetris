package com.example.androidtetris;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by duanyecong on 17-9-29.
 */

public class CustomRank extends GameActivity {
    public Button cancel;
    public Button logout;
    private DatabaseHelper helper;
    public  String name="";
    public  String score="";
    public TextView name1;
    public TextView name2;
    public TextView name3;
    public TextView name4;
    public TextView name5;
    public TextView player1_score;
    public TextView player2_score;
    public TextView player3_score;
    public TextView player4_score;
    public TextView player5_score;

    public String[] DB_name;
    public String[] DB_score;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_rank);
        helper = new DatabaseHelper(this);//数据库创建
        helper.getWritableDatabase();
        queryDB();
        name1=(TextView)findViewById(R.id.name1);
        player1_score=(TextView)findViewById(R.id.player1_score);

        name2=(TextView)findViewById(R.id.name2);
        player2_score=(TextView)findViewById(R.id.player2_score);

        name3=(TextView)findViewById(R.id.name3);
        player3_score=(TextView)findViewById(R.id.player3_score);


        name4=(TextView)findViewById(R.id.name4);
        player4_score=(TextView)findViewById(R.id.player4_score);

        name5=(TextView)findViewById(R.id.name5);
        player5_score=(TextView)findViewById(R.id.player5_score);


        DB_name = name.split(",");
        for (int i = 0;i<=DB_name.length;i++){
           switch (DB_name.length) {
               case   0:
//                   Toast.makeText(this, "NOT HAVE SCORE", Toast.LENGTH_SHORT).show();
                   break;
               case   1:
               name1.setText(DB_name[0]);
               break;
               case   2:
               name1.setText(DB_name[0]);
               name2.setText(DB_name[1]);
               break;
               case   3:
               name1.setText(DB_name[0]);
               name2.setText(DB_name[1]);
               name3.setText(DB_name[2]);
               break;
               case   4:
               name1.setText(DB_name[0]);
               name2.setText(DB_name[1]);
               name3.setText(DB_name[2]);
               name4.setText(DB_name[3]);
               break;
               case   5:
               name1.setText(DB_name[0]);
               name2.setText(DB_name[1]);
               name3.setText(DB_name[2]);
               name4.setText(DB_name[3]);
               name5.setText(DB_name[4]);
               break;

               default:
                   name1.setText(DB_name[0]);
                   name2.setText(DB_name[1]);
                   name3.setText(DB_name[2]);
                   name4.setText(DB_name[3]);
                   name5.setText(DB_name[4]);
                   break;
           }

        }
        DB_score = score.split(",");

        for (int i = 0;i<=DB_score.length;i++){
            switch (DB_score.length) {
                case   0:
                    break;
                case   1:
                    player1_score.setText(DB_score[0]);
                    break;
                case   2:
                    player1_score.setText(DB_score[0]);
                    player2_score.setText(DB_score[1]);
                    break;
                case   3:
                    player1_score.setText(DB_score[0]);
                    player2_score.setText(DB_score[1]);
                    player3_score.setText(DB_score[2]);
                    break;
                case   4:
                    player1_score.setText(DB_score[0]);
                    player2_score.setText(DB_score[1]);
                    player3_score.setText(DB_score[2]);
                    player4_score.setText(DB_score[3]);
                    break;
                case   5:
                    player1_score.setText(DB_score[0]);
                    player2_score.setText(DB_score[1]);
                    player3_score.setText(DB_score[2]);
                    player4_score.setText(DB_score[3]);
                    player5_score.setText(DB_score[4]);
                    break;

                default:
                    player1_score.setText(DB_score[0]);
                    player2_score.setText(DB_score[1]);
                    player3_score.setText(DB_score[2]);
                    player4_score.setText(DB_score[3]);
                    player5_score.setText(DB_score[4]);
                    break;
            }
            
        }



        cancel=(Button) findViewById(R.id.cancel) ;
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomRank.this,Homepage.class);
                startActivity(intent);
                finish();

            }
        });
        logout=(Button) findViewById(R.id.logout) ;
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SQLiteDatabase db=helper.getWritableDatabase();
                db.execSQL("delete from table_person");//删除表中数据,或者db.delete(table_person,null,null);
                finish();
            }

        });
    }



    public void queryDB(){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql = "select * from table_person ORDER BY player_score desc;" ;
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                name+=cursor.getString(cursor.getColumnIndex("player_name"))+",";
                Log.d("DYC", "name: "+name);
                score+=cursor.getInt(cursor.getColumnIndex("player_score"))+""+",";

            }while (cursor.moveToNext());
        }
        Log.d("DYC", "score: "+score);
        cursor.close();

    }
}
