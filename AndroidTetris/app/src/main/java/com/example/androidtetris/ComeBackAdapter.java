package com.example.androidtetris;

/**
 * Created by duanyecong on 17-9-25.
 */

public interface ComeBackAdapter {
    public  void refreshDisplay(Shape shape);
    public  void refreshDisplay(Shape shape,Ground ground);
    public  boolean isEnd(Shape eshape);
    public  void gameOver();
}
