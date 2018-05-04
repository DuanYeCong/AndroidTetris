package com.example.androidtetris;

import android.util.Log;


/**
 * Created by duanyecong on 17-9-25.
 */

public class Controller implements ComeBackAdapter {
    public Shape shape;
    public Ground ground;
    public ShapeFactory shapeFactory;

    public ComeBackAdapter mainActivity;
    public boolean isPause=false;
    @Override
    public void refreshDisplay(Shape shape) {
        // TODO Auto-generated method stub

        mainActivity.refreshDisplay(shape,ground);//更新方块

    }

    public void startGame(){
        Log.d("TAG===>","startGame");
        shape=shapeFactory.getShape(Controller.this);


    }


    public Controller(Ground ground,ShapeFactory shapeFactory,ComeBackAdapter mainActivity){

        this.ground = ground;
        this.shapeFactory = shapeFactory;
        this.mainActivity = mainActivity;

    }

    @Override
    public void refreshDisplay(Shape shape, Ground ground) {

    }

    @Override
    public boolean isEnd(Shape eshape) {
        Log.d("TAG===>11", "ground.isPause?"+isPause+" isMove:"+ground.isMove(eshape, Shape.DOWN)+" status:"+eshape.status);
        if(!isPause){
            if(ground.isMove(eshape, Shape.DOWN)){
                return false;
            }else {
                eshape.stopRunning();
            }
        }
        if(!isPause) {
            boolean isAccept = ground.accept(eshape);
            Log.e("TAG===>11", "isAccept:"+isAccept);
            if (isAccept) {

                this.shape = shapeFactory.getShape(Controller.this);

            } else {
                mainActivity.refreshDisplay(shape, ground);
                mainActivity.gameOver();
            }
        }

        return true;
    }
    public void beginThread(){
        isPause=false;
        shape.startThread();
    }
    @Override
    public void gameOver() {
    }
}
