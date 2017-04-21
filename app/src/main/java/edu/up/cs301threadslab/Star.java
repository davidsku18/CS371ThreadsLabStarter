package edu.up.cs301threadslab;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Star
 *
 * Stores data about an individual star on the sky
 *
 * @author Andrew Nuxoll
 * @version Fall 2015
 */
public class Star {
    protected float x; // x-coord
    protected float y; // y-coord

    public static final int ORIG_SIZE = 2;
    public static final int BURST_SIZE = 5;
    private int size = ORIG_SIZE; // all spots begin as a pin prick

    public static Paint starPaint = null; //used for drawing stars
    public static Paint blackPaint = null; //used to black out a star for twinkling effect
    protected Paint myPaint = null;

    /** this ctor creates a star at a specified location */
    public Star(int initX, int initY) {
        x = initX;
        y = initY;
        if (starPaint == null) {
            starPaint = new Paint();
            blackPaint = new Paint();
            starPaint.setColor(Color.WHITE);
            blackPaint.setColor(Color.BLACK);
        }
        myPaint = starPaint;
    }

    /**
     * the star can change its appearance to create a twinkling effect
     */
    public void twinkle() {
        if (size == BURST_SIZE) {
            if (myPaint == starPaint) {
                if (Math.random() < 0.75) {
                    myPaint = blackPaint;     //a burst star winks out
                }
            }
            else if (myPaint == blackPaint) {
                myPaint = starPaint;         //a burst, winked out star re-ignites
                size = ORIG_SIZE;
            }
        }
        else {  //ORIG_SIZE
            if (myPaint == blackPaint) {
                myPaint = starPaint;          //a winked out star re-ignites
            }
            else if (Math.random() < 0.05) {
                size = BURST_SIZE;            //a star bursts
            }
            else if (Math.random() < 0.05) {
                myPaint = blackPaint;         //a star winks out
            }
        }
    }//twinkle


    /**
     * a star knows how to draw itself on a given canvas.  This method should be called for each
     * frame of the animation
     *
     * @param canvas
     *            is the canvas to draw upon
     */
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, size, myPaint);
    }
}// class Star

