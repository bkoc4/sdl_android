package com.smartdevicelink.streaming.touch;

import android.view.View;

import com.smartdevicelink.proxy.rpc.TouchCoord;

/**
 * Created by Burak on 23.02.2018.
 */

public interface SDLTouchManagerListener {
    /**
     * A single tap was received
     *
     * @param view The view find by the Hit tester.
     *             Note In the current version of the sdl library the hit tester is not implemented therefore the view will always be null
     * @param point The point where single tap is occurred.
     */
    void onSingleTapReceive(View view, TouchCoord point);

    /**
     * A double tap was received
     *
     * @param view The view find by the Hit tester.
     *             Note In the current version of the sdl library the hit tester is not implemented therefore the view will always be null
     * @param point The point where double tap is occurred. It is avarage of the taps.
     */
    void onDoubleTapReceive(View view, TouchCoord point);

    /**
     * A panning action is received. It will be updated once the
     *
     * @param view The view find by the Hit tester.
     *             Note In the current version of the sdl library the hit tester is not implemented therefore the view will always be null
     * @param toPoint It is the previous point where pan is started.
     * @param fromPoint It is the new point for panning action.
     *
     */
    void onPanningReceive(View view, TouchCoord fromPoint, TouchCoord toPoint);

    /**
     * Pinch moved and changed scale
     *
     * @param view The view find by the Hit tester.
     *             Note In the current version of the sdl library the hit tester is not implemented therefore the view will always be null
     * @param soint Center point of the pinch in the head unit's coordinate system
     */
    void onPinchReceive(View view, TouchCoord soint, double scale);

    /**
     * Panning started
     *
     * @param point Location of the panning start point in the head unit's coordinate system.
     */
    void onPaningStart(View view, TouchCoord point);

    /**
     * Panning ended
     *
     * @param view The view find by the Hit tester.
     *             Note In the current version of the sdl library the hit tester is not implemented therefore the view will always be null
     * @param point Location of the panning's end point in the head unit's coordinate system
     */
    void onPanningEnd(View view, TouchCoord point);

    /**
     * Panning canceled
     *
     * @param point Location of the panning's end point in the head unit's coordinate system
     */
    void onPanningCanceled(TouchCoord point);

    /**
     * Pinch did start
     *
     * @param point Center point of the pinch start point in the head unit's coordinate system.
     */
    void onPinchStart(View view, TouchCoord point);

    /**
     * Pinch did end
     *
     * @param view The view find by the Hit tester.
     *             Note In the current version of the sdl library the hit tester is not implemented therefore the view will always be null
     * @param point Center point of the pinch in the head unit's coordinate system
     */
    void onPinchEnd(View view, TouchCoord point);

    /**
     * Pinch canceled
     *
     * @param point Center point of the pinch in the head unit's coordinate system
     */
    void onPinchCanceled(TouchCoord point);
}
