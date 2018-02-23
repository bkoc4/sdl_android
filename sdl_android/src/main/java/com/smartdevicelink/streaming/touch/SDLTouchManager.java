package com.smartdevicelink.streaming.touch;

import com.smartdevicelink.proxy.rpc.OnTouchEvent;
import com.smartdevicelink.proxy.rpc.TouchCoord;
import com.smartdevicelink.proxy.rpc.TouchEvent;
import com.smartdevicelink.proxy.rpc.enums.TouchType;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Burak on 19.02.2018.
 */

public class SDLTouchManager {

    SDLTouchManagerListener mCallback;

    public void setSDLTouchManagerDelegate(SDLTouchManagerListener callback){
        this.mCallback = callback;
    }

    private enum SDLPerformingTouchType {
        SDLPerformingTouchTypeNone,
        SDLPerformingTouchTypeSingleTouch,
        SDLPerformingTouchTypeMultiTouch,
        SDLPerformingTouchTypePanningTouch;
    }

    private SDLPerformingTouchType performingTouchType;
    private TouchCoord previousTouch;
    private TouchEvent previousTouchEvent;
    private TouchCoord lastStoredTouchLocation;
    private TouchCoord lastNotifiedTouchLocation;
    private SDLPinchGesture currentPinchGesture;
    private double previousPinchDistance;
    private Timer singleTapTimer;
    private TouchEvent singleTapTouchEvent;

    //Constants (Please see the Constructor for the values)
    private int maximumNumberOfTouches;
    private long movementTimeThreshold; //millisecond
    private long tapTimeThreshold; //milliseconds
    private long tapDistanceThreshold;
    public boolean syncedPanningEnabled;
    public boolean touchEnabled;


    public SDLTouchManager(){

        movementTimeThreshold = 50;
        tapTimeThreshold = 400;
        tapDistanceThreshold = 50;
        touchEnabled = true;
        syncedPanningEnabled = true;
        maximumNumberOfTouches = 2;

    }

    public void cancelPendingTouches(){
        sdl_cancelSingleTapTimer();
    }

    private void syncFrame(){
        if (!touchEnabled || mCallback==null){
            return;
        }
        switch (performingTouchType){
            case SDLPerformingTouchTypePanningTouch:
                TouchCoord storedTouchLocation = lastStoredTouchLocation;
                TouchCoord notifiedTouchLocation = lastNotifiedTouchLocation;
                if ((storedTouchLocation == notifiedTouchLocation) || (storedTouchLocation==null) || (notifiedTouchLocation==null)){
                    return;
                }
                mCallback.onPanningReceive(null, notifiedTouchLocation,storedTouchLocation);
                lastNotifiedTouchLocation=storedTouchLocation;
                break;

            case SDLPerformingTouchTypeMultiTouch:
                if (previousPinchDistance == currentPinchGesture.getDistance()){
                    return;
                }
                double scale = currentPinchGesture.getDistance()/previousPinchDistance;
                mCallback.onPinchReceive(null, currentPinchGesture.getCenter(), scale);
                break;

        }
    }

    /**
     * Handles detecting the type and state of the gesture and notifies the appropriate delegate callbacks.
     * @param notification     A OnTouchEvent notification.
     */
    public void sdl_onTouchEvent (OnTouchEvent notification){
        TouchEvent touchEvent  = notification.getEvent().get(0);
        TouchType touchType = notification.getType();
        if ((!touchEnabled) || touchEvent.getId()>maximumNumberOfTouches || mCallback==null){
            return;
        }

        switch (touchType){
            case BEGIN:
                sdl_handleOnTouchBegan(touchEvent);
                break;

            case MOVE:
                sdl_handleTouchMoved(touchEvent);
                break;

            case END:
                sdl_handleTouchEnded(touchEvent);
                break;

            case CANCEL:
                sdl_handleTouchCanceled(touchEvent);
                break;
        }

    }

    /**
     *  Handles a BEGIN touch event sent by Core
     *
     *  @param touchEvent Gesture information
     */
    private void sdl_handleOnTouchBegan(TouchEvent touchEvent){

        TouchCoord touch = touchEvent.getTouchCoordinates().get(0);
        performingTouchType = SDLPerformingTouchType.SDLPerformingTouchTypeSingleTouch;
        previousTouchEvent = touchEvent;

        switch (touchEvent.getId()){
            case 0: //First Finger
                previousTouch = touch;
                break;

            case 1: //Second Finger
                performingTouchType = SDLPerformingTouchType.SDLPerformingTouchTypeMultiTouch;
                currentPinchGesture = new SDLPinchGesture(previousTouch,touch);
                previousPinchDistance = currentPinchGesture.getDistance();
                //TODO: Add UI Hit tester here
                mCallback.onPinchStart(null, currentPinchGesture.getCenter());
                break;
        }

    }

    /**
     * Handles a MOVE touch event sent by Core
     * @param touchEvent Gesture information
     */
    private void sdl_handleTouchMoved(TouchEvent touchEvent){

        if ((touchEvent.getTimestamps().get(0)-previousTouchEvent.getTimestamps().get(0) > movementTimeThreshold) ||
                !syncedPanningEnabled){
            return;
        }

        TouchCoord currentTouchCoord = touchEvent.getTouchCoordinates().get(0);

        switch (performingTouchType){
            case SDLPerformingTouchTypeMultiTouch:
                switch (touchEvent.getId()){
                    case 0: //First Finger
                        currentPinchGesture.setFirstTouch(currentTouchCoord);
                        break;
                    case 1: //Second Finger
                        currentPinchGesture.setSecondTouch(currentTouchCoord);
                        break;
                }
                if (!syncedPanningEnabled){
                    syncFrame();
                }
                break;

            case SDLPerformingTouchTypeSingleTouch:
                lastNotifiedTouchLocation = currentTouchCoord;
                lastStoredTouchLocation = currentTouchCoord;

                performingTouchType= SDLPerformingTouchType.SDLPerformingTouchTypePanningTouch;
                //TODO: Add UI Hit tester here
                mCallback.onPaningStart(null, currentPinchGesture.getCenter());
                break;

            case SDLPerformingTouchTypePanningTouch:
                if (!syncedPanningEnabled){
                    syncFrame();
                }
                lastStoredTouchLocation = currentTouchCoord;
                break;

            case SDLPerformingTouchTypeNone:
                break;
        }
        previousTouch = currentTouchCoord;
        previousTouchEvent = touchEvent;

    }

    /**
     * Handles a END touch type notification sent by Core
     *
     * @param touchEvent    Gesture information
     */
    private void sdl_handleTouchEnded(TouchEvent touchEvent){

        TouchCoord currentTouchCoord = touchEvent.getTouchCoordinates().get(0);

        switch (performingTouchType){
            case SDLPerformingTouchTypeMultiTouch:
                sdl_setMultiTouchFingerTouchForTouch(touchEvent);
                if (currentPinchGesture.isValid()) {
                    //TODO: Add UI Hit tester here
                    mCallback.onPinchEnd(null, currentPinchGesture.getCenter());
                }
                currentPinchGesture = null;
                break;

            case SDLPerformingTouchTypePanningTouch:
                //TODO: Add UI Hit tester here
                mCallback.onPanningEnd(null, currentTouchCoord);
                lastStoredTouchLocation = null;
                lastNotifiedTouchLocation = null;
                break;

            case SDLPerformingTouchTypeSingleTouch:
                if (singleTapTimer==null){
                    sdl_initializeSingleTapTimerAtPoint(currentTouchCoord);
                    singleTapTouchEvent = touchEvent;
                }else{
                    sdl_cancelSingleTapTimer();

                    TouchCoord firstTap = singleTapTouchEvent.getTouchCoordinates().get(0);
                    TouchCoord secondTap = touchEvent.getTouchCoordinates().get(0);

                    long deltaTimeStamp = touchEvent.getTimestamps().get(0).longValue() - singleTapTouchEvent.getTimestamps().get(0).longValue();
                    int deltaX = firstTap.getX() - secondTap.getX();
                    int deltaY = firstTap.getY() - secondTap.getY();

                    if ((deltaTimeStamp<= tapTimeThreshold) && (deltaX<=tapDistanceThreshold) && (deltaY<=tapDistanceThreshold)){
                        //TODO: Add UI Hit tester here
                        TouchCoord center = new TouchCoord();
                        center.setX((firstTap.getX() + secondTap.getX())/2);
                        center.setY((firstTap.getY() + secondTap.getY())/2);
                        mCallback.onDoubleTapReceive(null, center);
                    }

                    singleTapTouchEvent = null;

                }
                break;

            case SDLPerformingTouchTypeNone:
                break;
        }

        previousTouchEvent = null;
        previousTouch = null;
        performingTouchType = SDLPerformingTouchType.SDLPerformingTouchTypeNone;

    }

    /**
     *  Handles a CANCEL touch event sent by CORE. A CANCEL touch event is sent when a gesture is interrupted during a video stream. This can happen when a system dialog box appears on the screen, such as when the user is alerted about an incoming phone call.
     *
     *  Pinch and pan gesture subscribers are notified if the gesture is canceled. Tap gestures are simply canceled without notification.
     *
     *  @param touchEvent    Gesture information
     */
    private void sdl_handleTouchCanceled(TouchEvent touchEvent){
        if (singleTapTimer !=null){
            singleTapTimer.cancel();
            singleTapTimer=null;
        }
        switch (performingTouchType){
            case SDLPerformingTouchTypeMultiTouch:
                sdl_setMultiTouchFingerTouchForTouch(touchEvent);
                if (currentPinchGesture.isValid()) {
                    //TODO: Add UI Hit tester here
                    mCallback.onPinchCanceled(currentPinchGesture.getCenter());
                }
                currentPinchGesture = null;
                break;

            case SDLPerformingTouchTypePanningTouch:
                mCallback.onPanningCanceled(touchEvent.getTouchCoordinates().get(0));
                break;

            case SDLPerformingTouchTypeSingleTouch: //Fallthrough

            case SDLPerformingTouchTypeNone:
                break;

        }
    }

    /**
     * Saves the pinch touch gesture to the correct finger
     *
     * @param touchEvent   Gesture information
     */
    private void sdl_setMultiTouchFingerTouchForTouch(TouchEvent touchEvent){
        TouchCoord currentTouchCoord = touchEvent.getTouchCoordinates().get(0);
        switch (touchEvent.getId()){
            case 0: //First Finger
                currentPinchGesture.setFirstTouch(currentTouchCoord);
                break;
            case 1: //Second Finger
                currentPinchGesture.setSecondTouch(currentTouchCoord);
                break;
        }

    }

    /**
     * Creates a timer used to detect the type of tap gesture (single or double tap)
     *
     * @param point  Screen coordinates of the tap gesture
     */
    private void sdl_initializeSingleTapTimerAtPoint(final TouchCoord point){
        singleTapTimer = new Timer();
        singleTapTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //TODO: Add UI Hit tester here
                mCallback.onSingleTapReceive(null, point);
                singleTapTouchEvent = null;
            }
        }, tapTimeThreshold);
    }

    /**
     *  Cancels a tap gesture timer
     */
    private void sdl_cancelSingleTapTimer(){
        if (singleTapTimer==null){
            return;
        }
        singleTapTimer.cancel();
        singleTapTimer = null;
    }

}
