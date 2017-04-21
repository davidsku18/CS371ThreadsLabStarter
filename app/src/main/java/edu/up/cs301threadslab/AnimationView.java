package edu.up.cs301threadslab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * AnimationView
 *
 * displays a collection of animations
 *
 * @author Andrew Nuxoll
 * @version Fall 2015
 */
public class AnimationView extends SurfaceView{
    // track the current dimensions of this surface view
    private int width;
    private int height;

    //a list of the animations I'm currently displaying
    private ArrayList<Animation> anims = new ArrayList<Animation>();

    /** called by the ctors to initialize the variables I've added to this class */
    private void myInitializationStuff() {
        setWillNotDraw(false);
        setBackgroundColor(Color.BLACK);  //enforce a black background for all animations
    }

    /* override all constructors since we don't know which one the Activity will use */
    public AnimationView(Context context) {
        super(context);
        myInitializationStuff();
    }

    public AnimationView(Context context, AttributeSet set) {
        super(context, set);
        myInitializationStuff();
    }

    public AnimationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        myInitializationStuff();
    }

    /** keep track of my dimensions on the screen */
    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
        super.onSizeChanged(xNew, yNew, xOld, yOld);
        this.width = xNew;
        this.height = yNew;
        for(Animation anim : anims) {
            anim.setSize(this.width, this.height);
        }
    }

    // accessors
    // Note: getWidth and getHeight are already in View and used for something
    // slightly different
    public int getMyWidth() {
        return this.width;
    }

    public int getMyHeight() {
        return this.height;
    }

    /** add an animation */
    public void addAnimation(Animation newAnim) {
        anims.add(newAnim);
    }

    /** draw the animations */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for(Animation anim : anims) {
            anim.draw(canvas);
        }
    }//draw

    /** This method is called each time the seekbar's progress changes.  This will always be a
     *  value between 1 and 100.
     */
    public void progressChange(int newProgress) {
        for(Animation anim: anims) {
            anim.progressChange(newProgress);
        }
    }

}//class AnimationView
