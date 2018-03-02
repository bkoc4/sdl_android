package com.smartdevicelink.streaming.touch;

import com.smartdevicelink.proxy.rpc.TouchCoord;

/**
 * Created by Burak on 19.02.2018.
 */

public class SDLPinchGesture {

    private TouchCoord noneTouchCoord;

    private double distance;
    private TouchCoord firstTouch;
    private TouchCoord secondTouch;
    private TouchCoord center;

    void initNoneTouch(){
        noneTouchCoord = new TouchCoord();
        noneTouchCoord.setX(-1);
        noneTouchCoord.setY(-1);
    }

    public SDLPinchGesture(TouchCoord firstTouch, TouchCoord secondTouch) {
        initNoneTouch();

        this.firstTouch = firstTouch;
        this.secondTouch = secondTouch;
        this.distance = -1;
        this.center = noneTouchCoord;
    }

    public void setFirstTouch(TouchCoord firstTouch) {
        this.firstTouch = firstTouch;
        sdl_invalidate();
    }

    public void setSecondTouch(TouchCoord secondTouch) {
        this.secondTouch = secondTouch;
        sdl_invalidate();
    }

    public double getDistance() {
        if (distance==-1){
            int deltaX = firstTouch.getX()-secondTouch.getX();
            int deltaY = firstTouch.getY()-secondTouch.getY();
            distance = Math.sqrt(deltaX*deltaX+deltaY*deltaY);

        }
        return distance;
    }

    public TouchCoord getCenter() {
        if ((center.getX()==noneTouchCoord.getX()) && (center.getY()==noneTouchCoord.getY())){
            center.setX((firstTouch.getX()+secondTouch.getX())/2);
            center.setY((firstTouch.getY()+secondTouch.getY())/2);
        }
        return center;
    }

    boolean isValid(){
        return ((firstTouch!=null) && (secondTouch!=null));
    }

    void sdl_invalidate(){
        center = noneTouchCoord;
        distance=-1;
    }
}
