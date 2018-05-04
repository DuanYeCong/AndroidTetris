package com.example.androidtetris;


/**
 * Created by duanyecong on 17-9-26.
 */

public class CurrentTime {


        public static String getTime(int hour,int minute,int sec ){

            return  (hour>=10?hour+"":"0"+hour+"")+":"+(minute>=10?minute+"":"0"+minute+"")+":"+(sec>=10?sec+"":"0"+sec+"");
        }

    }
