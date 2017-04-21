package edu.up.cs301threadslab;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * This application displays several animations.  It is used for the threads lab in CS371.
 *
 * @author Andrew Nuxoll
 * @version Fall 2015
 */
public class MainActivity extends Activity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private AnimationView myAV;
    private Button theButton;
    private SeekBar theSeekBar;
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup the animation(s)
        myAV = (AnimationView)findViewById(R.id.animationArea);
        myAV.addAnimation(new StarAnimation(myAV.getMyWidth(), myAV.getMyHeight()));

        //Let me know when someone taps the button
        theButton = (Button)findViewById(R.id.button);
        theButton.setOnClickListener(this);

        //Let me know when someone adjusts the seekbar
        theSeekBar = (SeekBar)findViewById(R.id.seekBar);
        theSeekBar.setOnSeekBarChangeListener(this);
        theSeekBar.setMax(900);

        DrawThread d = new DrawThread();
        d.start();
    }//onClick

    @Override
    public void onClick(View v) {
        myAV.postInvalidate();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        myAV.progressChange(seekBar.getProgress());
        myAV.postInvalidate();
    }

    /** These two methods aren't used */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    public class DrawThread extends Thread
    {
        public void run()
        {
            while (true) {
                myAV.postInvalidate();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ie) {

                }
            }
        }
    }
}
