package edu.up.cs301threadslab;

import android.graphics.Canvas;

import java.util.Random;

/**
 * Animation
 *
 * is an abstract class that indicates the implementing class can display an animation on a canvas
 *
 * @author Andrew Nuxoll
 * @version Fall 2015
 */
public abstract class Animation {

    /* dimensions of the animation canvas */
    protected int width;
    protected int height;

    /* We'll need a random number generator */
    protected static final Random rand = new Random();

    /** ctor inits instance variables */
    public Animation(int initWidth, int initHeight) {
        setSize(initWidth, initHeight);
    }

    /** the animation expects to be told how large the view is that it is drawing upon.
     * This method should be called when the animation changes and also each time the view changes
     * size.
     */
    public void setSize(int newWidth, int newHeight) {
        width = newWidth;
        height = newHeight;
    }

    /** This method is called repeatedly to get successive frames */
    public abstract void draw(Canvas canvas);

    /** This method is called whenever the user adjusts the seekbar */
    public abstract void progressChange(int newProgress);
}//interface Animation
